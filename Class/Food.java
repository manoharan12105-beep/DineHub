package Class;

import java.util.ArrayList;
import java.util.List;

import enums.Category;

public class Food {
  Long foodId;
  private String Name;
  private String description;
  private int timetoprepareMinute;
  private Category category;
  private boolean availability;
  private Double price;


  List<Food> foodList = new ArrayList<>();
  Food(Long foodId,String Name,String description,int timetoprepareMinute,Category category, boolean availability,Double price){
    this .foodId=foodId;
    this.Name=Name;
    this.description=description;
    this.timetoprepareMinute=timetoprepareMinute;
    this.category=category;
    this.availability=availability;
    this.price=price;
  }


    public String create(Food food){
       foodList.add(food);
       return "Food as Added";
    }


    public Food getbyId(long id){
        for(Food food: foodList){
            if(food.foodId==id){
                return food;
            }
        }
        return null;
    }


   public String update(Food food) {
    for (int i=0; i<foodList.size(); i++) {
        if (foodList.get(i).foodId == food.foodId) {
            foodList.set(i, food);
            return "updated food details";
        }
    }
    return "Not found food";
}

    public  String delete(long id){
         for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).foodId.equals(id)) {
                foodList.remove(i);
                return "deleted food";
            }
        }
        return "not found";
    }

    public String updateAvailability(boolean availability,Long foodId){
        for (int i=0; i<foodList.size(); i++) {
        if (foodList.get(i).foodId == foodId) {
           foodList.get(i).availability = availability;
            return "updated Successfully";
        }
    }
     return "not found";
    }

    public String updatePrice(Double price,Long foodId){
        for (int i=0; i<foodList.size(); i++) {
        if (foodList.get(i).foodId == foodId) {
           foodList.get(i).price = price;
            return "updated Successfully";
        }
    }
     return "not found";
    }


    public Long getFoodId() {
        return foodId;
    }


    public String getName() {
        return Name;
    }


    public String getDescription() {
        return description;
    }


    public int getTimetoprepareMinute() {
        return timetoprepareMinute;
    }


    public Category getCategory() {
        return category;
    }


    public boolean isAvailability() {
        return availability;
    }


    public Double getPrice() {
        return price;
    }


    public List<Food> getFoodList() {
        return foodList;
    }





}