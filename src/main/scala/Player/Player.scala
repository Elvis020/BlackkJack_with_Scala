package Player

import CardDeck.Suit

import scala.collection.mutable.ListBuffer

case class Player(name:String){
  val totalCardsOfPlayer:ListBuffer[Suit] = ListBuffer.empty[Suit]

  override def toString: String = totalCardsOfPlayer.mkString(start=" ➡️ "," , ",end = "")
}
