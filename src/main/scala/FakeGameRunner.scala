import scala.io.StdIn.readLine

object FakeGameRunner {

  private val fsg = FakeScrambleGameScala()

  @main def main(): Unit = {

    //println(fsg.restartGame())
    for i <- 1 to 6 do
      print("Guess:")
      val word = readLine()

      val sReturn = fsg.guess(word)
      println(sReturn)
  }

}