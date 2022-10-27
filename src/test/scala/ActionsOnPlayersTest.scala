import Actions.Actions.requestAndCreatePlayers
import CardDeck.numberOfPlayers
import Utils.UtilsFns.createPlayer
import munit.FunSuite

import java.io.StringReader

class ActionsOnPlayersTest extends FunSuite{
  override def afterEach(context: AfterEach): Unit = numberOfPlayers.clear()

  test("Verifies that 5 players are created, when the createPlayer function is called with an input of 5") {
    createPlayer(5)
    assertEquals(numberOfPlayers.size, 5)
  }


  test("Verifies that requestAndCreatePlayers() creates 3 players if the input is greater than 6") {
    val inputParam = "21"
    val in = new StringReader(inputParam)
    Console.withIn(in) {
      requestAndCreatePlayers()
    }
    assertEquals(numberOfPlayers.size, 3)
  }

  test("Verifies that requestAndCreatePlayers() creates 3 players if the input is less than 0") {
    val inputParam = "-2"
    val in = new StringReader(inputParam)
    Console.withIn(in) {
      requestAndCreatePlayers()
    }
    assertEquals(numberOfPlayers.size, 3)
  }


  test("Verifies that requestAndCreatePlayers() creates 3 players if the input is 0") {
    val inputParam = "0"
    val in = new StringReader(inputParam)
    Console.withIn(in) {
      requestAndCreatePlayers()
    }
    assertEquals(numberOfPlayers.size, 3)
  }

  test("Verifies that requestAndCreatePlayers() creates 5 players if the input is 5") {
    val inputParam = "5"
    val in = new StringReader(inputParam)
    Console.withIn(in) {
      requestAndCreatePlayers()
    }
    assertEquals(numberOfPlayers.size, 5)
  }


}
