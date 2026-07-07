package Class;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Orders {
  private Long orderId;
  private LocalDateTime orderDate;
  private Double price;
  private Status orderStatus;
  private int quantity;
  private Double totalAmount;


  static List<Orders> orderList = new ArrayList<>();
  public Orders(Long orderId, Double price,Status orderStatus,int quantity) { 
        this.orderId=orderId;
        this.orderDate=LocalDateTime.now();
        this.orderStatus=orderStatus;
        this.price=price;
        this.quantity=quantity;
        this.totalAmount=price*quantity;
    }


    public String create(Orders orders){
       orderList.add(orders);
       return "Food as Added";
    }


    public Orders getbyId(long id){
        for(Orders orders: orderList){
            if(orders.orderId==id){
                return orders;
            }
        }
        return null;
    }


   public String update(Orders orders) {
    for (int i=0; i<orderList.size(); i++) {
        if (orderList.get(i).orderId == orders.orderId) {
            orderList.set(i, orders);
            return "updated order details";
        }
    }
    return "Not found order";
}

    public  String delete(Long id){
         for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).orderId.equals(id)) {
                orderList.remove(i);
                return "deleted order";
            }
        }
        
        return "not found";
    }

  public String cancelOrder(Long id){
     for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).orderId.equals(id)) {
                orderList.remove(i);
                return "canceled order";
            }
        }
        return "Not found order";
    }

    public String updateStatus(Status orderStatus,Long id){
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).orderId.equals(id)) {
                orderList.get(i).orderStatus=orderStatus;
                return "Status updated";
            }
        }
        return "Not found";
    }


    public Long getOrderId() {
        return orderId;
    }


    public LocalDateTime getOrderDate() {
        return orderDate;
    }


    public Double getPrice() {
        return price;
    }


    public Status getOrderStatus() {
        return orderStatus;
    }


    public int getQuantity() {
        return quantity;
    }


    public Double getTotalAmount() {
        return totalAmount;
    }


    public static List<Orders> getOrderList() {
        return orderList;
    }


}