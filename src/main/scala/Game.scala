import scala.io.Source.fromFile

class Game:

  private val scoreLookup = Map(
    1 -> 1, 2 -> 2, 3 -> 4, 4 -> 6,
    5 -> 9, 6 -> 15, 7 -> 30, 8 -> 55,
    9 -> 90, 10 -> 140, 11 -> 200, 12 -> 280,
    13 -> 400, 14 -> 550, 15 -> 800, 16 -> 1200
  )

  private val bonusLookup = Map(
    1 -> 0, 2 -> 0, 3 -> 0, 4 -> 0,
    5 -> 0, 6 -> 100, 7 -> 200, 8 -> 300,
    9 -> 500, 10 -> 800, 11 -> 1200, 12 -> 1600,
    13 -> 2000, 14 -> 2500, 15 -> 3000, 16 -> 4000
  )
  var bigDict = Seq("chelations", "aa", "asset", "hen", "horse")

  def scoreWord(word:String): Int = {
    if word == null then return 0
    val len = word.length
    scoreLookup.getOrElse(len, 1)
  }

  def bonus(word:String): Int = {
    if word == null then return 0
    val len = word.length
    bonusLookup.getOrElse(len, 0)
  }

  def formatLetters(letters:String): String = """    C  H  E  L
                                        |
                                        |    A  T  I  O
                                        |
                                        |    N  S  E  S
                                        |
                                        |    A  R  I  N
                                        |
                                        |
                                        |""".stripMargin

  def isWordInLetters(word: String, letters:String): Boolean =
    word.diff(letters).isEmpty

  def isWordInDictionary(word: String, dictionary:Seq[String]): Boolean =
    dictionary.contains(word)

  def dictionaryFromLetters(letters: String): Seq[String] =
    // the commented stuff works, but I replaced it with a map
    // that I don't understand either
    //for(word <- bigDict
    //    if isWordInLetters(word, letters)) yield word
    bigDict.withFilter(word => isWordInLetters(word, letters)).map(word => word)

  def checkAndScore(word: String, letters: String, dictionary:Seq[String]): Int =
    val m = isWordInLetters(word, letters)
    if m then
      val n = isWordInDictionary(word, dictionary)
      if n then
        scoreWord(word)  // return value
      else
        throw new Exception("not in dictionary")
    else
      throw new Exception("not in letters")


