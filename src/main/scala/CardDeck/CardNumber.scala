package CardDeck


sealed abstract class CardNumber(val value:Int)
object CardNumber{
  case object Two extends CardNumber(2)
  case object Three extends CardNumber(3)
  case object Four extends CardNumber(4)
  case object Five extends CardNumber(5)
  case object Six extends CardNumber(6)
  case object Seven extends CardNumber(7)
  case object Eight extends CardNumber(8)
  case object Nine extends CardNumber(9)
  case object Ten extends CardNumber(10)
  case object Jack extends CardNumber(10)
  case object Queen extends CardNumber(10)
  case object King extends CardNumber(10)
  case object Ace extends CardNumber(11)
}

object t {
  def main(args: Array[String]): Unit = {
    val cNum = CardNumber
    println(cNum.Ten.value)
  }
}