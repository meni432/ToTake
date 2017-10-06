import java.io.{FileNotFoundException, FileReader, IOException}
import java.net.{MalformedURLException, URL}

object Main{
  def main(args: Array[String]): Unit = {

    try {
      val f = new FileReader("input.txt")
      // Use and close file
    } catch {
      case ex: IOException =>
      case ex: FileNotFoundException =>
    }


    implicit def intToRational(x: Int): Rational = new Rational(x)
    val r = new Rational(1/2)
    println(2 * r)

    val filesHere = (new java.io.File(".")).listFiles

    for (file <- filesHere if file.getName.endsWith(".sbt"))
      println(file)

    def grep(pattern: String) =
      for {
        file <- filesHere
        if file.getName.endsWith(pattern)
      } { println(file) }

    grep(".sbt")

    val n = 2
    val half =
      if (n % 2 == 0)
        n / 2
      else
        throw new RuntimeException("n must be even")


    def urlFor(path: String) =
      try {
        new URL(path)
      } catch {
        case e: MalformedURLException =>
          new URL("http://www.scala-lang.org")
      }

    println(urlFor("london"))
    println(urlFor("http://www.ynet.co.il"))

    val f = (_:Int) + (_:Int)
    println(f(1, 2))

    val someNumbers = List(-11, -10, -5, 0, 5, 10)
    someNumbers.foreach((x: Int) => println(x))
    someNumbers.foreach(x => println(x))

    someNumbers.foreach(println)

  }
}
