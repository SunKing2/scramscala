import org.scalatest._
import flatspec._
import matchers._

class BonusSpec extends AnyFlatSpec with should.Matchers {

  private val game = ScrambleHelper()

  private def assertWordMakesBonus(wordToCheck: String, score: Int) = {
    game.bonus(wordToCheck) should be(score)
  }
  it should "score 0 when user enters a word of length 1" in {
    assertWordMakesBonus("a", 0)
  }

  it should "score 0 when user enters a word of length 2" in {
    assertWordMakesBonus("at", 0)
  }

  it should "score 0 when user enters a word of length 4" in {
    assertWordMakesBonus("heat", 0)
  }

  it should "score 2500 when user enters a word of length 14" in {
    assertWordMakesBonus("theatricalness", 2500)
  }

  it should "score 3000 when user enters a word of length 15" in {
    assertWordMakesBonus("procrastination", 3000)
  }

  it should "score 4000 when user enters a word of length 16" in {
    assertWordMakesBonus("procrastinations", 4000)
  }
}