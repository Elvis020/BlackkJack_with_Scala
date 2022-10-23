import Actions.Actions.shuffleCards
import munit.FunSuite

class ActionTest extends FunSuite {
  test("Total number of shuffled cards should be 52") {
    val numberOfCards = shuffleCards().size
    assertEquals(numberOfCards, 52)
  }
}
