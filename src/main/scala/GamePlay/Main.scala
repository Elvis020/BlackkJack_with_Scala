package GamePlay

import Actions.Actions._
import CardDeck.numberOfPlayers
import Messages.Messages._
import Utils.TypeAlias.Deck

object Main extends App{
    // Step 1
    displayWelcomeMessage(welcome)
    requestAndCreatePlayers()

    // Step 2
    displayMessage(availablePlayers)
    numberOfPlayers.foreach(player => println(player.name))

    // Step 3: Each player is dealt a hand from the top of the deck
    println("\n" + shuffleCardsMessage)
    val cachedShuffledCards = shuffleCards()

    // Step 4: Dealer shuffles the cards
    dealer(cachedShuffledCards: Deck)

    // Step 5: First hand dealt to players
    for (player <- numberOfPlayers) dealCards(2, player)

    // Step 6
    displayMessage(dealingFirst2)
    numberOfPlayers.map(player => (player.name,player.toString())).foreach(nc => println(nc._1+" with "+nc._2))

    // Step 7: Players are dealt with based on the outcome of their cards
    dealWithPlayers()
}