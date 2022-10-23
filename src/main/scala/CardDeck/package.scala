import Utils.Player
import Utils.TypeAlias.ListOfPlayers

import scala.collection.mutable.ListBuffer

package object CardDeck {
  val all_clubs: ListBuffer[Clubs] = ListBuffer(
    Clubs(CardNumber.Two),
    Clubs(CardNumber.Three),
    Clubs(CardNumber.Four),
    Clubs(CardNumber.Five),
    Clubs(CardNumber.Six),
    Clubs(CardNumber.Seven),
    Clubs(CardNumber.Eight),
    Clubs(CardNumber.Nine),
    Clubs(CardNumber.Ten),
    Clubs(CardNumber.Jack),
    Clubs(CardNumber.Queen),
    Clubs(CardNumber.King),
    Clubs(CardNumber.Ace)
  )

  val all_hearts: ListBuffer[Hearts] = ListBuffer(
    Hearts(CardNumber.Two),
    Hearts(CardNumber.Three),
    Hearts(CardNumber.Four),
    Hearts(CardNumber.Five),
    Hearts(CardNumber.Six),
    Hearts(CardNumber.Seven),
    Hearts(CardNumber.Eight),
    Hearts(CardNumber.Nine),
    Hearts(CardNumber.Ten),
    Hearts(CardNumber.Jack),
    Hearts(CardNumber.Queen),
    Hearts(CardNumber.King),
    Hearts(CardNumber.Ace))

  val all_spades: ListBuffer[Spades] = ListBuffer(
    Spades(CardNumber.Two),
    Spades(CardNumber.Three),
    Spades(CardNumber.Four),
    Spades(CardNumber.Five),
    Spades(CardNumber.Six),
    Spades(CardNumber.Seven),
    Spades(CardNumber.Eight),
    Spades(CardNumber.Nine),
    Spades(CardNumber.Ten),
    Spades(CardNumber.Jack),
    Spades(CardNumber.Queen),
    Spades(CardNumber.King),
    Spades(CardNumber.Ace)
  )

  val all_diamonds: ListBuffer[Diamond] = ListBuffer(
    Diamond(CardNumber.Two),
    Diamond(CardNumber.Three),
    Diamond(CardNumber.Four),
    Diamond(CardNumber.Five),
    Diamond(CardNumber.Six),
    Diamond(CardNumber.Seven),
    Diamond(CardNumber.Eight),
    Diamond(CardNumber.Nine),
    Diamond(CardNumber.Ten),
    Diamond(CardNumber.Jack),
    Diamond(CardNumber.Queen),
    Diamond(CardNumber.King),
    Diamond(CardNumber.Ace)
  )

  val numberOfPlayers:ListOfPlayers = ListBuffer.empty[Player]

  val shufflingTechnique:List[String] = List(
    "Riffle shuffle",
    "Overhand Shuffle",
    "Hindu Shuffle",
    "Pharoah/Faro Shuffle")
}
