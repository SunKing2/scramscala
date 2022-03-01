import org.scalatest._
import flatspec._
import matchers._

class ValidWordSpec extends AnyFlatSpec with should.Matchers {

  private val game = ScrambleHelper

  private val dictionary = Seq("a", "aa", "asset", "chelations")

  private def isWordInDictionary(word: String) =
    game.isWordInDictionary(word, dictionary)

  "WordVerifier" should "accept chelations" in {
    isWordInDictionary("chelations") should be(true)
  }

  it should "accept aa" in {
    isWordInDictionary("aa") should be(true)
  }

  it should "reject aaa" in {
    isWordInDictionary("aaa") should be(false)
  }

  it should "accept asset" in {
    isWordInDictionary("asset") should be(true)
  }

}