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
  private int[] address = new int[2];
  private long userId;

  List<Customer> customerList = null;
  private String[][] cityMap;
  static Scanner scanner = new Scanner(System.in);

  public Customer(List<Customer> customerList, String[][] cityMap) {
    this.customerList = customerList;
    this.cityMap = cityMap;
  }
  

  /**
   * Create and add Customer
   */
  public boolean create(long customerId, User user) {
    try {
      Customer customer = new Customer(this.customerList, this.cityMap);
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

      System.out.print("Enter Address (x y): ");
      String[] input = scanner.nextLine().trim().split("\\s+");

      int[] address = {
        Integer.parseInt(input[0]),
        Integer.parseInt(input[1])
      };

      customer.setAddress(address);
      String locationName = "C" + customer.getId();
      placeOnMap(customer.getAddress(), locationName);
      this.customerList.add(customer);

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public void placeOnMap(int[] address, String locationName) {
    cityMap[address[0]][address[1]] = locationName;
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


  public int[] getAddress() {
    return address;
  }


  public void setAddress(int[] address) {
    this.address = address;
  }


  public long getUserId() {
    return userId;
  }


  public void setUserId(long userId) {
    this.userId = userId;
  }

}
