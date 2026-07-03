package Class;
import java.time.LocalDateTime;
import java.util.List;

import enums.Role;

public class User {
  private long id;
  private String username;
  private String email;
  private String password;
  private Role role;
  private LocalDateTime createdAt;

  
  /**
   * User Register Method
   * @param id
   * @param username
   * @param email
   * @param role
   * @return
   */
  public boolean register(long id, String username, String email, Role role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.role = role;
    this.createdAt = LocalDateTime.now();

    return true;
  }



  /**
   * User Login Method
   * @param users
   * @param email
   * @param password
   * @return
   */
  public boolean login (List<User> users, String email, String password) {
    User user = null;

    for(User u : users) {
      if(u.getEmail().equals(email)) {
        user = u;
      }
    }

    if(user == null) {
      return false;
      // System.out.println("Invalid email id");
    }

    if(user.getPassword().equals(password)) {
      return true;
    } else {
      return false;
    }
  }



  // ================== Getters ================== //

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public Role getRole() {
    return role;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public String getPassword() {
    return password;
  }

}
