import java.util.*;

import Class.Admin;
import Class.Customer;
import Class.Delivery;
import Class.DeliveryPerson;
import Class.DeliveryRating;
import Class.Favorite;
import Class.Food;
import Class.OrderDetail;
import Class.Orders;
import Class.Restaurant;
import Class.RestaurantManager;
import Class.RestaurantRating;
import Class.User;
import DTO.UserResponse;
import enums.Role;


public class Main {
  static long userId = 1;
  static long adminId = 1;
  static long managerId = 1;
  static long customerId = 1;
  static long restaurantId = 1;
  static long foodId = 1;
  static long orderId = 1;
  static long deliveryId = 1;
  static long favoriteId = 1;
  static long orderDetailId = 1;
  static long deliveryRating = 1;
  static long deliveryPersonId = 1;
  static long restaurantRating = 1;


  static List<User> userList = new ArrayList<>();
  static List<Admin> adminList = new ArrayList<>();
  static List<Customer> customerList = new ArrayList<>();
  static List<Restaurant> restaurantList = new ArrayList<>();
  static List<RestaurantManager> managerList = new ArrayList<>();

  static List<Food> food = new ArrayList<>();
  static List<Orders> orderList = new ArrayList<>();
  static List<Delivery> deliveryList = new ArrayList<>();
  static List<Favorite> favoriteList = new ArrayList<>();
  static List<OrderDetail> orderDetailList = new ArrayList<>();
  static List<DeliveryRating> deliveryRatingList = new ArrayList<>();
  static List<DeliveryPerson> deliveryPersonList = new ArrayList<>();
  static List<RestaurantRating> restaurantRatingList = new ArrayList<>();

  static UserResponse loggedIn = null;
  static Scanner scanner = new Scanner(System.in);


  // Data seeding (admin)
  public Main() {
    Admin admin = new Admin(userList, adminList);
    admin.setAdminId(adminId++);
    admin.setName("Manoharan");
    admin.setEmail("manoharan@gmail.com");
    admin.setAge(21);
    admin.setContactNo("+91 9876543210");
    admin.setUserId(userId++);
    boolean res = admin.addAdmin(admin, "admin$123");
  }

  
  // ======================================== Logged in Customer methods ======================================== //
  public static void customerMethods() {
    System.out.println("place holder (role.Userr)" );
  }


  // ========================================== Logged in Admin methods ========================================== //
  public static void adminMethods() {

    while(true) {
      System.out.println("1. Add Admin");
      System.out.println("2. Update User");
      System.out.println("3. Delete User");
      System.out.println("4. Add Restaurant");
      System.out.println("5. Delete Restaurant");
      System.out.println("6. Add Delivery Person");
      System.out.println("7. Remove Delivery Person");
      System.out.println("8. View Profile");
      System.out.println("9. LogOut");
      System.out.println();

      int choice = 0;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Enter valid choice...");
        continue;
      }

      switch (choice) {
        // Add admin
        case 1 : {
          System.out.print("Enter Admin name : ");
          String adName = scanner.nextLine();
          System.out.print("Enter Admin email : ");
          String adEmail = scanner.nextLine();
          System.out.print("Enter password");
          String pass = scanner.nextLine();
          System.out.print("Enter Admin age : ");
          int adAge = Integer.parseInt(scanner.nextLine());
          System.out.print("Enter Contact Number : ");
          String adContactNo = scanner.nextLine();

          Admin admin = new Admin(userList, adminList);
          admin.setAdminId(adminId++);
          admin.setName("Manoharan");
          admin.setEmail("manoharan@gmail.com");
          admin.setAge(21);
          admin.setContactNo("+91 9876543210");
          admin.setUserId(userId++);

          if(admin.addAdmin(admin, pass)) {
            System.out.println("Added Successful");
          } else {
            System.out.println("Something went wrong...");
          }

          break;
        }
      }


    }
  }


  // ============================================ Logged in RestaurantManager Methods ============================================ //
  public static void restaurantManagerMethods () {

  }


  // ============================================   Logged in DeliveryPerson Methods  ============================================ //
  public static void deliveryPersonMethods () {

  }
  
// ========================================================= Main Method ========================================================= //

  public static void main(String[] args) {
    Main mainObj = new Main();

    System.out.println("===================================");
    System.out.println("   Welcome to DineHub Food System  ");
    System.out.println("===================================");

    while (true) {
      System.out.println();
      System.out.println("1.Register");
      System.out.println("2.Login");
      System.out.println("3.LogOut");
      System.out.println("4.Exit");
      System.out.println();
      System.out.print("Enter your choice : ");

      int choice1 = 0;
      try {
        choice1 = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Invalid Input");
        continue;
      }

      User user = new User(userList);

      switch (choice1) {
        // Customer Register Case
        case 1 : {
          System.out.print("Enter Username : ");
          String username = scanner.nextLine();

          System.out.print("Enter Email    : ");
          String email = scanner.nextLine();

          System.out.print("Enter Password : ");
          String password = scanner.nextLine();

          if (user.register(userId++, username, email, password, Role.CUSTOMER)) {
            System.out.println();
            System.out.println("Registered [Name : " + username + ", Role : " + user.getRole() + "]" );
          } else {
            System.out.println("Register Failed...");;
          }

          break;
        }

        // Login Case
        case 2 : {
          System.out.print("Enter Email : ");
          String email = scanner.nextLine();
          System.out.print("Enter Password : ");
          String password = scanner.nextLine();

          UserResponse loggedInUser = user.login(email, password);
          if(loggedInUser != null) {
              System.out.println("Login Successful");
              loggedIn = loggedInUser;

              Role currentRole = loggedIn.getRole();
              if(currentRole == Role.CUSTOMER) {
                customerMethods();
              } else if(currentRole == Role.ADMIN) {
                adminMethods();
              } else if(currentRole == Role.RESTAURANT_MANAGER) {
                restaurantManagerMethods();
              } else if(currentRole == Role.DELIVERY_PERSON) {
                deliveryPersonMethods();
              }
          } else {
              System.out.println("Invalid credentials");
          }

          break;
        }

        // Logout Method
        case 3 : {
          loggedIn = null;
          System.out.println("Logged out Successfully");
          break;
        }

        case 4 : {
          System.out.println("===================================");
          System.out.println("    Thank you for using DineHub    ");
          System.out.println("===================================");
          return;
        }
        default: {
          System.out.println("Invalid choice");
          break;
        }
      }

    }
  }
}