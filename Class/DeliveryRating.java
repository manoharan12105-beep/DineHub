package Class;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryRating {
  private Long deliveryRatingId;
  private Long customerId;
  private Long orderId;
  private Long deliveryId;
  private int stars;
  private String details;
  private LocalDateTime dateTime;

  List<DeliveryRating> deliveryRatingList;

  public  DeliveryRating(List<DeliveryRating> deliveryRatingList, Long deliveryRatingId, Long customerId, Long orderId, Long deliveryId, int stars, String details){
    this.deliveryRatingList = deliveryRatingList;
    this.deliveryRatingId = deliveryRatingId;
    this.customerId = customerId;
    this.orderId = orderId;
    this.deliveryId = deliveryId;
    this.stars = stars;
    this.details = details;
    this.dateTime=LocalDateTime.now();
  }

    public String create(DeliveryRating deliveryRating){
          deliveryRatingList.add(deliveryRating);
          return "Delivery rating added ";
        }


    public DeliveryRating getbyId(long id){
        for(DeliveryRating deliveryRating: deliveryRatingList){
            if(deliveryRating.deliveryRatingId==id){
                return deliveryRating;
            }
        }
        return null;
    }


      public String update(DeliveryRating deliveryRating) {
        for(int i=0; i<deliveryRatingList.size(); i++) {
            if(deliveryRatingList.get(i).deliveryRatingId == deliveryRating.deliveryRatingId) {
                deliveryRatingList.set(i, deliveryRating);
                return "updated delivery rating";
            }
        }
        return "Not found food";
    }

    public  String delete(long id){
        for(int i = 0; i < deliveryRatingList.size(); i++) {
            if(deliveryRatingList.get(i).deliveryRatingId.equals(id)) {
                deliveryRatingList.remove(i);
                return "deleted";
            }
        }
        return "not found";
    }

    public Long getDeliveryRatingId() {
      return deliveryRatingId;
    }

    public Long getCustomerId() {
      return customerId;
    }

    public Long getOrderId() {
      return orderId;
    }

    public Long getDeliveryId() {
      return deliveryId;
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

    public List<DeliveryRating> getDeliveryRatingList() {
      return deliveryRatingList;
    }

}

