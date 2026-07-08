package Class;

import java.time.LocalDateTime;
import java.util.List;

import enums.Status;

public class Delivery {
  private Long deliveryId;
  private Long orderId;
  private Status status;
  private LocalDateTime deliveredAt;

  List<Delivery> deliveryList;

  public Delivery(List<Delivery> deliveryList, Long deliveryId, Long orderId, Status status){
    this.deliveryList=deliveryList;
    this.deliveryId=deliveryId;
    this.orderId=orderId;
    this.status=status;
    this.deliveredAt=LocalDateTime.now();
  }

  public String create(Delivery delivery){
      deliveryList.add(delivery);
      return "delivery Details as Added";
  }


  public Delivery getbyId(long id){
      for(Delivery delivery: deliveryList){
          if(delivery.deliveryId==id){
              return delivery;
          }
      }
      return null;
  }


  public String update(Delivery delivery) {
  for(int i=0; i<deliveryList.size(); i++) {
      if(deliveryList.get(i).deliveryId == delivery.deliveryId) {
          deliveryList.set(i, delivery);
          return "updated delivery details";
      }
  }
  return "Not found ";
}

  public  String delete(long id){
        for(int i = 0; i < deliveryList.size(); i++) {
          if(deliveryList.get(i).deliveryId.equals(id)) {
              deliveryList.remove(i);
              return "deleted";
          }
      }
      return "not found";
  }


  public boolean markDelivered(){
      return true;
  }

  public boolean updateStatus(){
      return true;
  }

  public Long getDeliveryId() {
      return deliveryId;
  }

  public Long getOrderId() {
      return orderId;
  }

  public Status getStatus() {
      return status;
  }

  public void setStatus(Status status) {
      this.status = status;
  }

  public LocalDateTime getDeliveredAt() {
      return deliveredAt;
  }

  public List<Delivery> getDeliveryList() {
      return deliveryList;
  }




  }

