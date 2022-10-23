package Actions

import CardDeck._
import Messages.Messages._
import Utils.CardRules._
import Utils.Player
import Utils.TypeAlias.{CardInDeck, Deck}

import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.util.{Failure, Random, Success, Try}

object Actions {

  // Asks the user's preference of shuffling technique
  def askUserTheShufflingTechnique():Unit = {
    displayMessage(listShufflingTechniques)
    displayShufflingTechniques()
    val readTechniqueInput = Try(StdIn.readInt())
    readTechniqueInput match {
      case Failure(exception) => {
        println("Kindly check your input and select a valid technique")
        askUserTheShufflingTechnique()
      }
      case Success(value) => value match {
        case n if n >= 0 & n < 4  => println("\n" + shuffleCardsMessage + s" using the ${shufflingTechnique(value-1)} technique")
        case _ => {
          askUserTheShufflingTechnique()
        }
      }
    }
  }

  // Function to display the shuffling techniques available
  def displayShufflingTechniques():Unit = {
    for (i <- Range(0, shufflingTechnique.length)) {
      println(s"${i + 1}.${shufflingTechnique(i)}")
    }
  }

  // Request the number of players and create them
  def requestAndCreatePlayers():Unit = {
    displayMessage("\n"+requestNumberOfPlayers)
    println("Enter 0 for the default value which is 3.😊")
    val readUserInput = Try(StdIn.readInt())
    readUserInput match {
      case Failure(exception) => {
        println("Kindly check your input and enter a valid number, between 1 and 6")
        requestAndCreatePlayers()
      }
      case Success(value) => value match {
        case number if number>6 || number<1 => {
          println("Value is either greater than 6 or less than 1, using default value of 3.")
          createPlayer(3)
        }
        case number => createPlayer(number)
      }
    }

  }


  // Creates players based on input/default value
  def createPlayer(value:Int) = {
    for (i <- Range.inclusive(1, value)) {
      val nameOfPlayer = s"Player${i}"
      numberOfPlayers.addOne(Player(nameOfPlayer))
    }
  }


  // At the start of the game, the deck is shuffled
  def shuffleCards(): Deck = {
    val allCards: Deck = ListBuffer(all_hearts, all_diamonds, all_clubs, all_spades).flatten
    Random.shuffle(allCards)
  }

  // Take the head of the shuffled deck and start giving it to the players(Current work-around)
  def dealer(cachedShuffledCards: Deck): CardInDeck = {
    val selected = cachedShuffledCards.head
    cachedShuffledCards.remove(0)
    selected
  }

  // Deal cards to player one-at-a-time
  def dealCards(numberOfTimes: Int = 1, player: Player): Unit = {
    for (_ <- Range(0, numberOfTimes)) player.totalCardsOfPlayer += dealer(shuffleCards())
  }

  // Deal cards to players and check the criteria each time a hand is dealt
  def dealWithPlayers(): Unit = {

    val playersToRemove = ListBuffer[Player]()
    numberOfPlayers match {
      case numberOfPlayers if (numberOfPlayers.isEmpty) => throw new IllegalArgumentException("There are no players available.")
      case _ =>
        displayMessage(dealingToPlayers)
        numberOfPlayers.map(player => (player.name,player.toString())).foreach(nc => println(nc._1+" with "+nc._2))
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
    if (rules()) getWinner
    else dealWithPlayers()
  }

  // Set of rules, on which the game is based on
  def rules(): Boolean = {
    val condition_1 = numberOfPlayers.count(stick) == numberOfPlayers.size
    val condition_2 = numberOfPlayers
      .map(player => (player.name, player.totalCardsOfPlayer)
      ._2.map(_.cardNumber.value).sum)
      .contains(21)

    val condition_3 = numberOfPlayers.size == 1
    condition_1 || condition_2 || condition_3
  }

  // Get winner
  def getWinner:Unit = {
    // Filtering the winners
    val filterWinners = numberOfPlayers
      .filter(player =>
        (player.name, player.totalCardsOfPlayer)
          ._2
          .map(_.cardNumber.value)
          .sum < 21)


    val finalWinners = filterWinners.map(player => (player.name,player.totalCardsOfPlayer.map(_.cardNumber.value).sum))


    displayMessage(playersWhoPassedCriteria)
    finalWinners.foreach(nc => println(nc._1+", Total: "+nc._2))


    // In cases where there is more than one winner, display all of them
    val get_final_winner = {
      val max_number = if (finalWinners.nonEmpty) {finalWinners.maxBy {_._2}._2} else 0
      filterWinners.map(player => (player.name,player.totalCardsOfPlayer.map(_.cardNumber.value).sum)).filter(_._2 == max_number)

    }


    displayMessage(results)

    // In cases where everyone does not meet criteria, no one wins
    if(get_final_winner.nonEmpty) {
      get_final_winner.map(_._1).foreach(println)
    } else println("No winners")
  }



}
