package wallet

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletResponse
import java.util.ArrayList
import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach
import java.util.HashMap
import java.util.Date
import javax.validation.Valid
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.core.Context
import javax.ws.rs.core.Request
import javax.ws.rs.core.EntityTag
import javax.ws.rs.core.Response
import com.sun.jersey.spi.container.CachedEntityContainerRequest
import org.springframework.web.context.request.WebRequest

object UserController {
  var userMap: HashMap[String, User] = new HashMap();
}

@RestController
class UserController() {

  @RequestMapping(value = Array("/api/v1/users"), method = Array(RequestMethod.POST))
  def createUser(@RequestBody @Valid userDetails: User, response: HttpServletResponse) = {

    UserController.userMap.put(userDetails.user_id, userDetails)
    response.setStatus(HttpServletResponse.SC_CREATED)
    UserController.userMap.get(userDetails.user_id)
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}"), method = Array(RequestMethod.GET))
  def viewUser(@PathVariable user_id: String) = {

    UserController.userMap.get(user_id)
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}"), method = Array(RequestMethod.PUT))
  def updateUser(@RequestBody @Valid userDetails: User, response: HttpServletResponse, @PathVariable user_id: String) = {
    UserController.userMap.get(user_id).setEmail(userDetails.email)
    UserController.userMap.get(user_id).setPassword(userDetails.password)
    UserController.userMap.get(user_id).updated_at = new Date(System.currentTimeMillis());
    response.setStatus(HttpServletResponse.SC_CREATED)
    UserController.userMap.get(user_id)
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.POST))
  def createIDCard(@RequestBody @Valid cardDetails: IDCard, response: HttpServletResponse, @PathVariable user_id: String) = {

    var user: User = UserController.userMap.get(user_id)

    user.cardMap.put(cardDetails.card_id, cardDetails)

    response.setStatus(HttpServletResponse.SC_CREATED)
    user.cardMap.get(cardDetails.card_id).get;
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.GET))
  def viewCards(@PathVariable user_id: String) = {
    var user: User = UserController.userMap.get(user_id)

    var cardsList: ArrayList[IDCard] = new ArrayList();
    user.cardMap.foreach {
      keyVal => cardsList.add(keyVal._2)
    }
    cardsList
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards/{card_id}"), method = Array(RequestMethod.DELETE))
  def deleteCards(@PathVariable user_id: String, @PathVariable card_id: String, response: HttpServletResponse) = {
    var user: User = UserController.userMap.get(user_id)
    user.cardMap.remove(card_id)
    response.setStatus(HttpServletResponse.SC_NO_CONTENT)
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.POST))
  def createWebLogin(@RequestBody @Valid loginDetails: WebLogin, response: HttpServletResponse, @PathVariable user_id: String) = {

    var user: User = UserController.userMap.get(user_id)
    user.loginMap.put(loginDetails.login_id, loginDetails)

    response.setStatus(HttpServletResponse.SC_CREATED)
    user.loginMap.get(loginDetails.login_id).get;
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.GET))
  def viewWebLogins(@PathVariable user_id: String) = {
    var user: User = UserController.userMap.get(user_id)

    var loginList: ArrayList[WebLogin] = new ArrayList();
    user.loginMap.foreach {
      keyVal => loginList.add(keyVal._2)
    }
    loginList
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE))
  def deleteWebLogin(@PathVariable user_id: String, @PathVariable login_id: String, response: HttpServletResponse) = {
    var user: User = UserController.userMap.get(user_id)
    user.loginMap.remove(login_id)
    response.setStatus(HttpServletResponse.SC_NO_CONTENT)
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
  def createbankAccount(@RequestBody @Valid accountDetails: BankAccount, response: HttpServletResponse, @PathVariable user_id: String) = {

    var user: User = UserController.userMap.get(user_id)
    user.accountMap.put(accountDetails.ba_id, accountDetails)

    response.setStatus(HttpServletResponse.SC_CREATED)
    user.accountMap.get(accountDetails.ba_id).get;
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
  def viewBankAccounts(@PathVariable user_id: String) = {
    var user: User = UserController.userMap.get(user_id)

    var accountList: ArrayList[BankAccount] = new ArrayList();
    user.accountMap.foreach {
      keyVal => accountList.add(keyVal._2)
    }
    accountList
  }

  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE))
  def deleteBankAccount(@PathVariable user_id: String, @PathVariable ba_id: String, response: HttpServletResponse) = {
    var user: User = UserController.userMap.get(user_id)
    user.accountMap.remove(ba_id)
    response.setStatus(HttpServletResponse.SC_NO_CONTENT)
  }

}
