package parser05

object Repl {
  def main(args: Array[String]) = loop

  def loop: Unit = {
    print("» ")
    val line = readLine()
    if (line != null && !line.trim.isEmpty) {
      println(eval(line))
      loop
    }
  }

  def eval(line: String) = Parser05(line) match {
    case Parser05.Success(tree, rest) ⇒
      val result = Evaluator(tree)
      s"$tree → $result"
    case Parser05.Failure(msg, rest) ⇒ msg
    case Parser05.Error(msg, rest)   ⇒ msg
  }

}




