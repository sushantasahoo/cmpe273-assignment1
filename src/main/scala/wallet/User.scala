package wallet

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.ArrayList
import scala.beans.BeanProperty
import scala.collection.mutable.HashMap
import java.util.Date
import org.hibernate.validator.constraints.NotEmpty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

object User {

  var uniqueId: Long = 10000;
  def getUniqueId = {
    uniqueId += 1; uniqueId
  }
}

class User {

  @BeanProperty
  var user_id: String = "u-"+User.getUniqueId
  @BeanProperty
  @JsonProperty("email")
  @NotEmpty
  var email: String = "";
  @BeanProperty
  @JsonProperty("password")
  @NotEmpty
  var password: String = "";
  @JsonProperty("name")
  var name: String = "";
  @BeanProperty
  var created_at: String =  new Date(System.currentTimeMillis()).toString();
 
  var updated_at: Date =  new Date(System.currentTimeMillis());
  
  var cardMap: HashMap[String, IDCard] = new HashMap();
  var loginMap: HashMap[String, WebLogin] = new HashMap();
  var accountMap: HashMap[String, BankAccount] = new HashMap();

}