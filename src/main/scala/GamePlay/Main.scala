package GamePlay

import Actions.Actions._
import CardDeck.numberOfPlayers
import Messages.Messages._
import Utils.TypeAlias.Deck
import Utils.UtilsFns.{dealCards, select_players}

object Main {
    def main(args: Array[String]): Unit = {
        val get_args: Seq[(String, String)] = args.sliding(2, 2).map(arg => (arg.toSeq.head, arg.toSeq.last)).toSeq


        // Step 1
        displayWelcomeMessage(welcome)
        val players = select_players(get_args)
        requestAndCreatePlayers(players)

        // Step 2
        displayMessage(availablePlayers)
        numberOfPlayers.foreach(player => println(player.name))

        // Step 3: Each player is dealt a hand from the top of the deck
        askUserTheShufflingTechnique()
        val cachedShuffledCards = shuffleCards()

        // Step 4: Dealer shuffles the cards
        dealCardToPlayer(cachedShuffledCards: Deck)

        // Step 5: First hand dealt to players
        for (player <- numberOfPlayers) dealCards(2, player)


        // Step 6
        displayMessage(dealingFirst2)
        numberOfPlayers.map(player => (player.name, player.toString()))
          .foreach(player => println(player._1 + " with" + player._2))

        // Step 7: Players are dealt with based on the outcome of their
        // cards and the winner(s) is determined
        dealWithPlayers()
    }
}