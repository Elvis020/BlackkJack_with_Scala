package Utils

import CardDeck.Suit

import scala.collection.mutable.ListBuffer

case class Player(name:String){
  var totalCardsOfPlayer:ListBuffer[Suit] = ListBuffer.empty[Suit]
}
