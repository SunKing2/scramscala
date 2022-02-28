class ScrambleController {
  var nGuesses = 0
  var score = 0
  var longestWord = ""
  val letters: String = "CHELATIONSESARIN".toLowerCase
  var gameIsOn: Boolean = false
  val g: ScrambleHelper = ScrambleHelper()
  g.bigDict = g.urlToList

  def restartGame(): String =
    nGuesses = 0
    score = 0
    longestWord = ""
    gameIsOn = true
    g.startMessage + g.formatLetters(letters)

  def checkLongest(word: String): Unit =
    if word.length > longestWord.length then
      longestWord = word

  def restartIfTypedRd(userInput: String): String =
    if userInput == "rd" || userInput == "/go" then
      return restartGame()
    ""  // if game is not on, return (because you don't process guess)

  def showScoreboard: String =
    val longestScore = g.scoreWord(longestWord)
    score += g.bonus(longestWord)
    gameIsOn = false
    g.scoreboard(score, longestWord, longestScore)

  def validateAndScoreWord(word:String):String =
    var iAdd = 0
    try
      iAdd = g.checkAndScore(word, letters, g.bigDict)
      score += iAdd
      s"yup, right: $iAdd points!"
    catch {
      case e: Throwable => e.getMessage // not in letters // not in dictionary
    }

  def processUserInput(userInput: String): String = {
    var sReturn = ""

    if !gameIsOn then return restartIfTypedRd(userInput)

    nGuesses += 1
    sReturn += validateAndScoreWord(userInput)
    checkLongest(userInput)

    if nGuesses > 4 then sReturn += showScoreboard

    sReturn
  }
}