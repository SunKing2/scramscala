class GameRun {

  private val g = ScrambleHelper

  // start of state data:
  var nGuesses = 0
  var score = 0
  var longestWord = ""
  var gameIsOn: Boolean = true
  val letters: String = "CHELATIONSESARIN".toLowerCase
  // end of state data

  def checkLongest(word: String): Unit =
    if word.length > longestWord.length then
      longestWord = word

  def showScoreboard: String =
    val longestScore = g.scoreWord(longestWord)
    score += g.bonus(longestWord)
    gameIsOn = false
    g.scoreboard(score, longestWord, longestScore)

}

class ScrambleController {
  private val g = ScrambleHelper
  g.bigDict = g.urlToList
  private var r:GameRun = GameRun()


  def restartGame(): String =
    r = GameRun()
    g.startMessage + g.formatLetters(r.letters)

  def processUserInput(userInput: String): String = {
    var sReturn = ""

    if !r.gameIsOn then
      if userInput == "rd" || userInput == "/go" then
        return restartGame()
      return ""  // if game is not on, return (because you don't process guess)

    r.nGuesses += 1

    var iAdd = 0
    try
      iAdd = g.checkAndScore(userInput, r.letters, g.bigDict)
      r.score += iAdd
      sReturn += s"yup, right: $iAdd points!"
    catch {
      case e: Throwable => sReturn += e.getMessage // not in letters // not in dictionary
    }


    r.checkLongest(userInput)

    if r.nGuesses > 4 then sReturn += r.showScoreboard

    sReturn
  }
}