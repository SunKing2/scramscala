import scala.io.StdIn.readLine

object ScrambleGameRunner {

  private val fsg = ScrambleController()

  @main def main(): Unit = {

    //println(fsg.restartGame())
    for i <- 1 to 6 do
      print("Guess:")
      val word = readLine()

      val sReturn = fsg.processUserInput(word)
      println(sReturn)
  }

}