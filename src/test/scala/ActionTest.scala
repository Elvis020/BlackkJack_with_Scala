import Actions.Actions.shuffleCards
import munit.FunSuite

class ActionTest extends FunSuite {
  test("Total number of shuffled cards should be 52") {
    val numberOfCards = shuffleCards().size
    assertEquals(numberOfCards, 52)
  }

  // TODO: Mock the cards and see if logic of getting winner is correct
}
