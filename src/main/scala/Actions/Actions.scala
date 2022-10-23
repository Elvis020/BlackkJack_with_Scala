package Actions

import CardDeck._
import Messages.Messages.{inPlay, playersWhoPassedCriteria, results}
import Utils.CardRules._
import Utils.Player
import Utils.TypeAlias.{CardInDeck, Deck}

import java.util.Collections
import scala.collection.mutable.ListBuffer
import scala.util.Random

object Actions {
  // At the start of the game, the deck is shuffled
  def shuffleCards(): Deck = {
    val allCards: Deck = ListBuffer(all_hearts, all_diamonds, all_clubs, all_spades).flatten
    Random.shuffle(allCards)
  }


  // take the head of the shuffled deck and start giving it to the players(Current work-around)
  def dealer(cachedShuffledCards: Deck): CardInDeck = {
    val selected = cachedShuffledCards.head
    cachedShuffledCards.remove(0)
    selected
  }

  // TODO: Write test to check the number of cards remaining after dealing the initial ones
  def dealCards(numberOfTimes: Int = 1, player: Player): Unit = {
    for (_ <- Range(0, numberOfTimes)) player.totalCardsOfPlayer += dealer(shuffleCards())
  }

  println("\n"+inPlay)
  println("-"*inPlay.length)
  def dealWithPlayers(): Unit = {


    val playersToRemove = ListBuffer[Player]()

    numberOfPlayers match {
      case numberOfPlayers if (numberOfPlayers.isEmpty) => throw new IllegalArgumentException("There are no players available.")
      case _ =>
        println("\nDealing to players")
        numberOfPlayers.map(player => (player.name, player.totalCardsOfPlayer.map(_.cardNumber.value))).foreach(println)
        for (player <- numberOfPlayers) {
          if (hit(player)) dealCards(1, player)
          else if (go_bust(player)) playersToRemove += player
          else if (stick(player)) println(s"It's a stick, no card is dealt to ${player.name}")
        }
    }

    // Check if you need to remove player
    if(playersToRemove.nonEmpty){
      numberOfPlayers -= playersToRemove.head
    }
    if (crossCheckWithRules()) getWinner
    else dealWithPlayers()
  }

  def crossCheckWithRules(): Boolean = {
    val condition_1 = numberOfPlayers.count(stick) == numberOfPlayers.size
    val condition_2 = numberOfPlayers
      .map(player => (player.name, player.totalCardsOfPlayer)
      ._2.map(_.cardNumber.value).sum)
      .contains(21)

    val condition_3 = numberOfPlayers.size == 1
    condition_1 || condition_2 || condition_3
  }

  // Get winner
  def getWinner = {
    // Filtering the winners
    val filterWinners = numberOfPlayers
      .filter(player =>
        (player.name, player.totalCardsOfPlayer)
          ._2
          .map(_.cardNumber.value)
          .sum < 21)

    val winners_version_1 = filterWinners.map(player => (player.name,player.totalCardsOfPlayer.map(_.cardNumber.value).sum))


    println("\n"+playersWhoPassedCriteria)
    println("-"*playersWhoPassedCriteria.length)
    winners_version_1.foreach(println)


    val finalWinners = filterWinners.map(player => (player.name,player.totalCardsOfPlayer.map(_.cardNumber.value).sum))
    val get_final_winner = {
      val max_number = if (finalWinners.nonEmpty) {finalWinners.maxBy {_._2}._2} else 0
      filterWinners.map(player => (player.name,player.totalCardsOfPlayer.map(_.cardNumber.value).sum)).filter(_._2 == max_number)

    }


    println("\n"+results)
    println("-"*results.length)
    if(get_final_winner.nonEmpty) get_final_winner.foreach(println) else "No winners"
  }



}
