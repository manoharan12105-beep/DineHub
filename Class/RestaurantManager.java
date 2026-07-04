package Class;

import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {
  private Long managerId;
  private String name;
  private int age;
  private String gender;
  private String contactNo;

    List<Food> foodList = new ArrayList<>();
    List<RestaurantManager> managerList = new ArrayList<>();
    
    RestaurantManager(Long managerId, String name,int age,String gender, String contactNo){
    this.managerId=managerId;
    this.name=name;
    this.age=age;
    this.gender=gender;
    this.contactNo=contactNo;
}


    public String create(RestaurantManager restaurantManager){
        managerList.add(restaurantManager);
        return "RestaurantManager added";
    }

    public RestaurantManager getbyId(long id){
        for(RestaurantManager restaurantManager: managerList){
            if(restaurantManager.managerId==id){
                return restaurantManager;
            }
        }
        return null;
    }

    public String update(RestaurantManager restaurantManager) {
    for (int i=0; i<managerList.size(); i++) {
        if (managerList.get(i).managerId == restaurantManager.managerId) {
            managerList.set(i, restaurantManager);
            return "updated";
        }
    }
    return "Not found";

}
    public  String delete(Long id){
        for (int i = 0; i < managerList.size(); i++) {
        if (managerList.get(i).managerId.equals(id)) {
                managerList.remove(i);
                return "deleted";
            }
        }
        return "not found";
    }

    public String addFood(Food food){
        foodList.add(food);
        return "Added Food";
    }

    public String updateFood(Food food) {
    for (int i=0; i<foodList.size(); i++) {
        if (foodList.get(i).foodId.equals(food.foodId)) {
            foodList.set(i, food);
            return "updated";
        }
    }
    return "Not found";
    }


    public  String deleteFood(Long id){
    for (int i = 0; i < foodList.size(); i++) {
            if(foodList.get(i).foodId.equals(id)) {
                foodList.remove(i);
                return "deleted"; 
            }
        }
        return "not found";
    }
}