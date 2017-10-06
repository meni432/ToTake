
import scala.concurrent._
import scala.util.{Failure, Success}

// the following is equivalent to `implicit val ec = ExecutionContext.global`

class CalculatorException extends Exception

object Calculator
{
  implicit def defaultExecutionContext = scala.concurrent.ExecutionContext.global

  def calculate(operator: String, operands : Seq[Double]) : Future[Seq[Double]] =
  {
    Future {
      val op = operator match {
        case "+" => if (operands.length == 2) new PlusOperator else throw new CalculatorException
        case "-" => if (operands.length == 2) new MinusOperator else throw new CalculatorException
        case "*" => if (operands.length == 2) new MultiplicationOperator else throw new CalculatorException
        case "^" => if (operands.length == 2) new PowerOperator else throw new CalculatorException
      }
      op.execute(operands)
    }
  }

  def main(args: Array[String]): Unit = {
    println("hello main")
    Calculator.calculate("+", List(1, 2)) onComplete {
      case Success(x) => println(x)
      case Failure(x) => println(x)
    }
    Calculator.calculate("-", List(1, 2)) onComplete println
    Calculator.calculate("*", List(1, 2)) onComplete println
    println("end main")
  }

//  def execute(operator : Operator, operands : Seq[Double]) : Future[Seq[Double]] =
//  {
//    //implement
//  }
}

