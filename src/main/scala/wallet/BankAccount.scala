package wallet
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.ArrayList
import scala.beans.BeanProperty
import scala.collection.mutable.HashMap
import org.hibernate.validator.constraints.NotEmpty

object BankAccount {

  var uniqueId: Long = 10000;
  def getUniqueId = {
    uniqueId += 1; uniqueId
  }
}

class BankAccount {
  @BeanProperty
  var ba_id: String = "b-"+BankAccount.getUniqueId
  @BeanProperty
  @JsonProperty("account_name")
  var account_name: String = ""
  @BeanProperty
  @JsonProperty("routing_number")
  @NotEmpty
  var routing_number: String = ""
  @BeanProperty
  @JsonProperty("account_number")
  @NotEmpty
  var account_number: String = ""
}