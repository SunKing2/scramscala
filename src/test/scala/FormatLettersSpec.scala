import org.scalatest._
import flatspec._
import matchers._

class FormatLettersSpec extends AnyFlatSpec with should.Matchers {

  private val game = ScrambleHelper

  private def assertFormttedCorrectly(letters: String, fmt:String) = {
    game.formatLetters(letters) should be(fmt)
  }

  "Format letters" should "show chelationsesarin formatted" in {
    val sC =
      """    C  H  E  L
        |
        |    A  T  I  O
        |
        |    N  S  E  S
        |
        |    A  R  I  N
        |
        |""".stripMargin
    assertFormttedCorrectly("chelationsesarin", sC)
  }

}