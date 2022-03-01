import org.scalatest._
import flatspec._
import matchers._

class DictionaryFromLettersSpec extends AnyFlatSpec with should.Matchers {

  private val game = ScrambleHelper
  private val letters:String = "CHELATIONSESARIN".toLowerCase

  private val myDictionary = game.dictionaryFromLetters(letters)

  private def isInLetterDictionary(word: String) =
    game.isWordInDictionary(word, myDictionary)

  "DictionaryFromLetters" should "accept chelations" in {
    isInLetterDictionary("chelations") should be(true)
  }

  it should "accept aa" in {
    isInLetterDictionary("aa") should be(true)
  }

  it should "reject ccccc" in {
    isInLetterDictionary("cccc") should be(false)
  }

  it should "reject cow" in {
    isInLetterDictionary("cow") should be(false)
  }

  it should "reject pig" in {
    isInLetterDictionary("pig") should be(false)
  }

  it should "reject aaa" in {
    isInLetterDictionary("aaa") should be(false)
  }

  it should "accept asset" in {
    isInLetterDictionary("asset") should be(true)
  }

  it should "accept hen" in {
    isInLetterDictionary("hen") should be(true)
  }

  it should "accept horse" in {
    isInLetterDictionary("horse") should be(true)
  }

}