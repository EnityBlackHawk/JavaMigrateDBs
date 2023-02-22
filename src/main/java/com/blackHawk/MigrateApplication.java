package com.blackHawk;


import com.blackHawk.migrate.Annotations.AddAnnotation;
import com.blackHawk.migrate.AutoGen;
import com.blackHawk.migrate.BaseClasses.TesteInterface;
import com.blackHawk.migrate.DBControl;
import com.blackHawk.migrate.models.MSS.Customer;
import com.blackHawk.migrate.models.MSS.Order;
import com.blackHawk.migrate.models.MSS.Orderline;
import com.blackHawk.migrate.models.MSS.Product;
import com.blackHawk.migrate.repositories.Mongo.MgProductRespository;
import com.blackHawk.migrate.services.Mongo.ProductService;

import org.burningwave.core.classes.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Modifier;
import java.util.Locale;

import org.burningwave.core.assembler.ComponentSupplier;

import static org.burningwave.core.assembler.StaticComponentContainer.Constructors;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {ProductService.class, MgProductRespository.class})
public class MigrateApplication {

	public static final boolean MIGRATE = false;
	public static final boolean EMBADED = true;

	public static void main(String[] args) throws ParseException {

		var context = SpringApplication.run(MigrateApplication.class, args);

		DBControl db = context.getBean(DBControl.class);


		// Getting data:
                
        List<Product> lp = db.GetProductMSS();
		List<Customer> lc = db.GetCustomerMSS();
		List<Order> lo = db.GetOrderMSS();
		List<Orderline> lol = db.GetOrderlineMSS();
		// end Getting data;

		if(MIGRATE)
		{

			List<com.blackHawk.migrate.models.Mongo.Product> lpM  	= new ArrayList<>();
			List<com.blackHawk.migrate.models.Mongo.Customer> lcM 	= new ArrayList<>();
			List<com.blackHawk.migrate.models.Mongo.Order> loM 	= new ArrayList<>();
			List<com.blackHawk.migrate.models.Mongo.Orderline> lolM	= new ArrayList<>();


			ModelMapper modelMapper = new ModelMapper();

			for(int i = 0; i < lp.size(); i++)
			{
				lpM.add(modelMapper.map(lp.get(i), com.blackHawk.migrate.models.Mongo.Product.class));
				db.SaveMongo(lpM.get(i));
			}

			for(int i = 0; i < lc.size(); i++)
			{
				lcM.add(modelMapper.map(lc.get(i), com.blackHawk.migrate.models.Mongo.Customer.class));
				db.SaveMongo(lcM.get(i));
			}

			for(int i = 0; i < lo.size(); i++)
			{
				loM.add(modelMapper.map(lo.get(i), com.blackHawk.migrate.models.Mongo.Order.class));
				db.SaveMongo(loM.get(i));
			}

			if(!EMBADED)
				for(int i = 0; i < lol.size(); i++)
				{
					lolM.add(modelMapper.map(lol.get(i), com.blackHawk.migrate.models.Mongo.Orderline.class));
					db.SaveMongo(lolM.get(i));
				}
		}
		else {
			var generadedObject = AutoGen.Generate("AutoOrder", "com.blackHawk.migrate.auto", TesteInterface.class);
			var met = generadedObject.getClass().getDeclaredMethods();

			System.out.print("METHODS: \n");
			for(var o : met)
			{
				System.out.print(o.getReturnType().toString() + " " + o.getName() + "(");
				for(var p : o.getParameterTypes())
				{
					System.out.print(p.getName() + ", ");
				}
				System.out.print(") \n");
			}
			System.out.print("FIELDS: \n");

			var fields = generadedObject.getClass().getDeclaredFields();
			for(var f : fields)
			{
				System.out.print(f.getType() + " " + f.getName() + "-> ");
				for(var a : f.getDeclaredAnnotations())
					System.out.print(a + ", ");
				System.out.print("\n");
			}

			System.out.print("TESTS: \n");
			try {
				var m = generadedObject.getClass().getDeclaredMethod("setId", int.class);
				m.invoke(generadedObject, 10);

				System.out.print(generadedObject.getClass().getMethod("getId").invoke(generadedObject));

				var OAnno = Order.class.getDeclaredField("id").getDeclaredAnnotations();
				for(var a : OAnno)
				{
					System.out.print(a.toString() + "\n");
				}

			} catch (NoSuchMethodException | NoSuchFieldException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}

		}
                       
                        
		
	}

}
