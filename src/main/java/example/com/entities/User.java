package example.com.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.util.Collection;

import org.slf4j.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String email;
  private String firstName;
  private String lastName;
  private String password;
  @Transient
  private final Logger logger = LoggerFactory.getLogger(getClass());

  public User() {}

  public User(String email, String firstName, String lastName, String password) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }

  public long getId() {
	  return id;
  }

  public void setId(long id) {
	  this.id = id;
  }

  public String getEmail() {
	  return email;
  }

  public void setEmail(String email) {
	  this.email = email;
  }

  public String getFirstName() {
	  return firstName;
  }

  public void setFirstName(String firstName) {
	  this.firstName = firstName;
  }

  public String getLastName() {
	  return lastName;
  }

  public void setLastName(String lastName) {
	  this.lastName = lastName;
  }

  public String getPassword() {
	  return password;
  }

  public void setPassword(String password) {
	  this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
	  // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getUsername() {
	  // TODO Auto-generated method stub
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
	  // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
	  // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
	  // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
	  // TODO Auto-generated method stub
    return true;
  }
}
