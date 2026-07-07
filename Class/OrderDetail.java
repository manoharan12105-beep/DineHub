package Class;

import java.time.LocalDateTime;

public class OrderDetail {
  private Long orderDetailId;
  private Long orderId;
  private int quantity;
  private double price;
  private Long foodId;
  private Long customerId;
  private LocalDateTime createdAt;
  
  public OrderDetail(Long orderDetailId, Long orderId, int quantity, double price, Long foodId, Long customerId) {
    this.orderDetailId = orderDetailId;
    this.orderId = orderId;
    this.quantity = quantity;
    this.price = price;
    this.foodId = foodId;
    this.customerId = customerId;
    this.createdAt = LocalDateTime.now();
  }
  
  public Long getOrderDetailId() {
    return orderDetailId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPrice() {
    return price;
  }

  public Long getFoodId() {
    return foodId;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "OrderDetail{"+ "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", quantity=" + quantity + ", price=" + price + ", foodId=" + foodId + ", customerId=" + customerId + ", createdAt=" + createdAt + '}';
  }
}
