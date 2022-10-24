import Actions.Actions.{dealCards, dealWithPlayers}
import CardDeck.CardNumber.{Ace, Jack, King}
import CardDeck.{Clubs, Spades, numberOfPlayers}
import Player.Player
import Utils.CardRules.{go_bust, hit, stick}
import munit.FunSuite

import scala.collection.mutable.ListBuffer

class CardRulesTest extends FunSuite{
  // Attempt to Mock(might find a better approach to Mocking)
  val player_1 = Player("player_1")
  test("Total Cards player_1 has is 0") {
    assertEquals(player_1.totalCardsOfPlayer.size, 0)
  }


  test("Total Cards player_1 has is 3, after the addition") {
    player_1.totalCardsOfPlayer ++= ListBuffer(Clubs(King), Clubs(Jack), Spades(Ace))
    assertEquals(player_1.totalCardsOfPlayer.size, 3)
  }

  test("Verifies that player_1 does not have a hit") {
    val isHit = hit(player_1)
    assertEquals(isHit, false)
  }

  test("Verifies that player_1 goes bust or is sticked") {
    val isBusted = go_bust(player_1)
    val isSticked = stick(player_1)
    assertEquals(isBusted, true)
    assertEquals(isSticked, true)
  }

}
