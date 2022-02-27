import org.scalatest._
import flatspec._
import matchers._

class CheckAndScoreSpec extends AnyFlatSpec with should.Matchers {

  private val game = Game()
  private val letters = "CHELATIONSESARIN".toLowerCase
  private val dictionary = Seq("a", "at", "teach", "chelations")

  private def assertWordIncreasesScore(wordToCheck: String, score: Int) = {
    game.checkAndScore(wordToCheck, letters, dictionary) should be(score)
  }

  "CheckAndScore" should "score 140 when user enters chelations" in {
    assertWordIncreasesScore("chelations", 140)
  }

  it should "score 9 when user enters teach" in {
    assertWordIncreasesScore("teach", 9)
  }

  it should "score 1 when user enters a word of length 1" in {
    assertWordIncreasesScore("a", 1)
  }

  it should "score 2 when user enters a word of length 2" in {
    assertWordIncreasesScore("at", 2)
  }

  it should "throw Exception:not in dictioanry if check che" in {
    a [Exception] should be thrownBy {
      assertWordIncreasesScore("che", -3)
    }
    the [Exception] thrownBy {
      assertWordIncreasesScore("che", -3)
    } should have message "not in dictionary"
  }

  it should "throw Exception:not in letters if check zuz" in {
    a [Exception] should be thrownBy {
      assertWordIncreasesScore("zuz", -3)
    }
    the [Exception] thrownBy {
      assertWordIncreasesScore("zuz", -3)
    } should have message "not in letters"
  }
}