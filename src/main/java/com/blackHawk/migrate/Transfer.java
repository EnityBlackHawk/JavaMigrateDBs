package com.blackHawk.migrate;

import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * TOrigin: Tipo de origem
 * TDestination: Tipo de destino
 * */
public class Transfer<TOrigin, TDestination> {

    private TOrigin[] origin;
    private List<TDestination> destination;
    private Class<TDestination> destinationClass;

    //Construtor usado para criar a intancia da classe de destino
    private Constructor<TDestination> destinationConstructor;

    // Tabela para relacionar o id do banco relacional com o do do Mongo
    private static IndexTable indexTable;
    // Table para relacionar a classe do banco relacional com o do Mongo
    private static TypeTable typeTable;

    /**
     * vector: Vetor com as instancias dos dados que devem ser migrados
     * destinationClass: Classe do destino
     * */
    public Transfer(TOrigin[] vector, Class<TDestination> destinationClass) {
        origin = vector;
        this.destinationClass = destinationClass;
        destination = new ArrayList<TDestination>();
        if(this.indexTable == null)
            indexTable = new IndexTable();
        if(typeTable == null)
            typeTable = new TypeTable();

        typeTable.Register(origin[0].getClass(), destinationClass);
    }

    public Transfer(TOrigin[] vector, Class<TDestination> destinationClass, IndexTable indexTable) {
        origin = vector;
        this.destinationClass = destinationClass;
        destination = new ArrayList<TDestination>();
        if(this.indexTable == null)
            this.indexTable = indexTable;
    }

    // A funcao faz um loop por todos os metodos da classe de origem,
    // quando encontra um metodo cujo o nome começa com "get", ela pega o nome, troca o "get" por "set" e
    // busca um metodo que possui um nome formado. A metodo encontrado é invocado, passando o return da funcao get
    // como parametro. Em caso de referencias, é necessario buscar o tipo no TypeTable
    public List<TDestination> DoTransfer(DBControl db) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class originType = origin[0].getClass();
        Method[] m = originType.getMethods();


        if(destinationConstructor == null) destinationConstructor = destinationClass.getConstructor();

        for(int i = 0; i < origin.length; i++) {
            // Cria uma intancia do novo objeto
            TDestination d = destinationConstructor.newInstance();

            // Loop entre todos os metodos da classe de origem
            for (Method n : m) {
                // Recebe o nome do metodo
                String name = n.getName();
                var v = name.substring(0, 3);

                if (name.equals("getId")) {
                    // Registra o id do original na table, e armazena o id gerado para colocar no Mongo
                    String newId = indexTable.Register(originType, (int) n.invoke(origin[i]));
                    // Chama "setId" na instancia de destino, e newId como parametro
                    d.getClass().getMethod("setId", String.class).invoke(d, newId);
                }

                // Se o metodo começar com "get"...
                if (v.equals("get") && name != "getId" && name != "getClass") {
                    // Armazena o tipo de retorno do metodo para pesquisar o set
                    var type = n.getReturnType();

                    // Gera o nome do metodo set que deve ser chamado
                    String fildName = name.substring(3);
                    String destMethodName = "set" + fildName;

                    // Se o tipo de retorno do metodo get possuir a anotacao Entity, significa que é uma referencia
                    // logo, devemos converter o tipo de retorno e buscar os dados no banco
                    if (type.isAnnotationPresent(Entity.class)) {

                        // Armazena o objeto que deve ser convertido
                        Object obj = n.invoke(origin[i]);
                        // Armazena o id do objeto que deve ser convertivo
                        int msId = (int) (obj.getClass().getMethod("getId").invoke(obj));

                        // Busca o objeto no Mongo:
                        // typeTable.Get() => Retorna o tipo na versao Mongo
                        // indexTable.GetMongoIndex() => Retorna o id na versao Mongo
                        Object set = db.GetFromMongo(typeTable.Get(type), indexTable.GetMongoIndex(type, msId));

                        if (set == null)
                            throw new RuntimeException(String.format("Referencia não encontrada: %s", typeTable.Get(type).toString()));

                        // Busca o metodo na classe de destino
                        Method destMethod = d.getClass().getMethod(destMethodName, typeTable.Get(type));
                        // Invoca o metodo passando o objeto buscado como parametro
                        destMethod.invoke(d, set);


                    } else {
                        // Busca o metodo na classe de destino
                        Method destMethod = d.getClass().getMethod(destMethodName, type);
                        // Invoca o metodo passando o resultado do metodo set como parametro
                        destMethod.invoke(d, n.invoke(origin[i]));
                    }

                }
            }
            // Adiciona o objeto criado na lista
            destination.add(d);
        }
        return destination;
    }

}
