package CardDeck

case class Hearts(number:CardNumber)  extends Suit {
  override def cardNumber: CardNumber = number
}
case class Spades(number:CardNumber) extends Suit {
  override def cardNumber: CardNumber = number
}
case class Diamond(number:CardNumber) extends Suit {
  override def cardNumber: CardNumber = number
}
case class Clubs(number:CardNumber) extends Suit {
  override def cardNumber: CardNumber = number

}


