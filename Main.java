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

   /*
    City Map:
      1 -> road 
      0 -> building
      r1, r2, r3, ... -> Restaurant Location
      c1, c2, c3, ... -> Customer Location
      d1, d2, d3, ... -> Delivery person Location
  */
 static int n = 60;
  static String[][] cityMap = new String[n][n];


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

    generateMap();
  }


  public Main(int[] coords, String name) {
    try {
      updateMap(coords[0], coords[1], name);
    } catch (Exception e) {
      System.out.println("Check Coords");
    }
  }


  // Generate default cityMap
  public static void generateMap() {
    // 1 -> road 
    // 0 -> building
    // # -> border
    // r1, r2, r3, ... -> Restaurant Location
    // c1, c2, c3, ... -> Customer Location
    // d1, d2, d3, ... -> Delivery person Location
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if(i == 0 || i == n -1  || j == 0 || j == n - 1) {
          cityMap[i][j] = "#";
        } else {
          if((i + 1) % 15 == 0 || (j + 1) % 3 == 0) {
            cityMap[i][j] = "1";
          } else {
            cityMap[i][j] = "0";
          }
        }
      }
    }
  
  }


  // Displays the City map
  public static void displayCityMap() {
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        System.out.print(cityMap[i][j] + " ");
      }
      System.out.println();
    }
  }



  // Update City Map
  public static boolean updateMap(int i, int j, String locationDetail) {
    if(i > 99 || j > 99 || locationDetail.length() < 2) {
      return false;
    }

    if(cityMap[i][j].equals("1") ) {
      System.out.println("Enter a Valid Location");
      return false;
    }

    String s = locationDetail.toUpperCase();
    char c = s.charAt(0);

    if(!(c == 'C' || c == 'R' || c == 'D')) {
      System.out.println("Invalid Location Detail");
      return false;
    }

    if(!cityMap[i][j].equals("0")) {
      System.out.println("There is already a Building there");
      return false;
    }

    cityMap[i][j] = s;

    return true;
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
                System.out.print("Enter Address (x, y) coordinates in the city map:");
                int[] address =  {Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine())};

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
              System.out.println("Are you sure to DELETE your account(Y/N) :");
              char confirmation = scanner.nextLine().charAt(0);

              if(confirmation == 'Y') {
                try {
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

                userList.remove(user);
                customerList.remove(customer);

                System.out.println("Account deleted Successfully");
                } catch (Exception e) {
                  System.out.println("Something went  wrong...");
                }
              } else if(confirmation == 'N') {
                System.out.println("Your Account is safe.");
              } else {
                System.out.println("Retry with Valid choice");
              }

              break;
            }
          }
          break;
        }
        
        // View food list 
        case 2 : {
          displayFoodMenu();
          break;
        }

        // Place Order 
        case 3 : {
          handlePlaceOrder();
          break;
        }

        // Cancel Order
        case 4 : {
          handleCancelOrder();
          break;
        }

        // View order history
        case 5 : {
          displayOrderHistory();
          break;
        }

        // Favorites
        case 6 : {
          handleFavoritesMenu();
          break;
        }

        // Logout
        case 7 : {
          loggedIn = null;
          System.out.println("Logged out Successfully");
          return;
        }

        default : {
          System.out.println("Enter valid choice...");
          break;
        }
      }
    }
  }

  private static Customer getLoggedInCustomer() {
    if (loggedIn == null) {
      return null;
    }

    for (Customer customer : customerList) {
      if (customer.getUserId() == loggedIn.getId()) {
        return customer;
      }
    }

    return null;
  }

  private static List<Food> collectAllFoods() {
    List<Food> allFoods = new ArrayList<>();
    for (RestaurantManager manager : managerList) {
      allFoods.addAll(manager.getFoodList());
    }
    return allFoods;
  }

  private static Food findFoodById(Long foodId) {
    for (Food foodItem : collectAllFoods()) {
      if (foodItem.getFoodId() != null && foodItem.getFoodId().equals(foodId)) {
        return foodItem;
      }
    }
    return null;
  }

  private static Orders findOrderById(Long orderId) {
    for (Orders order : orderList) {
      if (order.getOrderId() != null && order.getOrderId().equals(orderId)) {
        return order;
      }
    }
    return null;
  }

  private static Favorite findFavoriteById(Long favId) {
    for (Favorite favorite : favoriteList) {
      if (favorite.getFavId() != null && favorite.getFavId().equals(favId)) {
        return favorite;
      }
    }
    return null;
  }

  private static void displayFoodMenu() {
    System.out.println();
    System.out.println("===================================");
    System.out.println("             FOOD  MENU            ");
    System.out.println("===================================");

    List<Food> foods = collectAllFoods();
    if (foods.isEmpty()) {
      System.out.println("No food items available right now.");
      return;
    }

    for (Food foodItem : foods) {
      System.out.println();
      System.out.println("Food Id          : " + foodItem.getFoodId());
      System.out.println("Food Name        : " + foodItem.getName());
      System.out.println("Food Category    : " + foodItem.getCategory());
      System.out.println("Food description : " + foodItem.getDescription());
      System.out.println("Food price       : " + foodItem.getPrice());
      System.out.println("Preparation Time : " + foodItem.getTimetoprepareMinute() + " Minutes");
      String available = foodItem.isAvailability() ? "Available" : "Not Available";
      System.out.println("Food Availability: " + available);
    }
    System.out.println();
  }

  private static void handlePlaceOrder() {
    Customer customer = getLoggedInCustomer();
    if (customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    List<Food> foods = collectAllFoods();
    if (foods.isEmpty()) {
      System.out.println("No food items available to order.");
      return;
    }

    displayFoodMenu();

    try {
      System.out.print("Enter Food Id : ");
      Long foodId = Long.parseLong(scanner.nextLine());
      Food foodItem = findFoodById(foodId);

      if (foodItem == null) {
        System.out.println("Food not found.");
        return;
      }

      if (!foodItem.isAvailability()) {
        System.out.println("Selected food is not available.");
        return;
      }

      System.out.print("Enter Quantity : ");
      int quantity = Integer.parseInt(scanner.nextLine());
      if (quantity <= 0) {
        System.out.println("Quantity must be greater than zero.");
        return;
      }

      Orders order = new Orders(orderId++, foodItem.getPrice(), enums.Status.PENDING, quantity);
      orderList.add(order);
      Orders.getOrderList().add(order);

      OrderDetail detail = new OrderDetail(
          orderDetailId++,
          order.getOrderId(),
          quantity,
          foodItem.getPrice(),
          foodItem.getFoodId(),
          customer.getId());
      orderDetailList.add(detail);

      System.out.println("Order placed successfully.");
      System.out.println("Order Id   : " + order.getOrderId());
      System.out.println("Food Name  : " + foodItem.getName());
      System.out.println("Quantity   : " + quantity);
      System.out.println("Total Cost : " + order.getTotalAmount());
    } catch (Exception e) {
      System.out.println("Something went wrong while placing the order.");
    }
  }

  private static void handleCancelOrder() {
    Customer customer = getLoggedInCustomer();
    if (customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    if (orderList.isEmpty()) {
      System.out.println("No orders found.");
      return;
    }

    try {
      System.out.print("Enter Order Id to cancel : ");
      Long orderIdToCancel = Long.parseLong(scanner.nextLine());
      Orders order = findOrderById(orderIdToCancel);

      if (order == null) {
        System.out.println("Order not found.");
        return;
      }

      order.setOrderStatus(enums.Status.CANCELLED);
      orderList.remove(order);
      Orders.getOrderList().remove(order);

      for (int i = 0; i < orderDetailList.size(); i++) {
        OrderDetail detail = orderDetailList.get(i);
        if (detail.getOrderId().equals(orderIdToCancel) && detail.getCustomerId().equals(customer.getId())) {
          orderDetailList.remove(i);
          break;
        }
      }

      System.out.println("Order cancelled successfully.");
    } catch (Exception e) {
      System.out.println("Invalid order id.");
    }
  }

  private static void displayOrderHistory() {
    Customer customer = getLoggedInCustomer();
    if (customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    System.out.println();
    System.out.println("===================================");
    System.out.println("           ORDER HISTORY           ");
    System.out.println("===================================");

    boolean found = false;
    for (OrderDetail detail : orderDetailList) {
      if (!detail.getCustomerId().equals(customer.getId())) {
        continue;
      }

      Food foodItem = findFoodById(detail.getFoodId());
      Orders order = findOrderById(detail.getOrderId());

      System.out.println();
      System.out.println("Order Detail Id : " + detail.getOrderDetailId());
      System.out.println("Order Id        : " + detail.getOrderId());
      System.out.println("Food Id         : " + detail.getFoodId());
      System.out.println("Food Name       : " + (foodItem != null ? foodItem.getName() : "Unknown"));
      System.out.println("Quantity        : " + detail.getQuantity());
      System.out.println("Price           : " + detail.getPrice());
      System.out.println("Status          : " + (order != null ? order.getOrderStatus() : "Unknown"));
      System.out.println("Ordered At      : " + detail.getCreatedAt());
      found = true;
    }

    if (!found) {
      System.out.println("No order history found.");
    }
  }

  private static void handleFavoritesMenu() {
    Customer customer = getLoggedInCustomer();
    if (customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    while (true) {
      System.out.println();
      System.out.println("===================================");
      System.out.println("           FAVORITES MENU          ");
      System.out.println("===================================");
      System.out.println("1. View Favourites");
      System.out.println("2. Add Favourite");
      System.out.println("3. Remove Favourite");
      System.out.println("4. Back");
      System.out.print("Enter choice : ");

      int choice = 0;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Enter valid choice...");
        continue;
      }

      if (choice == 4) {
        return;
      }

      switch (choice) {
        case 1 : {
          boolean found = false;
          for(Favorite favorite : favoriteList){
            if(!favorite.getCustomerId().equals(customer.getId()))
              continue;

            System.out.println();
            System.out.println("Favourite Id : " + favorite.getFavId());
            System.out.println("Food Id      : " + favorite.getFoodId());
            System.out.println("Note         : " + favorite.getNote());
            System.out.println("Added At     : " + favorite.getAddAt());
            found = true;
          }

          if (!found) 
            System.out.println("No favourites found.");


          break;
        }

        case 2 : {
          try {
            displayFoodMenu();
            System.out.print("Enter Food Id to add to favourites : ");
            Long foodId = Long.parseLong(scanner.nextLine());
            Food foodItem = findFoodById(foodId);

            if(foodItem == null) {
              System.out.println("Food not found.");
              break;
            }

            System.out.print("Enter note : ");
            String note = scanner.nextLine();
            Favorite favorite = new Favorite(favoriteId++, customer.getId(), foodId, note.isBlank() ? foodItem.getName() : note);
            favoriteList.add(favorite);
            Favorite.getFavoriteList().add(favorite);
            System.out.println("Favourite added successfully.");
          } catch (Exception e) {
            System.out.println("Could not add favourite.");
          }
          break;
        }

        case 3 : {
          try {
            System.out.print("Enter Favourite Id to remove : ");
            Long favId = Long.parseLong(scanner.nextLine());
            Favorite favorite = findFavoriteById(favId);

            if(favorite == null || !favorite.getCustomerId().equals(customer.getId())) {
              System.out.println("Favourite not found.");
              break;
            }

            favoriteList.remove(favorite);
            Favorite.getFavoriteList().remove(favorite);
            System.out.println("Favourite removed successfully.");
          } catch (Exception e) {
            System.out.println("Invalid favourite id.");
          }
          break;
        }

        default : {
          System.out.println("Enter valid choice...");
          break;
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
      User user = new User(userList, cityMap);
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
          return;
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
    System.out.println();
    System.out.println("===================================");
    System.out.println("      Restaurant Manager Menu      ");
    System.out.println("===================================");

    System.out.println("1. Add Food");
    System.out.println("2. Update Food Details");
    System.out.println("3. Delete Food");
    System.out.println("4. ");
    

  }


  // ============================================   Logged in DeliveryPerson Methods  ============================================ //
  public static void deliveryPersonMethods () {
    System.out.println("place holder (role.DeliveryPerson)");
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

      User user = new User(userList, cityMap);

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

          displayCityMap();
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
