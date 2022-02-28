class FakeScrambleGameScala {
  val startMessage: String = """ Get ready for a round of TSCRAM!
                       |
                       | The letters are:
                       |""".stripMargin
  var nGuesses = 0
  var score = 0
  var longestWord = ""
  val letters: String = "CHELATIONSESARIN".toLowerCase
  var gameIsOn: Boolean = false
  val g: Game = Game()
  g.bigDict = urlToList

  //https://www.cross-tables.com/download/twl18.txt
  def urlToList: Seq[String] = {
    import scala.io.Source
    val source = Source.fromURL("https://www.cross-tables.com/download/twl18.txt")
    val myList = source.getLines().toList.map(_.toLowerCase)
    println(s"len is ${myList.length}")
    val cAA = myList.contains("aa")
    print(s"contains aa $cAA")
    val cAAH = myList.contains("aah")
    print(s"contains aah $cAAH")
    val cZymurgy = myList.contains("zymurgy")
    print(s"contains zymurgy $cZymurgy")
    val cZZZ = myList.contains("zz")
    println(s"contains zzz $cZZZ")
    source.close()
    myList
  }

  def restartGame(): String = {
    nGuesses = 0
    score = 0
    longestWord = ""
    gameIsOn = true
    startMessage +
      g.formatLetters(letters)
  }

  def checkLongest(word: String): Unit = {
    if word.length > longestWord.length then
      longestWord = word
  }

  def guess(guess: String): String = {
    var sReturn = ""

    if !gameIsOn then
      if guess == "rd" || guess == "/go" then
        return restartGame()
      return ""  // if game is not on, return (because you don't process guess)

    nGuesses += 1

    var iAdd = 0

    try
      iAdd = g.checkAndScore(guess, letters, g.bigDict)
      score += iAdd
      checkLongest(guess)
      sReturn += s"yup, right: $iAdd points!"
    catch {
      case e: Throwable => sReturn += e.getMessage // not in letters // not in dictionary
    }

    if (nGuesses > 4) {
      val longestBonus = g.bonus(longestWord)
      val longestScore = g.scoreWord(longestWord)
      score += longestBonus
      gameIsOn = false
      sReturn += s"""
                    |
                    |  !! Time's up !!
                    |
                    | SCORE:
                    |
                    |   SunKing2     :  $score (*${longestWord.toUpperCase} for $longestScore points!)""".stripMargin
    }
    sReturn
  }
}