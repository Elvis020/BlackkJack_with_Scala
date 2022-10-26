import Actions.Actions.{askUserTheShufflingTechnique, dealCardToPlayer, shuffleCards}
import Utils.TypeAlias.Card
import munit.FunSuite

import java.io.{ByteArrayOutputStream, StringReader}
import scala.collection.mutable.ListBuffer

class ActionTest extends FunSuite {
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

  // TODO: Mock the cards and see if logic of getting winner is correct
}
