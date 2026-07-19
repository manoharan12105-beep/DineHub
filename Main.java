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
import enums.Category;
import enums.Role;
import Utils.Cell;
import Utils.PathFinder;


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
  static PathFinder pathFinder = new PathFinder();


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
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
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
      System.out.println("7. Rate Completed Order");
      System.out.println("8. Logout");
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
                for(User u : userList) {
                  if(u.getId() == userId) {
                    user = u;
                    break;
                  }
                }

                Customer customer = null;

                for(Customer c : customerList) {
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

        // Rate Completed Order
        case 7 : {
          handleCustomerRatings();
          break;
        }

        // Logout
        case 8 : {
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
    if(loggedIn == null) {
      return null;
    }

    for(Customer customer : customerList) {
      if(customer.getUserId() == loggedIn.getId()) {
        return customer;
      }
    }

    return null;
  }

  private static List<Food> collectAllFoods() {
    List<Food> allFoods = new ArrayList<>();
    for(RestaurantManager manager : managerList) {
      allFoods.addAll(manager.getFoodList());
    }
    return allFoods;
  }

  private static Food findFoodById(Long foodId) {
    for(Food foodItem : collectAllFoods()) {
      if(foodItem.getFoodId() != null && foodItem.getFoodId().equals(foodId)) {
        return foodItem;
      }
    }
    return null;
  }

  private static Customer findCustomerById(Long customerId) {
    for(Customer customer : customerList) {
      if(customer.getId() == customerId) {
        return customer;
      }
    }
    return null;
  }

  private static OrderDetail findOrderDetailByOrderId(Long orderId) {
    for(OrderDetail detail : orderDetailList) {
      if(detail.getOrderId() != null && detail.getOrderId().equals(orderId)) {
        return detail;
      }
    }
    return null;
  }

  private static Orders findOrderById(Long orderId) {
    for(Orders order : orderList) {
      if(order.getOrderId() != null && order.getOrderId().equals(orderId)) {
        return order;
      }
    }
    return null;
  }

  private static Favorite findFavoriteById(Long favId) {
    for(Favorite favorite : favoriteList) {
      if(favorite.getFavId() != null && favorite.getFavId().equals(favId)) {
        return favorite;
      }
    }
    return null;
  }

  private static Restaurant findRestaurantById(Long restaurantIdValue) {
    for(Restaurant restaurant : restaurantList) {
      if(restaurant.getRestaurantId() != null && restaurant.getRestaurantId().equals(restaurantIdValue)) {
        return restaurant;
      }
    }

    return null;
  }

  private static int calculateEstimatedDeliverySeconds(List<Cell> path) {
    if(path == null || path.size() < 2) {
      return -1;
    }

    int units = path.size() - 1;
    int totalSeconds = 0;
    for(int i = 0; i < units; i++) {
      totalSeconds += 50 + (int) (Math.random() * 11);
    }

    return totalSeconds;
  }

  private static void removeRestaurantFromMap(Restaurant restaurant) {
    if(restaurant == null) {
      return;
    }

    int[] address = restaurant.getAddress();
    if(address == null || address.length != 2) {
      return;
    }

    if(address[0] >= 0 && address[0] < cityMap.length && address[1] >= 0 && address[1] < cityMap[0].length) {
      cityMap[address[0]][address[1]] = "0";
    }
  }

  private static void displayFoodMenu() {
    System.out.println();
    System.out.println("===================================");
    System.out.println("             FOOD  MENU            ");
    System.out.println("===================================");

    List<Food> foods = collectAllFoods();
    if(foods.isEmpty()) {
      System.out.println("No food items available right now.");
      return;
    }

    for(Food foodItem : foods) {
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
    if(customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    List<Food> foods = collectAllFoods();
    if(foods.isEmpty()) {
      System.out.println("No food items available to order.");
      return;
    }

    displayFoodMenu();

    try {
      System.out.print("Enter Food Id : ");
      Long foodId = Long.parseLong(scanner.nextLine());
      Food foodItem = findFoodById(foodId);

      if(foodItem == null) {
        System.out.println("Food not found.");
        return;
      }

      if(!foodItem.isAvailability()) {
        System.out.println("Selected food is not available.");
        return;
      }

      System.out.print("Enter Quantity : ");
      int quantity = Integer.parseInt(scanner.nextLine());
      if(quantity <= 0) {
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
    if(customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    if(orderList.isEmpty()) {
      System.out.println("No orders found.");
      return;
    }

    try {
      System.out.print("Enter Order Id to cancel : ");
      Long orderIdToCancel = Long.parseLong(scanner.nextLine());
      Orders order = findOrderById(orderIdToCancel);

      if(order == null) {
        System.out.println("Order not found.");
        return;
      }

      order.setOrderStatus(enums.Status.CANCELLED);
      orderList.remove(order);
      Orders.getOrderList().remove(order);

      for(int i = 0; i < orderDetailList.size(); i++) {
        OrderDetail detail = orderDetailList.get(i);
        if(detail.getOrderId().equals(orderIdToCancel) && detail.getCustomerId().equals(customer.getId())) {
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
    if(customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    System.out.println();
    System.out.println("===================================");
    System.out.println("           ORDER HISTORY           ");
    System.out.println("===================================");

    boolean found = false;
    for(OrderDetail detail : orderDetailList) {
      if(!detail.getCustomerId().equals(customer.getId())) {
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

    if(!found) {
      System.out.println("No order history found.");
    }
  }

  private static void handleFavoritesMenu() {
    Customer customer = getLoggedInCustomer();
    if(customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    while(true) {
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

      if(choice == 4) {
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

          if(!found) 
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

  private static RestaurantManager getLoggedInRestaurantManager() {
    if(loggedIn == null) {
      return null;
    }

    for(RestaurantManager manager : managerList) {
      if(manager.getUserId() != null && manager.getUserId() == loggedIn.getId()) {
        return manager;
      }
    }

    return null;
  }

  private static Restaurant findRestaurantByManagerId(Long managerId) {
    for(Restaurant restaurant : restaurantList) {
      if(restaurant.getRestaurantManagerId() != null && restaurant.getRestaurantManagerId().equals(managerId)) {
        return restaurant;
      }
    }

    return null;
  }

  private static Restaurant findRestaurantByFoodId(Long foodId) {
    for(RestaurantManager manager : managerList) {
      for(Food foodItem : manager.getFoodList()) {
        if(foodItem.getFoodId() != null && foodItem.getFoodId().equals(foodId)) {
          return findRestaurantByManagerId(manager.getManagerId());
        }
      }
    }

    return null;
  }

  private static Delivery findDeliveryByOrderId(Long orderId) {
    for(Delivery delivery : deliveryList) {
      if(delivery.getOrderId() != null && delivery.getOrderId().equals(orderId)) {
        return delivery;
      }
    }

    return null;
  }

  private static void displayDeliveredOrdersForCustomer(Customer customer) {
    System.out.println();
    System.out.println("===================================");
    System.out.println("        COMPLETED ORDERS          ");
    System.out.println("===================================");

    boolean found = false;
    for(OrderDetail detail : orderDetailList) {
      if(!detail.getCustomerId().equals(customer.getId())) {
        continue;
      }

      Orders order = findOrderById(detail.getOrderId());
      if(order == null || order.getOrderStatus() != enums.Status.DELIVERED) {
        continue;
      }

      Food foodItem = findFoodById(detail.getFoodId());
      Restaurant restaurant = findRestaurantByFoodId(detail.getFoodId());
      Delivery delivery = findDeliveryByOrderId(detail.getOrderId());

      System.out.println();
      System.out.println("Order Id     : " + detail.getOrderId());
      System.out.println("Food Name    : " + (foodItem != null ? foodItem.getName() : "Unknown"));
      System.out.println("Restaurant   : " + (restaurant != null ? restaurant.getName() : "Unknown"));
      System.out.println("Delivery Id  : " + (delivery != null ? delivery.getDeliveryId() : "Unknown"));
      System.out.println("Status       : " + order.getOrderStatus());
      if(delivery != null && delivery.getEstimatedDeliveryMinutes() != null) {
        System.out.println("ETA          : " + delivery.getEstimatedDeliveryMinutes() + " minutes");
      }
      found = true;
    }

    if(!found) {
      System.out.println("No completed orders found.");
    }
  }

  private static void handleCustomerRatings() {
    Customer customer = getLoggedInCustomer();
    if(customer == null) {
      System.out.println("Customer profile not found.");
      return;
    }

    while(true) {
      System.out.println();
      System.out.println("===================================");
      System.out.println("          RATING MENU              ");
      System.out.println("===================================");
      System.out.println("1. Rate Restaurant");
      System.out.println("2. Rate Delivery");
      System.out.println("3. Back");
      System.out.print("Enter choice : ");

      int choice = 0;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Enter valid choice...");
        continue;
      }

      if(choice == 3) {
        return;
      }

      switch (choice) {
        case 1 : {
          try {
            displayDeliveredOrdersForCustomer(customer);
            System.out.print("Enter Order Id : ");
            Long orderIdValue = Long.parseLong(scanner.nextLine());
            Orders order = findOrderById(orderIdValue);
            if(order == null || order.getOrderStatus() != enums.Status.DELIVERED) {
              System.out.println("Order not found or not delivered yet.");
              break;
            }

            OrderDetail detail = null;
            for(OrderDetail od : orderDetailList) {
              if(od.getOrderId().equals(orderIdValue) && od.getCustomerId().equals(customer.getId())) {
                detail = od;
                break;
              }
            }

            if(detail == null) {
              System.out.println("Order not found.");
              break;
            }

            Restaurant restaurant = findRestaurantByFoodId(detail.getFoodId());
            if(restaurant == null) {
              System.out.println("Restaurant not found.");
              break;
            }

            System.out.print("Enter Rating Stars (1 to 5) : ");
            int stars = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Details : ");
            String details = scanner.nextLine();

            RestaurantRating rating = new RestaurantRating(restaurantRatingList, restaurantRating++, customer.getId(), restaurant.getRestaurantId(), orderIdValue, stars, details);
            restaurantRatingList.add(rating);
            System.out.println("Restaurant rated successfully.");
          } catch (Exception e) {
            System.out.println("Could not rate restaurant.");
          }
          break;
        }

        case 2 : {
          try {
            displayDeliveredOrdersForCustomer(customer);
            System.out.print("Enter Order Id : ");
            Long orderIdValue = Long.parseLong(scanner.nextLine());
            Orders order = findOrderById(orderIdValue);
            if(order == null || order.getOrderStatus() != enums.Status.DELIVERED) {
              System.out.println("Order not found or not delivered yet.");
              break;
            }

            Delivery delivery = findDeliveryByOrderId(orderIdValue);
            if(delivery == null || delivery.getStatus() != enums.Status.DELIVERED) {
              System.out.println("Delivery not found or not delivered yet.");
              break;
            }

            System.out.print("Enter Rating Stars (1 to 5) : ");
            int stars = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Details : ");
            String details = scanner.nextLine();

            DeliveryRating rating = new DeliveryRating(deliveryRatingList, deliveryRating++, customer.getId(), orderIdValue, delivery.getDeliveryId(), stars, details);
            deliveryRatingList.add(rating);
            System.out.println("Delivery rated successfully.");
          } catch (Exception e) {
            System.out.println("Could not rate delivery.");
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

  private static DeliveryPerson getLoggedInDeliveryPerson() {
    if(loggedIn == null) {
      return null;
    }

    for(DeliveryPerson deliveryPerson : deliveryPersonList) {
      if(deliveryPerson.getUserId() != null && deliveryPerson.getUserId() == loggedIn.getId()) {
        return deliveryPerson;
      }
    }

    return null;
  }

  private static boolean isDeliveryAssignedToPerson(DeliveryPerson deliveryPerson, Long deliveryIdValue) {
    for(Delivery delivery : deliveryPerson.getDeliveryList()) {
      if(delivery.getDeliveryId() != null && delivery.getDeliveryId().equals(deliveryIdValue)) {
        return true;
      }
    }

    return false;
  }

  private static void displayManagerFoods(RestaurantManager manager) {
    System.out.println();
    System.out.println("===================================");
    System.out.println("            FOOD LIST              ");
    System.out.println("===================================");

    List<Food> foods = manager.getFoodList();
    if(foods.isEmpty()) {
      System.out.println("No food items added yet.");
      return;
    }

    for(Food foodItem : foods) {
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
  }

  private static Delivery findDeliveryById(Long deliveryId) {
    for(Delivery delivery : deliveryList) {
      if(delivery.getDeliveryId() != null && delivery.getDeliveryId().equals(deliveryId)) {
        return delivery;
      }
    }

    return null;
  }

  private static void displayPendingOrdersForDelivery() {
    System.out.println();
    System.out.println("===================================");
    System.out.println("         AVAILABLE ORDERS          ");
    System.out.println("===================================");

    boolean found = false;
    for(Orders order : orderList) {
      if(order.getOrderStatus() == enums.Status.CANCELLED || order.getOrderStatus() == enums.Status.DELIVERED) {
        continue;
      }

      String foodName = "Unknown";
      for(OrderDetail detail : orderDetailList) {
        if(detail.getOrderId().equals(order.getOrderId())) {
          Food foodItem = findFoodById(detail.getFoodId());
          if(foodItem != null) {
            foodName = foodItem.getName();
          }
          break;
        }
      }

      System.out.println();
      System.out.println("Order Id   : " + order.getOrderId());
      System.out.println("Food Name  : " + foodName);
      System.out.println("Quantity   : " + order.getQuantity());
      System.out.println("Status     : " + order.getOrderStatus());
      found = true;
    }

    if(!found) {
      System.out.println("No orders available for delivery.");
    }
  }

  private static void displayAssignedDeliveries(DeliveryPerson deliveryPerson) {
    System.out.println();
    System.out.println("===================================");
    System.out.println("       ASSIGNED DELIVERIES         ");
    System.out.println("===================================");

    List<Delivery> assignedDeliveries = deliveryPerson.getDeliveryList();
    if(assignedDeliveries == null || assignedDeliveries.isEmpty()) {
      System.out.println("No deliveries assigned.");
      return;
    }

    for(Delivery delivery : assignedDeliveries) {
      System.out.println();
      System.out.println("Delivery Id : " + delivery.getDeliveryId());
      System.out.println("Order Id    : " + delivery.getOrderId());
      System.out.println("Status      : " + delivery.getStatus());
      System.out.println("Delivered At: " + delivery.getDeliveredAt());
      if(delivery.getEstimatedDeliveryMinutes() != null) {
        System.out.println("ETA         : " + delivery.getEstimatedDeliveryMinutes() + " minutes");
      }
      if(delivery.getEstimatedDeliverySeconds() != null) {
        System.out.println("ETA Seconds : " + delivery.getEstimatedDeliverySeconds() + " seconds");
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
          if(userDetail != null) {
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
          System.out.print("Enter Restaurant Address (x y): ");
          String[] locationInput = scanner.nextLine().trim().split("\\s+");
          int[] address = {
            Integer.parseInt(locationInput[0]),
            Integer.parseInt(locationInput[1])
          };
          restaurant.setAddress(address);

          String createMessage = restaurant.create(restaurant);
          System.out.println(createMessage);
          if(createMessage.toLowerCase().contains("created")) {
            restaurant.placeOnMap(cityMap, "R" + restaurant.getRestaurantId());
            displayCityMap();
          }
          break;
        }

        // Delete Restaurant
        case 6 : {
          System.out.println();
          System.out.print("Enter Restaurant id to delete : ");
          long id = Long.parseLong(scanner.nextLine());
          Restaurant restaurant = findRestaurantById(id);
          if(restaurant == null) {
            System.out.println("not found");
            break;
          }

          removeRestaurantFromMap(restaurant);
          Restaurant restaurantRepo = new Restaurant(restaurantList, managerList);
          System.out.println(restaurantRepo.delete(id));
          displayCityMap();
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
          System.out.print("Enter Password : ");
          String password = scanner.nextLine();
          System.out.print("Enter Age : ");
          int age = Integer.parseInt(scanner.nextLine());
          System.out.print("Enter Gender : ");
          String gender = scanner.nextLine();
          DeliveryPerson deliveryPerson = new DeliveryPerson(deliveryPersonList, deliveryPersonId++, name, phone, email,age, gender);
          deliveryPerson.create(deliveryPerson, userId++, email, password, userList);

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
    while(true) {
      RestaurantManager manager = getLoggedInRestaurantManager();
      if(manager == null) {
        System.out.println("Restaurant manager profile not found.");
        return;
      }

      System.out.println();
      System.out.println("===================================");
      System.out.println("      Restaurant Manager Menu      ");
      System.out.println("===================================");
      System.out.println("1. Add Food");
      System.out.println("2. Update Food");
      System.out.println("3. Delete Food");
      System.out.println("4. View Food List");
      System.out.println("5. Logout");
      System.out.print("Enter choice : ");

      int choice = 0;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Enter valid choice...");
        continue;
      }

      switch (choice) {
        case 1 : {
          try {
            System.out.print("Enter Food name : ");
            String name = scanner.nextLine();
            System.out.print("Enter Food description : ");
            String description = scanner.nextLine();
            System.out.print("Enter Preparation Time (Minutes) : ");
            int timeToPrepare = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Category (BREAKFAST/LUNCH/DINNER/SNACKS/BEVERAGES/DESSERTS) : ");
            Category category = Category.valueOf(scanner.nextLine().trim().toUpperCase());
            System.out.print("Available (true/false) : ");
            boolean availability = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Enter Price : ");
            double price = Double.parseDouble(scanner.nextLine());

            Food foodItem = new Food(manager.getFoodList());
            if(foodItem.create(foodId++, name, description, timeToPrepare, category, availability, price)) {
              manager.addFood(foodItem);
              System.out.println("Food added successfully.");
            } else {
              System.out.println("Could not add food.");
            }
          } catch (Exception e) {
            System.out.println("Something went wrong while adding food.");
          }
          break;
        }

        case 2 : {
          try {
            displayManagerFoods(manager);
            System.out.println();
            System.out.println("1. Update Full Details");
            System.out.println("2. Update Price");
            System.out.println("3. Update Availability");
            System.out.print("Enter choice : ");
            int updateChoice = Integer.parseInt(scanner.nextLine());

            if(updateChoice == 1) {
              System.out.print("Enter Food Id : ");
              Long id = Long.parseLong(scanner.nextLine());
              System.out.print("Enter Food name : ");
              String name = scanner.nextLine();
              System.out.print("Enter Food description : ");
              String description = scanner.nextLine();
              System.out.print("Enter Preparation Time (Minutes) : ");
              int timeToPrepare = Integer.parseInt(scanner.nextLine());
              System.out.print("Enter Category (BREAKFAST/LUNCH/DINNER/SNACKS/BEVERAGES/DESSERTS) : ");
              Category category = Category.valueOf(scanner.nextLine().trim().toUpperCase());
              System.out.print("Available (true/false) : ");
              boolean availability = Boolean.parseBoolean(scanner.nextLine());
              System.out.print("Enter Price : ");
              double price = Double.parseDouble(scanner.nextLine());

              Food foodItem = new Food(manager.getFoodList());
              if(foodItem.create(id, name, description, timeToPrepare, category, availability, price)) {
                System.out.println(manager.updateFood(foodItem));
              }
            } else if(updateChoice == 2) {
              System.out.print("Enter Food Id : ");
              Long id = Long.parseLong(scanner.nextLine());
              System.out.print("Enter New Price : ");
              Double price = Double.parseDouble(scanner.nextLine());

              Food foodItem = new Food(manager.getFoodList());
              System.out.println(foodItem.updatePrice(price, id));
            } else if(updateChoice == 3) {
              System.out.print("Enter Food Id : ");
              Long id = Long.parseLong(scanner.nextLine());
              System.out.print("Available (true/false) : ");
              boolean availability = Boolean.parseBoolean(scanner.nextLine());

              Food foodItem = new Food(manager.getFoodList());
              System.out.println(foodItem.updateAvailability(availability, id));
            } else {
              System.out.println("Invalid choice");
            }
          } catch (Exception e) {
            System.out.println("Something went wrong while updating food.");
          }
          break;
        }

        case 3 : {
          try {
            displayManagerFoods(manager);
            System.out.print("Enter Food Id to delete : ");
            Long id = Long.parseLong(scanner.nextLine());
            System.out.println(manager.deleteFood(id));
          } catch (Exception e) {
            System.out.println("Something went wrong while deleting food.");
          }
          break;
        }

        case 4 : {
          displayManagerFoods(manager);
          break;
        }

        case 5 : {
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

  
  // ============================================   Logged in DeliveryPerson Methods  ============================================ //
  public static void deliveryPersonMethods () {
    while(true) {
      System.out.println();
      System.out.println("===================================");
      System.out.println("        Delivery Person Menu       ");
      System.out.println("===================================");
      System.out.println("1. View Profile");
      System.out.println("2. View Assigned Deliveries");
      System.out.println("3. Accept Delivery");
      System.out.println("4. Update Delivery Status");
      System.out.println("5. Logout");
      System.out.print("Enter choice : ");

      int choice = 0;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        System.out.println("Enter valid choice...");
        continue;
      }

      DeliveryPerson deliveryPerson = getLoggedInDeliveryPerson();
      if(deliveryPerson == null) {
        System.out.println("Delivery person profile not found.");
        return;
      }

      switch (choice) {
        case 1 : {
          System.out.println();
          System.out.println("Delivery Person Id : " + deliveryPerson.getDeliverpersonId());
          System.out.println("Name               : " + deliveryPerson.getName());
          System.out.println("Email              : " + deliveryPerson.getEmail());
          System.out.println("Phone No           : " + deliveryPerson.getPhoneNo());
          System.out.println("Age                : " + deliveryPerson.getAge());
          System.out.println("Gender             : " + deliveryPerson.getGender());
          System.out.println("Status             : " + deliveryPerson.getStatus());
          System.out.println("Join Date          : " + deliveryPerson.getJoinDate());
          break;
        }

        case 2 : {
          displayAssignedDeliveries(deliveryPerson);
          break;
        }

        case 3 : {
          try {
            displayPendingOrdersForDelivery();
            System.out.print("Enter Order Id to accept : ");
            Long orderIdValue = Long.parseLong(scanner.nextLine());
            Orders order = findOrderById(orderIdValue);

            if(order == null) {
              System.out.println("Order not found.");
              break;
            }

            if(order.getOrderStatus() == enums.Status.CANCELLED || order.getOrderStatus() == enums.Status.DELIVERED) {
              System.out.println("This order cannot be accepted.");
              break;
            }

            if(findDeliveryByOrderId(orderIdValue) != null) {
              System.out.println("This order already has a delivery.");
              break;
            }

            Delivery delivery = new Delivery(deliveryList, deliveryId++, order.getOrderId(), enums.Status.CONFIRMED);
            OrderDetail detail = findOrderDetailByOrderId(orderIdValue);
            Restaurant restaurant = detail != null ? findRestaurantByFoodId(detail.getFoodId()) : null;
            Customer customer = detail != null ? findCustomerById(detail.getCustomerId()) : null;

            if(restaurant != null && customer != null && restaurant.getAddress() != null && customer.getAddress() != null) {
              List<Cell> path = pathFinder.shortestPath(cityMap, restaurant.getAddress(), customer.getAddress());
              int estimatedSeconds = calculateEstimatedDeliverySeconds(path);

              if(estimatedSeconds > 0) {
                int estimatedMinutes = (int) Math.ceil(estimatedSeconds / 60.0);
                delivery.setEstimatedDeliveryMinutes(estimatedMinutes);
                delivery.setEstimatedDeliverySeconds(estimatedSeconds);
                System.out.println("Shortest path found:");
                pathFinder.printPath(path);
                System.out.println("Estimated delivery time: " + estimatedMinutes + " minutes (" + estimatedSeconds + " seconds)");
              } else {
                System.out.println("Could not calculate delivery route.");
              }
            } else {
              System.out.println("Could not calculate delivery route.");
            }

            deliveryList.add(delivery);
            deliveryPerson.acceptDelivery(delivery);
            order.setOrderStatus(enums.Status.CONFIRMED);
            deliveryPerson.setStatus(enums.Status.AVAILABLE);

            System.out.println("Delivery accepted successfully.");
          } catch (Exception e) {
            System.out.println("Something went wrong while accepting delivery.");
          }
          break;
        }

        case 4 : {
          try {
            displayAssignedDeliveries(deliveryPerson);
            System.out.print("Enter Delivery Id : ");
            Long deliveryIdValue = Long.parseLong(scanner.nextLine());
            Delivery delivery = findDeliveryById(deliveryIdValue);

            if(delivery == null) {
              System.out.println("Delivery not found.");
              break;
            }

            System.out.println("1. Out For Delivery");
            System.out.println("2. Delivered");
            System.out.print("Enter new status : ");
            int statusChoice = Integer.parseInt(scanner.nextLine());

            if(!isDeliveryAssignedToPerson(deliveryPerson, deliveryIdValue)) {
              System.out.println("This delivery is not assigned to you.");
              break;
            }

            if(statusChoice == 1) {
              delivery.setStatus(enums.Status.OUT_FOR_DELIVERY);
              deliveryPerson.setStatus(enums.Status.OUT_FOR_DELIVERY);
              Orders order = findOrderById(delivery.getOrderId());
              if(order != null) {
                order.setOrderStatus(enums.Status.OUT_FOR_DELIVERY);
              }
              System.out.println("Delivery status updated.");
            } else if(statusChoice == 2) {
              delivery.setStatus(enums.Status.DELIVERED);
              deliveryPerson.setStatus(enums.Status.AVAILABLE);

              Orders order = findOrderById(delivery.getOrderId());
              if(order != null) {
                order.setOrderStatus(enums.Status.DELIVERED);
              }

              System.out.println("Delivery marked as delivered.");
            } else {
              System.out.println("Invalid status choice.");
            }
          } catch (Exception e) {
            System.out.println("Something went wrong while updating delivery status.");
          }
          break;
        }

        case 5 : {
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
  
// ========================================================= Main Method ========================================================= //

  public static void main(String[] args) {
    Main mainObj = new Main();

    System.out.println("===================================");
    System.out.println("   Welcome to DineHub Food System  ");
    System.out.println("===================================");

    while(true) {
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

          if(user.register(userId++, customerId++, username, email, password, Role.CUSTOMER, customerList)) {
            System.out.println();
            System.out.println("Registered [Name : " + username + ", Role : CUSTOMER]" );
          } else {
            System.out.println("Register Failed...");
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


