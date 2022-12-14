package Utils

import CardDeck.Suit
import Player.Player

import scala.collection.mutable.ListBuffer


object TypeAlias {
  type Deck = ListBuffer[Card]
  type Card = Suit with Product
  type ListOfPlayers = ListBuffer[Player]
  type GameInPlay = Unit
  type Message = String
  type Score = Int
  type PlayersWithScores = ListBuffer[(String, Int)]
}
