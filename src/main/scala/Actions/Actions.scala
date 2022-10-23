package Actions

import CardDeck._
import Utils.CardRules._
import Utils.Player
import Utils.TypeAlias.{CardInDeck, Deck}

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

  // Stage 3(Actions) - Players go in turn
  def dealWithPlayers(): Unit = numberOfPlayers match {
    case numberOfPlayers if (numberOfPlayers.isEmpty) => throw new IllegalArgumentException("There are no players available.")
    case _ =>
      for (player <- numberOfPlayers) {
        if (hit(player)) dealCards(1, player)
        else if (stick(player)) println(s"It's a stick, no card is dealt to ${player.name}")
        else if (go_bust(player)) removePlayer(player)
      }
      if (crossCheckWithRules()) getWinner
      else dealWithPlayers()
  }

  def crossCheckWithRules(): Boolean = {
    val condition_1 = numberOfPlayers.count(stick) == numberOfPlayers.size
    val condition_2 = numberOfPlayers.map(player => (player.name, player.totalCardsOfPlayer)._2.map(_.cardNumber.value).sum).contains(21)
    val condition_3 = numberOfPlayers.size == 1
    condition_1 || condition_2 || condition_3
  }

  // Get winner
  def getWinner = {
    val filterWinners = numberOfPlayers
      .filter(player =>
        (player.name, player.totalCardsOfPlayer)
          ._2
          .map(_.cardNumber.value)
          .sum < 21)


    // TODO: Resolve the solution so that it works for when the values are the same and also,select the maximum
    filterWinners.map(player => (player.name, player.totalCardsOfPlayer,player.totalCardsOfPlayer.map(_.cardNumber.value).sum)).sortBy(_._2.map(_.cardNumber.value).sum).foreach(println)
  }



}
