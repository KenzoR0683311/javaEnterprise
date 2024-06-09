package example.com.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import org.slf4j.*;

import example.com.enums.TodoStatus;

@Entity
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private String comment;
  private Date expiryDate;
  
  private TodoStatus currentStatus = TodoStatus.TODO; 
  

  @Transient
  private final Logger logger = LoggerFactory.getLogger(getClass());
  
  @ManyToOne
  @JoinColumn(name = "user_fk") 
  private User user;
  
  public Todo() {}

  public Todo(String title, String comment, Date expiryDate) {
    this.title = title;
    this.comment = comment;
    this.expiryDate = expiryDate;
  }
  
  public long getId() {
	  return id;
  }
  
  public void setId(long id) {
	  this.id = id;
  }
  
  public String getTitle() {
	  return title;
  }

  public void setTitle(String title) {
	  this.title = title;
  }
  
  public String getComment() {
	  return comment;
  }

  public void setComment(String comment) {
	  this.comment = comment;
  }

  public Date getExpiryDate() {
	  return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
	  this.expiryDate = expiryDate;
  }

  public TodoStatus getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(TodoStatus currentStatus) {
    this.currentStatus = currentStatus;
  }

  public void setUser(User user) {
    this.user = user;
  }
}

