package Utils

import CardDeck.Suit

import scala.collection.mutable.ListBuffer

object TypeAlias {
  type Deck = ListBuffer[Suit with Product]
  type CardInDeck = Suit with Product
  type ListOfPlayers = ListBuffer[Player]
}
