package Actions

import CardDeck._
import Messages.Messages._
import Player.Player
import Utils.CardRules._
import Utils.TypeAlias._
import Utils.UtilsFns._

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.util.{Failure, Random, Success, Try}

object Actions {

  // Asks the user's preference of shuffling technique
  def askUserTheShufflingTechnique():GameInPlay = {
    displayMessage(listShufflingTechniques)
    displayShufflingTechniques()
    val readTechniqueInput = Try(StdIn.readInt()-1)
    readTechniqueInput match {
      case Failure(_) =>
        println("Kindly check your input and select a valid technique")
        askUserTheShufflingTechnique()
      case Success(value) => value match {
        case n if n >= 0 & n < 4  => println("\n" + shuffleCardsMessage + s" using the ${shufflingTechnique(value)} technique")
        case _ => askUserTheShufflingTechnique()
      }
    }
  }


  // Request the number of players and create them
  def requestAndCreatePlayers(numberOfPlayers:Int):GameInPlay = {
    if (numberOfPlayers>6 || numberOfPlayers<2) throw new Exception("Number of players cannot be more than 6 or less than 2")
    else createPlayer(numberOfPlayers)
  }


  // At the start of the game, the deck is shuffled
  def shuffleCards(): Deck = {
    val allCards: Deck = ListBuffer(all_hearts, all_diamonds, all_clubs, all_spades).flatten
    Random.shuffle(allCards)
  }

  // Take the head of the shuffled deck and start giving it to the players(Current work-around)
  def dealCardToPlayer(cachedShuffledCards: Deck) = {
    val selected = cachedShuffledCards.head
    cachedShuffledCards.remove(0)
    selected
  }


  // Deal cards to players and check the criteria each time a hand is dealt
  @tailrec
  def dealWithPlayers(): GameInPlay = {

    val playersToRemove = ListBuffer[Player]()
    numberOfPlayers match {
      case numberOfPlayers if (numberOfPlayers.isEmpty) => throw new IllegalArgumentException("There are no players available.")
      case _ =>
        
        // Display message indicating that you are dealing to players
        displayMessage(dealingToPlayers)
        numberOfPlayers.map(player => (player.name,player.toString(),player)).foreach(x => println("🫴 "+x._1+x._2+" with total: "+calculateValueOfCards(x._3)))
        println("\n")
        
        // Deal to players available
        for (player <- numberOfPlayers) {
              if (hit(player)) {
                dealCards(1, player)
                println(s"🫴 ${player.name} has been dealt again.${player.toString()}, with total: ${calculateValueOfCards(player)}")
          }
          else if (go_bust(player)) playersToRemove += player
          else if (stick(player)) println(s"🩼 It's a stick, no card is dealt to ${player.name}")
        }
    }

    // Check if you need to remove player
    if(playersToRemove.nonEmpty){
      println(s"🚮 ${playersToRemove.head.name} has been removed.");
      numberOfPlayers -= playersToRemove.head
    }
    
    // Check again with rules and deal again to players depending on outcome
    if (rules()) getWinner(numberOfPlayers)
    else dealWithPlayers()
  }

  def get_probable_winners(players:ListOfPlayers):ListOfPlayers = {
    players
      .filter(player =>(player.name, player.totalCardsOfPlayer.map(_.cardNumber.value).sum < 21)._2)
  }

  // Get winner
  def getWinner(numberOfPlayers:ListOfPlayers): GameInPlay = {

    // Filtering the probable winners
    val probableWinners: ListOfPlayers = get_probable_winners(numberOfPlayers)
    val probableWinnersWithTheirScores: PlayersWithScores = probableWinners.map(player => (player.name, calculateValueOfCards(player)))


    displayMessage(playersWhoPassedCriteria)
    passedCriteria(probableWinnersWithTheirScores: PlayersWithScores)

    // In cases where there is more than one winner, display all of them and 
    // in cases where everyone does not meet criteria, no one wins
    val winners: PlayersWithScores = get_final_winner(probableWinnersWithTheirScores, probableWinners)


    displayMessage(results)
    if (winners.nonEmpty) {
      winners.map(_._1).foreach(println)
    } else println("No winners")
  }




}
