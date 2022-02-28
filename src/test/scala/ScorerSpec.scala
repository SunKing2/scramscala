import org.scalatest._
import flatspec._
import matchers._

class ScorerSpec extends AnyFlatSpec with should.Matchers {

  private val game = ScrambleHelper()

  private def assertWordIncreasesScore(wordToCheck: String, score: Int) = {
    game.scoreWord(wordToCheck) should be(score)
  }

  "Game Scorer" should "score 140 when user enters chelations" in {
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

  it should "score 6 when user enters a word of length 4" in {
    assertWordIncreasesScore("heat", 6)
  }

  it should "score 550 when user enters a word of length 14" in {
    assertWordIncreasesScore("theatricalness", 550)
  }

  it should "score 800 when user enters a word of length 15" in {
    assertWordIncreasesScore("procrastination", 800)
  }

  it should "score 1200 when user enters a word of length 16" in {
    assertWordIncreasesScore("procrastinations", 1200)
  }

  it should "throw ArithmeticException if dividing by zero" in {
    a [ArithmeticException] should be thrownBy {
      val oopsie = 10/0
    }
  }
}