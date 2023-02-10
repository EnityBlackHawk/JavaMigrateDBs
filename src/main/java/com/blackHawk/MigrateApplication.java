package com.blackHawk;

import com.blackHawk.migrate.DBControl;
import com.blackHawk.migrate.Transfer;
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

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {ProductService.class, MgProductRespository.class})
public class MigrateApplication {

	public static final boolean MIGRATE = false;

	public static void main(String[] args) throws ParseException {

		var context = SpringApplication.run(MigrateApplication.class, args);

		DBControl db = context.getBean(DBControl.class);
                
                                        List<Product> lp = db.GetProductMSS();
		List<Customer> lc = db.GetCustomerMSS();
		List<Order> lo = db.GetOrderMSS();
		List<Orderline> lol = db.GetOrderlineMSS();


		if(MIGRATE)
		{
                                                             /*
			List<Product> lp = db.GetProductMSS();
			List<Customer> lc = db.GetCustomerMSS();
			List<Order> lo = db.GetOrderMSS();
			List<Orderline> lol = db.GetOrderlineMSS();
                                                            */
			List<com.blackHawk.migrate.models.Mongo.Product> lpM  	= new ArrayList<>();
			List<com.blackHawk.migrate.models.Mongo.Customer> lcM 	= new ArrayList<>();
			List<com.blackHawk.migrate.models.Mongo.Order> loM 	= new ArrayList<>();
			List<com.blackHawk.migrate.models.Mongo.Orderline> lolM	= new ArrayList<>();
                                                         
                                                           /*
                                                            try {
                                                                var field = com.blackHawk.migrate.models.Mongo.Order.class.getField("orderlines");
                                                            } catch (NoSuchFieldException ex) {
                                                                Logger.getLogger(MigrateApplication.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                            */
                                                            
                                                          

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
                                                        
//			for(int i = 0; i < lol.size(); i++)
//			{
//				lolM.add(modelMapper.map(lol.get(i), com.blackHawk.migrate.models.Mongo.Orderline.class));
//				db.SaveMongo(lolM.get(i));
//			}
		}
                    if(!MIGRATE)
                    {
                         
                    }
                       
                        
		
	}

}
