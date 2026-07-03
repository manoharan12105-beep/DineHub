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


  List<User> userList = new ArrayList<>();
  List<Admin> adminList = new ArrayList<>();
  List<Customer> customerList = new ArrayList<>();
  List<Restaurant> restaurantList = new ArrayList<>();
  List<RestaurantManager> managerList = new ArrayList<>();

  List<Food> food = new ArrayList<>();
  List<Orders> orderList = new ArrayList<>();
  List<Delivery> deliveryList = new ArrayList<>();
  List<Favorite> favoriteList = new ArrayList<>();
  List<OrderDetail> orderDetailList = new ArrayList<>();
  List<DeliveryRating> deliveryRatingList = new ArrayList<>();
  List<DeliveryPerson> deliveryPersonList = new ArrayList<>();
  List<RestaurantRating> restaurantRatingList = new ArrayList<>();

  
  


  public static void main(String[] args) {

  }
}