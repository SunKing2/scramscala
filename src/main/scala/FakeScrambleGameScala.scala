class FakeScrambleGameScala {
  val startMessage = """ Get ready for a round of TSCRAM!
                       |
                       | The letters are:
                       |""".stripMargin
  var nGuesses = 0
  var score = 0
  val letters = "CHELATIONSESARIN".toLowerCase
  val g = Game()
  var longestWord = ""

  //https://www.cross-tables.com/download/twl18.txt
  def urlToList = {
    import scala.io.Source
    val myList = Source.fromURL("https://www.cross-tables.com/download/twl18.txt").getLines().toList.map(_.toLowerCase)
    println(s"len is ${myList.length}")
    val cAA = myList.contains("aa")
    print(s"contains aa $cAA")
    val cAAH = myList.contains("aah")
    print(s"contains aah $cAAH")
    val cZymurgy = myList.contains("zymurgy")
    print(s"contains zymurgy $cZymurgy")
    val cZZZ = myList.contains("zz")
    print(s"contains zzz $cZZZ")
    myList
  }

  def restartGame(): String = {
    g.bigDict = urlToList
    nGuesses = 0
    score = 0
    longestWord = ""
    startMessage +
      g.formatLetters(letters)
  }

  def checkLongest(word: String): Unit = {
    if word.length > longestWord.length then
      longestWord = word
  }

  def guess(guess: String): String = {
    var sReturn = ""
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