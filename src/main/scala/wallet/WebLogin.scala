package wallet
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.ArrayList
import scala.beans.BeanProperty
import scala.collection.mutable.HashMap
import org.hibernate.validator.constraints.NotEmpty

object WebLogin {

  var uniqueId: Long = 10000;
  def getUniqueId = {
    uniqueId += 1; uniqueId
  }
}

class WebLogin {

  @BeanProperty
  var login_id: String = "l-"+WebLogin.getUniqueId
  @BeanProperty
  @JsonProperty("url")
  @NotEmpty
  var url: String = ""
  @BeanProperty
  @JsonProperty("login")
  @NotEmpty
  var login: String = ""
  @BeanProperty
  @JsonProperty("password")
  @NotEmpty
  var password: String = ""

}