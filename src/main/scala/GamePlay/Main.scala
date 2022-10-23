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
      for {
        _ <- Range(0,numberOfTimes)
      } player.totalCardsOfPlayer += dealer()
    }


    for (player <- numberOfPlayers) dealCards(2,player)






    // Stage 3(Actions) - Players go in turn
    // TODO: Have a checker, check if the current player uses the strategy available
    def dealWithPlayers():Unit = numberOfPlayers match{
      case numberOfPlayers if (numberOfPlayers.isEmpty) => throw new IllegalArgumentException("There are no players available.")
      case _ =>
        for (i <- numberOfPlayers.indices) {
          val current_player = numberOfPlayers(i)
          if (hit(current_player)) dealCards(1,current_player)
          if (stick(current_player)) println("It's a stick, no card is dealt")
          if(go_bust(current_player)) removePlayer(current_player)
        }
        if(!crossCheckWithRules()) dealWithPlayers()
        else getWinner()


    }
    def crossCheckWithRules(): Boolean = {
      val condition_1 = numberOfPlayers.count(stick) == numberOfPlayers.size
      val condition_2 = numberOfPlayers.flatMap(_.totalCardsOfPlayer).map(_.cardNumber.value).contains(21)
      val condition_3 = numberOfPlayers.size == 1
      condition_1 & condition_2 & condition_3
    }
    // Get winner displays the final result for now
//    def getWinner(): ListBuffer[(Player, Int)] = for {
//      player <- numberOfPlayers
//      score <- numberOfPlayers.flatMap(_.totalCardsOfPlayer).map(_.cardNumber.value)
//    } yield (player, score)
    def getWinner()= {
      numberOfPlayers.foreach(println)
  }

  // Fix recursion issue
  dealWithPlayers()
  }
}