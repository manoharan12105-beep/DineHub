package Class;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private Long restaurantId;
    private String name;
    private String owner;
    private LocalDateTime startedAt;
    private Long restaurantManagerId; 

    List<Restaurant> restaurantList = new ArrayList<>();
    List<Food> foodList = new ArrayList<>();
    List<Orders> orderList = new ArrayList<>();

    Restaurant( Long restaurantId,String name,String owner, Long restaurantManagerId){
        this.restaurantId=restaurantId;
        this.name=name;
        this.owner=owner;
        this.startedAt = LocalDateTime.now();
        this.restaurantManagerId=restaurantManagerId;

    }

    public String create(Restaurant restaurant){
       restaurantList.add(restaurant);
       return "Restaurant as created";
    }

    public Restaurant getbyId(long id){
        for(Restaurant resturant: restaurantList){
            if(resturant.restaurantId==id){
                return resturant;
            }
        }
        return null;
    }


   public String update(Restaurant restaurant) {
    for (int i=0; i<restaurantList.size(); i++) {
        if (restaurantList.get(i).restaurantId == restaurant.restaurantId) {
            restaurantList.set(i, restaurant);
            return "updated";
        }
    }
    return "Not found";
}

    public  String delete(long id){
         for(Restaurant restaurant: restaurantList){
            if(restaurant.restaurantId==id){
                restaurantList.remove(id);
                return "deleted"; 
            }
        }
        return "not found";
    }


     public void getMenu( ){
       for(Food food:foodList){
        System.out.print(food);
       }
    }

    public void getOrders( ){
       for(Orders order:orderList){
        System.out.print(order);
       }
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public Long getRestaurantManagerId() {
        return restaurantManagerId;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public List<Orders> getOrderList() {
        return orderList;
    }
}