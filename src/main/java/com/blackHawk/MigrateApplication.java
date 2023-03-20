package com.blackHawk;


import com.blackHawk.migrate.AutoGen;
import com.blackHawk.migrate.BaseClasses.*;
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

import javax.lang.model.element.PackageElement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {ProductService.class, MgProductRespository.class})
public class MigrateApplication {

	public static final boolean MIGRATE = true;
	public static final boolean REFERENCED = false;
	public static final boolean TESTE1 = true;
	public static final boolean TESTE2 = false;


	public static void main(String[] args) throws Throwable {

		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("E:\\cpp\\JavaBuilder\\x64\\Debug\\JavaBuilder.exe");
		int val = p.waitFor();


		HashMap<String, Boolean> hm = new HashMap<>();
		/*
		hm.put("refOrderline", REFERENCED);

		Class<?> externalAutoOrder = Class.forName("com.blackHawk.migrate.External.AutoOrderEx");

		var pair = AutoGen.Generate("AutoOrder", "com.blackHawk.migrate.auto", OrderInterface.class, externalAutoOrder, hm);

		Class<?> autoOrderClass = pair.getFirst();
		//Object _generadedObject = pair.getSecond();

		var pair2 = AutoGen.Generate("AutoCustomer", "com.blackHawk.migrate.auto", CustomerInterface.class, AutoCustomerClass.class, hm);

		Class<?> autoCustomerClass = pair2.getFirst();
		//Object generadedObject = pair2.getSecond();
		*/

		Class<?> externalCustomerClass = Class.forName("com.blackHawk.migrate.External.AutoCustomerEx");

		var pair = AutoGen.Generate("AutoCustomer", "com.blackHawk.migrate.auto", CustomerInterface.class, externalCustomerClass, hm);
		Class<?> autoCustomerClass = pair.getFirst();


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
			List<Object> lcM 	= new ArrayList<>();
			List<Object> loM 	= new ArrayList<>();
			List<com.blackHawk.migrate.models.Mongo.Orderline> lolM	= new ArrayList<>();


			ModelMapper modelMapper = new ModelMapper();

			for(int i = 0; i < lc.size(); i++)
			{
				lcM.add(modelMapper.map(lc.get(i), autoCustomerClass));
				System.out.print(lcM.get(i).getClass().getSuperclass().getName());
			}

//			for(int i = 0; i < lp.size(); i++)
//			{
//				lpM.add(modelMapper.map(lp.get(i), com.blackHawk.migrate.models.Mongo.Product.class));
//				db.SaveMongo(lpM.get(i));
//			}
//
//			for(int i = 0; i < lc.size(); i++)
//			{
//				lcM.add(modelMapper.map(lc.get(i), autoCustomerClass));
//				db.SaveMongo((AutoCustomerClass) lcM.get(i));
//			}
//
//			for(int i = 0; i < lo.size(); i++)
//			{
//				loM.add(modelMapper.map(lo.get(i), autoOrderClass));
//				db.SaveMongo(externalAutoOrder.cast(loM.get(i)));
//			}
//
//			if(REFERENCED)
//				for(int i = 0; i < lol.size(); i++)
//				{
//					lolM.add(modelMapper.map(lol.get(i), com.blackHawk.migrate.models.Mongo.Orderline.class));
//					db.SaveMongo(lolM.get(i));
//				}
		}
		else if(TESTE1)
		{
		}
		else if(TESTE2){

//			var met = generadedObject.getClass().getDeclaredMethods();
//
//			System.out.print("\nClass: " + generadedClass.getName() + "\n");
//
//			System.out.print("\nANNOTATION: \n");
//			System.out.print("\t");
//			System.out.print(generadedClass.getAnnotations()[0].toString() + "\n");
//
//
//			System.out.print("\nMETHODS: \n");
//			for(var o : met)
//			{
//				System.out.print("\t");
//				System.out.print(o.getReturnType().toString() + " " + o.getName() + "(");
//				for(var p : o.getParameterTypes())
//				{
//					System.out.print(p.getName() + ", ");
//				}
//				System.out.print(") \n");
//			}
//			System.out.print("\nFIELDS: \n");
//
//			var fields = generadedObject.getClass().getDeclaredFields();
//			for(var f : fields)
//			{
//				System.out.print("\t");
//				System.out.print(f.getType() + " " + f.getName() + "-> ");
//				for(var a : f.getDeclaredAnnotations())
//					System.out.print(a + ", ");
//				System.out.print("\n");
//			}
//
//			System.out.print("\nTESTS: \n");

		}
                       
                        
		
	}

}
