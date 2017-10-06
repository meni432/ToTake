trait Operator
{
  def execute(operands : Seq[Double]) : Seq[Double]
}

trait ArithmeticOperator extends Operator{
  def arithmeticOperator(op: ((Double, Double) => Double), acc: Double, operands: Seq[Double]): Double = {
      if (operands.nonEmpty) arithmeticOperator(op, op(acc, operands.head), operands.tail)
      else acc
  }
}

class PlusOperator extends ArithmeticOperator {
  override def execute(operands: Seq[Double]): Seq[Double] = {
    val plusArithmeticOP = (a: Double, b: Double) => a + b
    List(arithmeticOperator(plusArithmeticOP, 0, operands))
  }
}

class MinusOperator extends ArithmeticOperator {
  override def execute(operands: Seq[Double]): Seq[Double] = {
    val minusArithmeticOP = (a: Double, b: Double) => a - b
    List(arithmeticOperator(minusArithmeticOP, 0, operands))
  }
}

class MultiplicationOperator extends ArithmeticOperator {
  override def execute(operands: Seq[Double]): Seq[Double] = {
    val multiplicationArithmeticOP = (a: Double, b: Double) => a * b
    List(arithmeticOperator(multiplicationArithmeticOP, 1, operands))
  }
}

class PowerOperator extends Operator {
  override def execute(operands: Seq[Double]): Seq[Double] = {
    val a = operands(0)
    val n = operands(1)
    List(Math.pow(a, n))
  }
}

class FactorialOperator extends ArithmeticOperator {
  override def execute(operands: Seq[Double]): Seq[Double] = {
    val multiplicationArithmeticOP = (a: Double, b: Double) => a * b
    if (operands.length == 1) {
      val n = operands.head
      val factRange: List[Int] = List.range(1, 10)
      val fractionalDouble: List[Double] = factRange.map(x => x.toDouble)
      List(arithmeticOperator(multiplicationArithmeticOP, 1, fractionalDouble))
    }
    else throw new CalculatorException
  }
}

class GCDOperator extends Operator {
  override def execute(operands: Seq[Double]): Seq[Double] = {
    if (operands.length == 2) {
      val n: Int = operands(0).toInt
      val m: Int = operands(1).toInt
      def gcd(n: Int, m: Int): Int = {
        if (m % n == 0) n
        gcd(n, m % n)
      }
      List(gcd(n, m))
    }
    else throw new CalculatorException
  }
}

class OddOperator extends Operator {
  override def execute(operands: Seq[Double]): Seq[Double] = {
    if (operands.length > 1) {
      val n: Int = operands(0).toInt
      val m: Int = operands(1).toInt
      def gcd(n: Int, m: Int): Int = {
        if (m % n == 0) n
        gcd(n, m % n)
      }
      List(gcd(n, m))
    }
    else throw new CalculatorException
  }
}


