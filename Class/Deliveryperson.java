package Class;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import enums.Role;
import enums.Status;

public class DeliveryPerson {
    private Long deliverpersonId;
    private Long userId;
    private String name;
    private String phoneNo;
    private String email;
    private int age;
    private String gender;
    private Status status;
    private LocalDateTime joinDate;

    List<DeliveryPerson> deliveryPersonList;
    List<Delivery> deliveryList;

    public DeliveryPerson(List<DeliveryPerson> deliveryPersonList) {
        this.deliveryPersonList = deliveryPersonList;
        this.deliveryList = new ArrayList<>();
    }

    public DeliveryPerson(List<DeliveryPerson> deliveryPersonList, Long deliverpersonId, String name,String phoneNo, String email, int age, String gender){
        this.deliveryPersonList = deliveryPersonList;
        this.deliverpersonId=deliverpersonId;
        this.name=name;
        this.phoneNo=phoneNo;
        this.email=email;
        this.age=age;
        this.gender=gender;
        this.status=Status.AVAILABLE;
        this.joinDate=LocalDateTime.now();
        this.deliveryList = new ArrayList<>();
    }

    public String create(DeliveryPerson deliveryPerson){
       deliveryPersonList.add(deliveryPerson);
       return "Delivery person as Added";
    }

    public String create(DeliveryPerson deliveryPerson, long userId, String email, String password, List<User> userList){
       deliveryPerson.userId = userId;

       User user = new User(userList);
       user.setId(userId);
       user.setUsername(deliveryPerson.getName());
       user.setEmail(email);
       user.setPassword(password);
       user.setRole(Role.DELIVERY_PERSON);

       userList.add(user);
       deliveryPersonList.add(deliveryPerson);
       return "Delivery person as Added";
    }


    public DeliveryPerson getbyId(long id){
        for(DeliveryPerson deliveryPerson: deliveryPersonList){
            if(deliveryPerson.deliverpersonId==id){
                return deliveryPerson;
            }
        }
        return null;
    }


   public String update(DeliveryPerson deliveryPerson) {
    for(int i=0; i<deliveryPersonList.size(); i++) {
        if(deliveryPersonList.get(i).deliverpersonId.equals(deliveryPerson.deliverpersonId)) {
            deliveryPersonList.set(i, deliveryPerson);
            return "updated Delivery person details";
        }
    }
    return "Not found order";
}

    public  String delete(Long id){
        for(int i = 0; i < deliveryPersonList.size(); i++) {
            if(deliveryPersonList.get(i).deliverpersonId.equals(id)) {
                deliveryPersonList.remove(i);
                return "deleted account";
            }
        }
        return "not found";
    }


    public boolean acceptDelivery(Delivery delivery){
      if(deliveryList == null) {
        deliveryList = new ArrayList<>();
      }
      deliveryList.add(delivery);
      return true;
    }

    public boolean updatedDeliveryStatus(Delivery delivery, Status status){
      this.status = status;
      return true;
    }

    public List<Delivery> viewAssignOfDeliveries(){
        return deliveryList;
    }

    public Long getDeliverpersonId() {
        return deliverpersonId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public List<DeliveryPerson> getDeliveryPersonList() {
        return deliveryPersonList;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    
}

