package models;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import utils.DateParser;

@Entity
public class User extends Model {

  @Id
  public String name;
  public String screen_name;
  public String image_url;

  public User(String screenName) {
  	  this.screen_name = screenName;
  	  this.name = screenName;
  }

  public User(String name, String screen_name, String image_url) {
  	  this.name = name;
  	  this.screen_name = screen_name;
  	  this.image_url = image_url;
  }

  public static Finder<String, User> find = new Finder<String, User> (
  		String.class, User.class
  	);
}
