package wallet

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date
import scala.beans.BeanProperty
import org.hibernate.validator.constraints.NotEmpty

object IDCard {
  var uniqueId: Long = 10000;
  def getUniqueId = {
    uniqueId += 1; uniqueId
  }
}

class IDCard {
  @BeanProperty
  @JsonProperty("cardid")
  var card_id: String = "c-"+IDCard.getUniqueId
  @BeanProperty
  @JsonProperty("card_name")
  @NotEmpty
  var card_name: String = "";
  @BeanProperty
  @JsonProperty("card_number")
  @NotEmpty
  var card_number: String = "";
  @BeanProperty
  @JsonProperty("expiration_date")
  var expiration_date: String = new Date().toString();

}