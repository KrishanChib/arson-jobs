// import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import java.io.ByteArrayOutputStream

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
  // owedDf.show()

  // Replace with your desired webhook url
  // TODO: Update to env variable
  val webhookUrl = ""

    val outCapture = new ByteArrayOutputStream
      Console.withOut(outCapture) {
        // owedDf.show(numRows = 3, truncate = 20, vertical = true)
        owedDf.show()
      }

    val dfString = new String(outCapture.toByteArray)
    
    // triple backticks specify a code block message which uses monospaced font. Neccessary for df columns to have even spacing
    requests.post(webhookUrl, data = Map("content" -> s"```$dfString```"))

}