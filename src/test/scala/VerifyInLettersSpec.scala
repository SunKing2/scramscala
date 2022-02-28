import org.scalatest._
import flatspec._
import matchers._

class VerifyInLettersSpec extends AnyFlatSpec with should.Matchers {

  private val game = ScrambleHelper()
  private val letters = "CHELATIONSESARIN".toLowerCase

  private def isInLetters(word: String) =
    game.isWordInLetters(word, letters)

  "LetterVerifier" should "accept chelations" in {
    isInLetters("chelations") should be(true)
  }

  it should "accept aa" in {
    isInLetters("aa") should be(true)
  }

  it should "reject ccccc" in {
    isInLetters("cccc") should be(false)
  }

  it should "reject aaa" in {
    isInLetters("aaa") should be(false)
  }

  it should "accept asset" in {
    isInLetters("asset") should be(true)
  }

  it should "reject assets" in {
    isInLetters("assets") should be(false)
  }

}