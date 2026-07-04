package Class;
import java.time.LocalDateTime;
import java.util.List;

import DTO.UserRequest;
import DTO.UserResponse;
import enums.Role;

public class User {
  private long id;
  private String username;
  private String email;
  private String password;
  private Role role;
  private LocalDateTime createdAt;

  List<User> userList = null;



  public User(List<User> userList) {
    this.userList = userList;
  }

  
  /**
   * User Register Method
   * @param id
   * @param username
   * @param email
   * @param role
   * @return
   */
  public boolean register(long id, String username, String email, String password, Role role) {
    try {
      this.id = id;
      this.username = username;
      this.email = email;
      this.password = password;
      this.role = role;
      this.createdAt = LocalDateTime.now();
    } catch (Exception e) {
      return false;
    }

    return true;
  }



  /**
   * User Login Method
   * @param email
   * @param password
   * @return
   */
  public boolean login (String email, String password) {
    User user = null;

    for(User u : userList) {
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



  // Get User by Id 
  private User getById(long userId) {
    User user = null;
    for(User u : userList) {
      if(u.getId() == userId) {
        user = u;
        break;
      }
    }

    return user;
  }



  /**
   * Gives User profile
   * @param userId
   * @return
   */
  public UserResponse getProfile(long userId) {
    User user = getById(userId);
    if(user == null)
      return null;

    UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt());
    return userResponse;
  }



  // Admin method
  public boolean updateProfile(long userid, UserRequest req) {
    try {
      User user = getById(userid);
      user.setPassword(req.getPassword());
      user.setUsername(req.getUsername());
      user.setRole(req.getRole());
    } catch (Exception e) {
      return false;
    }

    return true;

  }


  // Admin method
  public boolean deleteAccount(long userId) {
    try {
      User user = getById(userId);
      userList.remove(user);
    } catch (Exception e) {
      return false;
    }

    return true;
  }



  


  // ================== Getters and Setters ================== //

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

  public void setId(long id) {
    this.id = id;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public void setRole(Role role) {
    this.role = role;
  }

}
