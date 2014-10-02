package wallet
import org.springframework.boot.SpringApplication

object WalletWebApplication {

  def main(args: Array[String]) {
    SpringApplication.run(classOf[WalletConfig]);
  }
}