package Class;

import java.util.List;

public class Admin {
  private long adminId;
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
}
