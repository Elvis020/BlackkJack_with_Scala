package GamePlay

import Actions.Actions._
import CardDeck.numberOfPlayers
import Messages.Messages._
import Utils.TypeAlias.Deck

object Main {
  def main(args: Array[String]): Unit = {
    displayWelcomeMessage(welcome)
    requestAndCreatePlayers()

    displayMessage(availablePlayers)
    numberOfPlayers.foreach(println)

    // Each player is dealt a hand from the top of the deck
    println("\n" + shuffleCardsMessage)
    val cachedShuffledCards = shuffleCards()

    // Dealer shuffles the cards
    dealer(cachedShuffledCards: Deck)

    // First hand dealt to players
    for (player <- numberOfPlayers) dealCards(2, player)

    println("Dealing the first 2 cards to the players")
    numberOfPlayers.map(player => (player.name,player.totalCardsOfPlayer)).foreach(println)

    // Players are dealt with based on the outcome of their cards
    dealWithPlayers()
  }
}