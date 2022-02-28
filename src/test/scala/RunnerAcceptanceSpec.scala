import org.scalatest._
import flatspec._
import matchers._

class RunnerAcceptanceSpec extends AnyFlatSpec with should.Matchers {

  private val ctr = ScrambleController()

  private def assertGuessesProduceOutput(expected: String, guesses: String*) = {
    var sReturn = ""

    sReturn += ctr.restartGame() + "\n"
    for guess <- guesses do
      sReturn += ctr.processUserInput(guess) + "\n"
    sReturn  should be(expected)
  }

  "Game Runner" should "produce good output" in {
    val guesses = Seq("eat", "ate", "eats", "seat", "chaste", "tea")
    val expected =
      """ Get ready for a round of TSCRAM!
        |
        | The letters are:
        |    C  H  E  L
        |
        |    A  T  I  O
        |
        |    N  S  E  S
        |
        |    A  R  I  N
        |
        |
        |yup, right: 4 points!
        |yup, right: 4 points!
        |yup, right: 6 points!
        |yup, right: 6 points!
        |yup, right: 15 points!
        |
        |  !! Time's up !!
        |
        | SCORE:
        |
        |   SunKing2     :  135 (*CHASTE for 15 points!)
        |
        |""".stripMargin
    assertGuessesProduceOutput(expected, guesses*)
  }

}