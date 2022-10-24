import CardDeck._
import Player.Player
import munit.FunSuite

import scala.collection.mutable.ListBuffer

class CardDeckTest extends FunSuite{
  override def afterEach(context: AfterEach): Unit = numberOfPlayers.clear()

  // Mocking the players
  val player_1 = Player("Player1")
  val player_2 = Player("Player2")
  val player_3 = Player("Player3")

  test("Total number of Clubs should be 13") {
    assertEquals(all_clubs.size, 13)
  }
  test("Total number of Spades should be 13") {
    assertEquals(all_spades.size, 13)
  }
  test("Total number of Diamonds should be 13") {
    assertEquals(all_diamonds.size, 13)
  }
  test("Total number of Hearts should be 13") {
    assertEquals(all_hearts.size, 13)
  }

  test("At the start of the game, no player has been added so the total number of players should be 0") {
    assertEquals(numberOfPlayers.size, 0)
  }

}
