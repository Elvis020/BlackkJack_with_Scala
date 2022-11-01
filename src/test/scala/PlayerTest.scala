import Actions.Actions.requestAndCreatePlayers
import CardDeck.numberOfPlayers
import Player.Player
import Utils.UtilsFns.{createPlayer, dealCards, select_players}
import munit.FunSuite

import scala.collection.mutable.ListBuffer

class PlayerTest extends FunSuite{
  override def afterAll(): Unit = numberOfPlayers.clear()
  override def afterEach(context: AfterEach): Unit = numberOfPlayers.clear()


  // Mocking the players
  val player_1 = Player("player_1")
  val player_2 = Player("player_2")
  val player_3 = Player("player_3")

  test("Verifies player_1 has 2 cards after he is dealt with initially") {
    dealCards(2, player_1)
    assertEquals(player_1.totalCardsOfPlayer.size, 2)
  }


  test("Verifies player_1 has 3 cards after he is dealt again") {
    dealCards(1, player_1)
    assertEquals(player_1.totalCardsOfPlayer.size, 3)
  }

  test("Verifies that there are a total of 3 players") {
    numberOfPlayers.addAll(ListBuffer(player_1,player_2,player_3))
    assertEquals(numberOfPlayers.size, 3)
  }


  test("Verifies that 5 players are created, when the createPlayer function is called with an input of 5") {
    createPlayer(5)
    assertEquals(numberOfPlayers.size, 5)
  }


  test("Verifies that requestAndCreatePlayers() throws an exception if input is 21") {
    val thrown = intercept[Exception] {
      requestAndCreatePlayers(21)
    }
    assertEquals(thrown.getMessage,"Number of players cannot be more than 6 or less than 2")
  }

  test("Verifies that requestAndCreatePlayers() throws an exception if input is -2") {
    val thrown = intercept[Exception] {
      requestAndCreatePlayers(-2)
    }
    assertEquals(thrown.getMessage,"Number of players cannot be more than 6 or less than 2")
  }



  test("Verifies that requestAndCreatePlayers() creates 3 players if there is no args for --player") {
    val getArgs = List(("--strategy","all-stick"))
    val players = select_players(getArgs)
    requestAndCreatePlayers(players)
    assertEquals(numberOfPlayers.size,3)
  }

  test("Verifies that requestAndCreatePlayers() creates 5 players if the input is 5") {
    requestAndCreatePlayers(5)
    assertEquals(numberOfPlayers.size, 5)
  }


}
