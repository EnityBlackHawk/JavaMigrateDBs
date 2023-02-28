package com.blackHawk;


import com.blackHawk.migrate.AutoGen;
import com.blackHawk.migrate.BaseClasses.AutoClass;
import com.blackHawk.migrate.BaseClasses.OrderInterface;
import com.blackHawk.migrate.DBControl;
import com.blackHawk.migrate.models.MSS.Customer;
import com.blackHawk.migrate.models.MSS.Order;
import com.blackHawk.migrate.models.MSS.Orderline;
import com.blackHawk.migrate.models.MSS.Product;
import com.blackHawk.migrate.repositories.Mongo.MgProductRespository;
import com.blackHawk.migrate.services.Mongo.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {ProductService.class, MgProductRespository.class})
public class MigrateApplication {

	public static final boolean MIGRATE = true;
	public static final boolean REFERENCED = true;
	public static final boolean TESTE1 = false;
	public static final boolean TESTE2 = true;


	public static void main(String[] args) throws Throwable {

		HashMap<String, Boolean> hm = new HashMap<>();

		hm.put("refOrderline", REFERENCED);

		var pair = AutoGen.Generate("AutoOrder", "com.blackHawk.migrate.auto", OrderInterface.class, hm);

		Class<?> generadedClass = pair.getFirst();
		Object generadedObject = pair.getSecond();


		var context = SpringApplication.run(MigrateApplication.class, args);

		DBControl db = context.getBean(DBControl.class);


		if(MIGRATE)
		{

			// Getting data:
			List<Product> lp = db.GetProductMSS();
			List<Customer> lc = db.GetCustomerMSS();
			List<Order> lo = db.GetOrderMSS();
			List<Orderline> lol = db.GetOrderlineMSS();
			// end Getting data;


			List<com.blackHawk.migrate.models.Mongo.Product> lpM  	= new ArrayList<>();
			List<com.blackHawk.migrate.models.Mongo.Customer> lcM 	= new ArrayList<>();
			List<Object> loM 	= new ArrayList<>();
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
				loM.add(modelMapper.map(lo.get(i), generadedClass));
				db.SaveMongo((AutoClass) loM.get(i));
			}

			if(REFERENCED)
				for(int i = 0; i < lol.size(); i++)
				{
					lolM.add(modelMapper.map(lol.get(i), com.blackHawk.migrate.models.Mongo.Orderline.class));
					db.SaveMongo(lolM.get(i));
				}
		}
		else if(TESTE1)
		{

		}
		else if(TESTE2){



			var met = generadedObject.getClass().getDeclaredMethods();

			System.out.print("\nANNOTATION: \n");
			System.out.print("\t");
			System.out.print(generadedClass.getAnnotations()[0].toString() + "\n");


			System.out.print("\nMETHODS: \n");
			for(var o : met)
			{
				System.out.print("\t");
				System.out.print(o.getReturnType().toString() + " " + o.getName() + "(");
				for(var p : o.getParameterTypes())
				{
					System.out.print(p.getName() + ", ");
				}
				System.out.print(") \n");
			}
			System.out.print("\nFIELDS: \n");

			var fields = generadedObject.getClass().getDeclaredFields();
			for(var f : fields)
			{
				System.out.print("\t");
				System.out.print(f.getType() + " " + f.getName() + "-> ");
				for(var a : f.getDeclaredAnnotations())
					System.out.print(a + ", ");
				System.out.print("\n");
			}

			System.out.print("\nTESTS: \n");


		}
                       
                        
		
	}

}
