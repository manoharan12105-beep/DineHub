package Class;

import java.util.List;
import java.util.Scanner;

public class Customer {
  private long id;
  private String name;
  private String email;
  private int age;
  private String contactNo;
  private String gender;
  private String address;
  private long userId;

  List<Customer> customerList = null;
  static Scanner scanner = new Scanner(System.in);

  public Customer(List<Customer> customerList) {
    this.customerList = customerList;
  }
  

  /**
   * Create and add Customer
   */
  public boolean create(long customerId, User user, List<Customer> customerList) {
    try {
      Customer customer = new Customer(this.customerList);
      customer.userId = user.getId();
      customer.name = user.getUsername();
      customer.email = user.getEmail();
      customer.id = customerId;
      
      System.out.print("Enter age : ");
      customer.age = Integer.parseInt(scanner.nextLine());

      System.out.print("Enter gender(M/F/O) : ");
      customer.gender = scanner.nextLine();

      System.out.print("Enter Contact No : ");
      customer.contactNo = scanner.nextLine();

      System.out.print("Enter Address :");
      customer.address = scanner.nextLine();

      customerList.add(customer);

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  public long getId() {
    return id;
  }


  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public int getAge() {
    return age;
  }


  public void setAge(int age) {
    this.age = age;
  }


  public String getContactNo() {
    return contactNo;
  }


  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }


  public String getGender() {
    return gender;
  }


  public void setGender(String gender) {
    this.gender = gender;
  }


  public String getAddress() {
    return address;
  }


  public void setAddress(String address) {
    this.address = address;
  }


  public long getUserId() {
    return userId;
  }


  public void setUserId(long userId) {
    this.userId = userId;
  }

}
