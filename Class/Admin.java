package Class;

import java.util.List;

import enums.Role;

public class Admin {
  private long adminId;
  private long userId;
  private String name;
  private String email;
  private int age;
  private String contactNo;

  List<User> userList = null;
  List<Admin> adminList = null;
  

  public Admin (List<User> userList, List<Admin> adminList) {
    this.userList = userList;
    this.adminList = adminList;
  }

  // NoArgConstructor
  public Admin() {

  }



  // Add Admin
  public boolean addAdmin(Admin ad, String password) {
    try {
      User user = new User(ad.getUserId(), ad.getName(), ad.getEmail(), password, Role.ADMIN);

      userList.add(user);
      adminList.add(ad);
      return true;
    } catch (Exception e) {
      return false;
    }
  }




  

  // ========================================== Getters and Setters ==========================================
  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
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

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
  
}
