package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("name1", "lastname1", "1@mail.com", new Car("lada", 12)));
      userService.add(new User("name2", "lastname2", "2@mail.com", new Car("niva", 23)));
      userService.add(new User("name3", "lastname3", "3@mail.com", new Car("bmw", 34)));
      userService.add(new User("name4", "lastname4", "4@mail.com", new Car("kia", 45)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("model = "+user.getUserCar().getModel());
         System.out.println("series = "+user.getUserCar().getSeries());
         System.out.println();
      }

      List<User> userCar = userService.getByModel("kia", 45);
      for (User user : userCar) {
         System.out.println("Машиной " + user.getUserCar().getModel() + " " + user.getUserCar().getSeries() + " владеет пользователь:");
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
      }

      context.close();
   }
   }

