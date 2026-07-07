package Class;

import java.util.ArrayList;
import java.util.List;

import enums.Role;

public class RestaurantManager {
  private Long managerId;
  private String name;
  private int age;
  private String gender;
  private String contactNo;
  private Long userId; 

    List<Food> foodList = new ArrayList<>();
    List<RestaurantManager> managerList;
    List<User> userList;
    
   public  RestaurantManager( List<RestaurantManager> managerList, List<User> userList){
    this.managerList = managerList;
    this.userList = userList;
}

    public RestaurantManager(List<RestaurantManager> managerList, List<User> userList, Long managerId, String name,int age,String gender, String contactNo){
    this.managerList = managerList;
    this.userList = userList;
    this.managerId=managerId;
    this.name=name;
    this.age=age;
    this.gender=gender;
    this.contactNo=contactNo;
}


    public String create(RestaurantManager restaurantManager, long userId, String email, String password){  
        restaurantManager.userId=userId;

        User user=new User(userList);
        user.setId(userId);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.RESTAURANT_MANAGER);
        userList.add(user);
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

    public Long getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public List<RestaurantManager> getManagerList() {
        return managerList;
    }

    public List<User> getUserList() {
        return userList;
    }
}