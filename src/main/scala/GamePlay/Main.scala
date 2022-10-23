package GamePlay

import CardDeck.{CardNumber, Clubs, Diamond, Hearts, Spades, Suit, all_clubs, all_diamonds, all_hearts, all_spades, numberOfPlayers}
import Utils.CardRules.{go_bust, hit, removePlayer, stick}
import Utils.Player
import Utils.TypeAlias.{CardInDeck, Deck}

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {

    // Assume there are 3 players
    val player_1 = Player("Player1")
    val player_2 = Player("Player2")
    val player_3 = Player("Player3")

    // Adding players to the list
    numberOfPlayers.addAll(ListBuffer(player_1,player_2,player_3))


    // At the start of the game, the deck is shuffled
    def shuffleCards():Deck = {
      val allCards:Deck = ListBuffer(all_hearts,all_diamonds,all_clubs, all_spades).flatten
      Random.shuffle(allCards)
    }
      // Each player is dealt a hand from the top of the deck
      // TODO: Write test to check the number of cards generated
    val cachedShuffledCards = shuffleCards()
      // take the head of the shuffled deck and start giving it to the players(Current work-around)
    def dealer():CardInDeck = {
      val selected = cachedShuffledCards.head
      cachedShuffledCards.remove(0)
      selected
    }
      // TODO: Write test to check the number of cards remaining after dealing the initial ones
    def dealCards(numberOfTimes:Int = 1,player: Player): Unit ={
      for(_ <- Range(0,numberOfTimes)) player.totalCardsOfPlayer += dealer()
    }


    for (player <- numberOfPlayers) dealCards(2,player)






    // Stage 3(Actions) - Players go in turn
    def dealWithPlayers():Unit = numberOfPlayers match{
      case numberOfPlayers if (numberOfPlayers.isEmpty) => throw new IllegalArgumentException("There are no players available.")
      case _ =>
        for (player <- numberOfPlayers) {
          if (hit(player)) dealCards(1,player)
          else if (stick(player)) println("It's a stick, no card is dealt")
          else if(go_bust(player)) removePlayer(player)
        }
        println("---------OK---------")
        if(crossCheckWithRules()) getWinner
        else dealWithPlayers()
    }
    def crossCheckWithRules(): Boolean = {
      val condition_1 = numberOfPlayers.count(stick) == numberOfPlayers.size
      val condition_2 = numberOfPlayers.map(player => (player.name,player.totalCardsOfPlayer)._2.map(_.cardNumber.value).sum).contains(21)
      val condition_3 = numberOfPlayers.size == 1
      condition_1 || condition_2 || condition_3
    }


    // Get winner displays the final result for now
//    def getWinner(): ListBuffer[(Player, Int)] = for {
//      player <- numberOfPlayers
//      score <- numberOfPlayers.flatMap(_.totalCardsOfPlayer).map(_.cardNumber.value)
//    } yield (player, score)
    def getWinner = {
      val filterWinners = numberOfPlayers
        .filter(player =>
          (player.name,player.totalCardsOfPlayer)
            ._2
            .map(_.cardNumber.value)
            .sum < 21)


      // TODO: Resolve the solution so that it works for when the values are the same and also,select the maximum
      filterWinners.map(player => (player.name, player.totalCardsOfPlayer)).sortBy(_._2.map(_.cardNumber.value).sum).foreach(println)
    }

    dealWithPlayers()

  }
}