package GamePlay

import Actions.Actions.{dealCards, dealWithPlayers, dealer, shuffleCards}
import CardDeck.numberOfPlayers
import Utils.Player
import Utils.TypeAlias.Deck

import scala.collection.mutable.ListBuffer

object Main {
  def main(args: Array[String]): Unit = {

    // Assuming there are 3 players
    val player_1 = Player("Player1")
    val player_2 = Player("Player2")
    val player_3 = Player("Player3")

    // Adding players to the list
    numberOfPlayers.addAll(ListBuffer(player_1, player_2, player_3))

    // Each player is dealt a hand from the top of the deck
    // TODO: Write test to check the number of cards generated
    val cachedShuffledCards = shuffleCards()

    // Dealer shuffles the cards
    dealer(cachedShuffledCards: Deck)

    // First hand dealt to players
    for (player <- numberOfPlayers) dealCards(2, player)

    // Players are dealt with based on the outcome of their cards
    dealWithPlayers()
  }
}