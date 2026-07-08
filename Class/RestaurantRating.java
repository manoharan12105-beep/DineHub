package Class;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantRating {
  private Long restaurantRatingId;
  private Long customerId;
  private Long restaurantId;
  private Long orderId;
  private int stars;
  private String details;
  private LocalDateTime dateTime;

  List<RestaurantRating> restaurantRatingList;

  public RestaurantRating(List<RestaurantRating> restaurantRatingList, Long restaurantRatingId, Long customerId, Long restaurantId, Long orderId, int stars, String details){
    this.restaurantRatingList = restaurantRatingList;
    this.restaurantRatingId = restaurantRatingId;
    this.customerId = customerId;
    this.restaurantId = restaurantId;
    this.orderId = orderId;
    this.stars = stars;
    this.details = details;
    this.dateTime = LocalDateTime.now();
  }

  public String create(RestaurantRating restaurantRating){
    restaurantRatingList.add(restaurantRating);
    return "Restaurant rating added";
  }

  public RestaurantRating getbyId(long id){
    for(RestaurantRating restaurantRating: restaurantRatingList){
      if(restaurantRating.restaurantRatingId.equals(id)){
        return restaurantRating;
      }
    }
    return null;
  }

  public String update(RestaurantRating restaurantRating){
    for(int i = 0; i < restaurantRatingList.size(); i++){
      if(restaurantRatingList.get(i).restaurantRatingId.equals(restaurantRating.restaurantRatingId)){
        restaurantRatingList.set(i, restaurantRating);
        return "updated restaurant rating";
      }
    }
    return "Not found";
  }

  public String delete(long id){
    for(int i = 0; i < restaurantRatingList.size(); i++){
      if(restaurantRatingList.get(i).restaurantRatingId.equals(id)){
        restaurantRatingList.remove(i);
        return "deleted";
      }
    }
    return "not found";
  }

  public Long getRestaurantRatingId() {
    return restaurantRatingId;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public Long getRestaurantId() {
    return restaurantId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public int getStars() {
    return stars;
  }

  public String getDetails() {
    return details;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public List<RestaurantRating> getRestaurantRatingList() {
    return restaurantRatingList;
  }
}
