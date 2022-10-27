import Actions.Actions.requestAndCreatePlayers
import CardDeck.numberOfPlayers
import Player.Player
import Utils.UtilsFns.{createPlayer, dealCards}
import munit.FunSuite

import java.io.StringReader
import scala.collection.mutable.ListBuffer

class PlayerTest extends FunSuite{
  override def afterAll(): Unit = numberOfPlayers.clear()

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


}
