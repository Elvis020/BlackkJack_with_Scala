import Actions.Actions._
import CardDeck.CardNumber._
import CardDeck._
import Player.Player
import Utils.TypeAlias.Card
import munit.FunSuite

import java.io.{ByteArrayOutputStream, StringReader}
import scala.collection.mutable.ListBuffer

class ActionTest extends FunSuite {
  override def afterAll(): Unit = numberOfPlayers.clear()

  // Mocking the players
  val player_1 = Player("Player1")
  val player_2 = Player("Player2")
  val player_3 = Player("Player3")

  val card1 = ListBuffer(Hearts(CardNumber.Three), Diamond(CardNumber.Nine), Clubs(CardNumber.Ace))
  val card2 = ListBuffer(Clubs(Four), Spades(Eight), Hearts(Two))
  val card3 = ListBuffer(Diamond(Six), Clubs(Eight), Spades(Two))

  // Adding cards to players
  player_1.totalCardsOfPlayer.addAll(card1)
  player_2.totalCardsOfPlayer.addAll(card2)
  player_3.totalCardsOfPlayer.addAll(card3)
  numberOfPlayers.addAll(ListBuffer(player_1, player_2, player_3))



  test("Total number of shuffled cards should be 52") {
    val numberOfCards = shuffleCards().size
    assertEquals(numberOfCards, 52)
  }

  test("Verifies that the Pharoah/Faro Shuffle technique is selected, when input is 4") {
    val inputParam = "4"
    val in = new StringReader(inputParam)
    val out = new ByteArrayOutputStream()
    Console.withOut(out){
      Console.withIn(in) {
        askUserTheShufflingTechnique()
      }
    }
    val actual = out.toString.split("\n").last.strip()
    val required = "Cards have been shuffled using the Pharoah/Faro Shuffle technique"
    assertEquals(actual,required)
  }

  test("Verifies that the Riffle shuffle technique is selected, when input is 1") {
    val inputParam = "1"
    val in = new StringReader(inputParam)
    val out = new ByteArrayOutputStream()
    Console.withOut(out){
      Console.withIn(in) {
        askUserTheShufflingTechnique()
      }
    }
    val actual = out.toString.split("\n").last.strip()
    val required = "Cards have been shuffled using the Riffle shuffle technique"
    assertEquals(actual,required)
  }

  test("Verifies that only one card is dealt to the player, when the dealCardToPlayer() is called") {
    val listOfCards = ListBuffer[Card]()
    listOfCards += dealCardToPlayer(shuffleCards())
    assertEquals(listOfCards.size, 1)
  }

  test("With the given data, the total number of probable players are 2") {
    val actual = get_probable_winners(numberOfPlayers).size
    assertEquals(actual,2)
  }

  test("With the given data, the probable players are Player2 and Player3") {
    val actual = get_probable_winners(numberOfPlayers).map(_.name)
    assertEquals(actual,ListBuffer("Player2","Player3"))
  }

  test("With the given data, the final winner is Player 3") {
    val actual = getWinner(numberOfPlayers)
    val out = new ByteArrayOutputStream()
    Console.withOut(out){
      getWinner(numberOfPlayers)
    }
    val winner = out.toString.split("\n").last.strip()
    assertEquals(winner,"Player3")
  }


}
