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
import DTO.UserRequest;
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
    
    while(true) {
      System.out.println();
      System.out.println("===================================");
      System.out.println("           Customer Menu           ");
      System.out.println("===================================");
      System.out.println("1.Account Settings"); // update, delete.
      System.out.println("2. View Food List");
      System.out.println("3. Place Order");
      System.out.println("4. Cancel Order");
      System.out.println("5. View Order histroy");
      System.out.println("6. Favourites");      // View, add, remove.
      System.out.println("7. Logout");
      System.out.print("Enter choice :");

      int choice = 0;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Enter valid choice...");
        continue;
      }


      switch(choice) {
        
        // Customer Account Settings
        case 1 : {
          System.out.println();
          System.out.println("===================================");
          System.out.println("          Account Setting          ");
          System.out.println("===================================");
          System.out.println("1. Update Account");
          System.out.println("2. Delete Account");
          System.out.print("Enter a choice :");

          int choice1 = 0;
          try {
            choice1 = Integer.parseInt(scanner.nextLine());
          } catch (Exception e) {
            System.out.println("Enter valid choice...");
            continue;
          }

          switch(choice1) {
            //  Update Customer Details
            case 1 : {
              try{
                long userId = loggedIn.getId();
                Customer customer = null;

                for(Customer c : customerList) {
                  if(c.getUserId() == userId) {
                    customer = c;
                    break;
                  }
                }
                System.out.println("===== Enter Update Details =====");
                System.out.println("Enter name :");
                String name = scanner.nextLine();
                System.out.println("Enter password :");
                String pass = scanner.nextLine();
                System.out.println("Enter age :");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter Contact No :");
                String contactNo = scanner.nextLine();
                System.out.println("Enter Gender(M/F/O) :");
                String gender = scanner.nextLine();
                System.out.println("Enter Address :");
                String address = scanner.nextLine();

                customer.setName(name);
                customer.setAge(age);
                customer.setContactNo(contactNo);
                customer.setGender(gender);
                customer.setAddress(address);

                User user = null;

                for(User u : userList) {
                  if(u.getId() == userId) {
                    user = u;
                    break;
                  }
                }

                user.setUsername(name);
                user.setPassword(pass);

                System.out.println("Account updated Successfully");
              } catch (Exception e) {
                System.out.println("Something went wrong, Try again some time later");
              }

              break;
            }

            // Delete Account 
            case 2 : {
              long userId = loggedIn.getId();
              User user = null;
              for (User u : userList) {
                if(u.getId() == userId) {
                  user = u;
                  break;
                }
              }

              Customer customer = null;

              for (Customer c : customerList) {
                if(c.getUserId() == userId) {
                  customer = c;
                  break;
                }
              }

              System.out.println("Are you sure to DELETE your account(Y/N) :");
              char confirmation = scanner.nextLine().charAt(0);

              if(confirmation == 'Y') {
                userList.remove(user);
                customerList.remove(customer);

                System.out.println("Account deleted Successfully");
              } else if(confirmation == 'N') {
                System.out.println("Your Account is safe.");
              }

            }
          }
        }
      }
    }
  }


  // ========================================== Logged in Admin methods ========================================== //
  public static void adminMethods() {

    while(true) {
      System.out.println();
      System.out.println("===================================");
      System.out.println("             Admin Menu            ");
      System.out.println("===================================");
      System.out.println("1. View Profile");
      System.out.println("2. Add Admin");
      System.out.println("3. Update User");
      System.out.println("4. Delete User");
      System.out.println("5. Add Restaurant");
      System.out.println("6. Delete Restaurant");
      System.out.println("7. Add Delivery Person");
      System.out.println("8. Remove Delivery Person");
      System.out.println("9. LogOut");
      System.out.println();
      System.out.print("Enter choice :");

      int choice = 0;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Enter valid choice...");
        continue;
      }

      Admin admin = new Admin(userList, adminList);
      User user = new User(userList);
      switch (choice) {
        // View profile
        case 1 : {
          System.out.print("Enter User id :");
          long id = Long.parseLong(scanner.nextLine());
          UserResponse userDetail = user.getProfile(id);
          if (userDetail != null) {
            System.out.println();
            System.out.println("User Id   : " + userDetail.getId());
            System.out.println("Username  : " + userDetail.getUsername());
            System.out.println("Email     : " + userDetail.getEmail());
            System.out.println("Role      : " + userDetail.getRole());
            System.out.println("Created at: " + userDetail.getCreatedAt());
            System.out.println();
          } else {
            System.out.println("No user found with id " + id);
          }


          break;
        }


        // Add admin
        case 2 : {
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

        
          admin.setAdminId(adminId++);
          admin.setName(adName);
          admin.setEmail(adEmail);
          admin.setAge(adAge);
          admin.setContactNo(adContactNo);
          admin.setUserId(userId++);

          if(admin.addAdmin(admin, pass)) {
            System.out.println("Added Successful");
          } else {
            System.out.println("Something went wrong...");
          }

          break;
        }

        // Update User
        case 3 : {
          System.out.println();
          System.out.print("Enter User id to Update :");
          long id = Long.parseLong(scanner.nextLine());
          System.out.print("Enter Username : ");
          String name = scanner.nextLine();
          System.out.println("Enter Password : ");
          String pass = scanner.nextLine();
          
          if(user.updateProfile(id, new UserRequest(name, pass, null))) {
            System.out.println("User profile with User Id " + id + " has been Successfully updated");
          } else {
            System.out.println("Something went wrong...");
          }
           
          break;
        }

        // Delete user 
        case 4 : {
          System.out.println();
          System.out.print("Enter User id to Delete : ");
          long id = Long.parseLong(scanner.nextLine());

          if(user.deleteAccount(id)) {
            System.out.println("User with User Id " + id + " has been deleted");
          } else {
            System.out.println("Something went wrong...");
          }

          break;
        }

        // Add Restaurant
        case 5 : {

          System.out.println();
          System.out.print("Enter Manager name : ");
          String name = scanner.nextLine();
          System.out.print("Enter Manager age: ");
          int age = Integer.parseInt(scanner.nextLine());
          System.out.print("Enter Manager gender(M/F/O): ");
          String gender = scanner.nextLine();
          System.out.print("Enter Manager contactNo: ");
          String contactNo = scanner.nextLine();
          System.out.print("Enter Manager email : ");
          String email = scanner.nextLine();
          System.out.print("Enter Manager password : ");
          String password = scanner.nextLine();

          RestaurantManager  restaurantManager = new RestaurantManager(managerList,userList, managerId++, name, age, gender, contactNo);
          restaurantManager.create(restaurantManager, userId++, email, password);

          System.out.print("Enter Restaurant name : ");
          String restaurantname = scanner.nextLine();
          System.out.print("Enter Owner name : ");
          String owner = scanner.nextLine();
          Restaurant restaurant = new Restaurant(restaurantList, managerList, restaurantId++, restaurantname, owner, restaurantManager.getManagerId());
          System.out.println(restaurant.create(restaurant));
          break;
        }

        // Delete Restaurant
        case 6 : {
        System.out.println();
        System.out.print("Enter Restaurant id to delete : ");
        long id = Long.parseLong(scanner.nextLine());
        Restaurant restaurant = new Restaurant(restaurantList, managerList);
        System.out.println(restaurant.delete(id));
        break;
        }

        // Add Delivery Person
        case 7 : {
          System.out.println();
          System.out.print("Enter Delivery Person name : ");
          String name = scanner.nextLine();
          System.out.print("Enter Phone No : ");
          String phone = scanner.nextLine();
          System.out.print("Enter Email : ");
          String email = scanner.nextLine();
          System.out.print("Enter Age : ");
          int age = Integer.parseInt(scanner.nextLine());
          System.out.print("Enter Gender : ");
          String gender = scanner.nextLine();
          DeliveryPerson deliveryPerson = new DeliveryPerson(deliveryPersonList, deliveryPersonId++, name, phone, email,age, gender);
          deliveryPerson.create(deliveryPerson);

          break;
        }

        // Remove Delivery Person
     case 8 : {
        System.out.println();
        System.out.print("Enter Delivery Person id to remove : ");
        long id = Long.parseLong(scanner.nextLine());

        DeliveryPerson deliveryPerson = new DeliveryPerson(deliveryPersonList);
        System.out.println(deliveryPerson.delete(id));
        break;
}

        
        

        case 9 : {
          loggedIn = null;
          System.out.println("Logged out Successfully");
          break;
        }

        default : {
          System.out.println("Enter Valid Choice");
          break;
        }

      }


    }
  }


  // ============================================ Logged in RestaurantManager Methods ============================================ //
  public static void restaurantManagerMethods () {
    System.out.println("place holder (role.RestaurantManager)" );
  }


  // ============================================   Logged in DeliveryPerson Methods  ============================================ //
  public static void deliveryPersonMethods () {
    System.out.println("place holder (role.DeliveryPerson)" );
  }
  
// ========================================================= Main Method ========================================================= //

  public static void main(String[] args) {
    Main mainObj = new Main();

    System.out.println("===================================");
    System.out.println("   Welcome to DineHub Food System  ");
    System.out.println("===================================");

    while (true) {
      System.out.println(userList.toString());
      System.out.println(adminList.toString());
      System.out.println(customerList.toString());

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

          if (user.register(userId++, customerId++, username, email, password, Role.CUSTOMER, customerList)) {
            System.out.println();
            System.out.println("Registered [Name : " + username + ", Role : CUSTOMER]" );
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
