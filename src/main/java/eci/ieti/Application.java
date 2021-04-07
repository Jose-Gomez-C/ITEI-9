package eci.ieti;

import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;
import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
		MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

		customerRepository.deleteAll();

		customerRepository.save(new Customer("Alice", "Smith"));
		customerRepository.save(new Customer("Bob", "Marley"));
		customerRepository.save(new Customer("Jimmy", "Page"));
		customerRepository.save(new Customer("Freddy", "Mercury"));
		customerRepository.save(new Customer("Michael", "Jackson"));

		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");

		customerRepository.findAll().stream().forEach(System.out::println);
		System.out.println();

		productRepository.deleteAll();

		productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
		productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
		productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
		productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
		productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
		productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
		productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
		productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
		productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));

		System.out.println("Paginated search of products by criteria:");
		System.out.println("-------------------------------");

		productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
				.forEach(System.out::println);

		System.out.println();
		System.out.println();
		System.out.println("Paginación product");
		productRepository.findAll(PageRequest.of(0, 5)).stream().forEach(System.out::println);
		System.out.println();

		User user1 = userRepository.save(new User(1, "prueba1", "prueba1@gmail.com"));
		User user2 = userRepository.save(new User(2, "prueba2", "prueba2@gmail.com"));
		User user3 = userRepository.save(new User(3, "prueba3", "prueba3@gmail.com"));
		User user4 = userRepository.save(new User(4, "prueba4", "prueba4@mail.com"));
		User user5 = userRepository.save(new User(5, "prueba5", "prueba5@mail.com"));
		User user6 = userRepository.save(new User(6, "prueba6", "prueba6@mail.com"));
		User user7 = userRepository.save(new User(7, "prueba7", "prueba7@mail.com"));
		User user8 = userRepository.save(new User(8, "prueba8", "prueba8@mail.com"));
		User user9 = userRepository.save(new User(9, "prueba9", "prueba9@mail.com"));
		User user10 = userRepository.save(new User(10, "prueba10", "prueba9@mail.com"));

		todoRepository.save(
				new Todo("012345678901234567890012345678900123456789", 10, addDays(new Date(), 150), user2, "Ready"));
		todoRepository.save(new Todo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 121, addDays(new Date(), 130), user2,
				"Completed"));
		todoRepository.save(new Todo("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", 100, addDays(new Date(), -43), user4,
				"In Progress"));
		todoRepository.save(new Todo("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC", 1000, addDays(new Date(), -42),
				user3, "In Progress"));
		todoRepository.save(new Todo("asdddddddddddddddasdasdasssssssssdasdasdad", 100, addDays(new Date(), 40), user1,
				"Completed"));
		todoRepository.save(new Todo("cambiar todo", 7, addDays(new Date(), 3), user1, "In Progress"));
		todoRepository.save(new Todo("test", 100, addDays(new Date(), -130), user2, "In Progress"));
		todoRepository.save(new Todo("test1", 1000, addDays(new Date(), 0), user2, "Ready"));
		todoRepository.save(new Todo("test2", 10, addDays(new Date(), -31), user1, "Ready"));
		todoRepository.save(new Todo("magkpnmapkgnmpa", 2, addDays(new Date(), 32), user10, "Completed"));
		todoRepository.save(new Todo("cambiar todo x2", 5, addDays(new Date(), -33), user1, "Ready"));
		todoRepository.save(new Todo("cambiar todo x3", 40, addDays(new Date(), 53), user6, "Completed"));
		todoRepository.save(new Todo("opciones", 10, addDays(new Date(), 312), user6, "Ready"));
		todoRepository.save(new Todo("sadasdas", 3000, addDays(new Date(), 10), user9, "Ready"));
		todoRepository.save(new Todo("pfjaipfjpampamfpamfip", 53, addDays(new Date(), 34), user5, "Completed"));
		todoRepository.save(new Todo("sdasdasd", 5, addDays(new Date(), 21), user9, "Completed"));
		todoRepository.save(new Todo("kasnfln", 4748, addDays(new Date(), -33), user10, "Ready"));
		todoRepository.save(new Todo("gmagma", 485452, addDays(new Date(), -30), user9, "In Progress"));
		todoRepository.save(new Todo("klgmamgolá", 352, addDays(new Date(), -38), user10, "Ready"));
		todoRepository.save(new Todo("ksdfjiopafmpam,fla,sfop", 2, addDays(new Date(), 42), user3, "Ready"));
		todoRepository.save(new Todo("kanfgopangop", 5485, addDays(new Date(), -35), user8, "In Progress"));
		todoRepository.save(new Todo("apksmfgopangpajgpmañgklm", 5421, addDays(new Date(), 31), user7, "Ready"));
		todoRepository.save(new Todo("iajfgoiajfgpsja", 5, addDays(new Date(), -13), user6, "In Progress"));
		todoRepository.save(new Todo("kafnoiasnfoanhfoianh", 2, addDays(new Date(), 13), user5, "Completed"));
		todoRepository.save(new Todo("cjkanfoiasndfiopsanmfpasm", 1, addDays(new Date(), 63), user4, "In Progress"));

		System.out.println("-------Todos where the dueDate has expired------");
		Query query = new Query();
		query.addCriteria(Criteria.where("dueDate").lt(new Date()));
		List<Todo> oldTodos = mongoOperation.find(query, Todo.class);
		oldTodos.forEach(System.out::println);
		
		System.out.println("-------Todos that are assigned to given user and have priority greater equal to 5----");
		Query query2 = new Query();
        query2.addCriteria(Criteria.where("priority").gte(5).andOperator(Criteria.where("responsible").is(user2)));
        List<Todo> todosByResponsible = mongoOperation.find(query2,Todo.class);
        todosByResponsible.forEach(System.out::println);
        
        System.out.println("-------Todos that contains a description with a length greater than 30 characters------------------------");
        Query query4 = new Query();
        query4.addCriteria(Criteria.where("description").regex("^.{30,}"));
        List<Todo> largeDescriptionTodos = mongoOperation.find(query4,Todo.class);
        largeDescriptionTodos.forEach(System.out::println);
	}

	private static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

}