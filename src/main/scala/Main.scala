// import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object Main extends App {

  /* TODO:  
    - Find a way to pull down live google sheet
    - Add email, text functionality
    - If doing individual messages add which tournaments the owed dues are for?
    - Consider expanding to more than just dues
  */

  // TODO: Add parameters based on how user wants to receive dues reminders
  // case class parameters (
  //       sendDiscord: Option[Boolean] = None,
  //       sendEmail: Option[Boolean] = None,
  //       sendText: Option[Boolean] = None
  //   )

  val spark = SparkSession
    .builder()
    .appName(s"Dues Ingress")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  val duesDf = spark.read.format("csv").option("header", "true").load("dues.csv")
  // duesDf.show()

  val owedDf = duesDf.filter(col("Total Owed") > 0)
  owedDf.show()

  // Replace with your desired webhook url
  // TODO: Update to env variable
  val webhookUrl = ""

  // TODO: Either find a way to adjust message visibility or send one message for all debtors
  owedDf.collect().foreach { row =>
      val player = row.getAs[String]("Player")
      val amount = row.getAs[String]("Total Owed")
      val message = s"Hi, $player! You currently owe $$$amount. Please send in your payment or talk to Krishan if you need to set up a payment plan."

      requests.post(webhookUrl, data = Map("content" -> message))
    }

}