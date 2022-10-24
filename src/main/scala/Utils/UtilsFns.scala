package Utils

import Actions.Actions.{dealCardToPlayer, shuffleCards}
import CardDeck.{numberOfPlayers, shufflingTechnique}
import Player.Player
import Utils.CardRules.stick
import Utils.TypeAlias.GameInPlay

object UtilsFns {
  // Set of rules, on which the game is based on
  def rules(): Boolean = {
    val condition_1 = numberOfPlayers.count(stick) == numberOfPlayers.size
    val condition_2 = numberOfPlayers
      .map(player => (player.name, player.totalCardsOfPlayer)
        ._2.map(_.cardNumber.value).sum)
      .contains(21)

    val condition_3 = numberOfPlayers.size == 1
    condition_1 || condition_2 || condition_3
  }

  // Deal cards to player one-at-a-time
  def dealCards(numberOfTimes: Int = 1, player: Player): GameInPlay = {
    for (_ <- Range(0, numberOfTimes)) player.totalCardsOfPlayer += dealCardToPlayer(shuffleCards())
  }

  // Creates players based on input/default value
  def createPlayer(value: Int):GameInPlay = {
    for (i <- Range(0, value)) {
      val nameOfPlayer = s"Player${i + 1}"
      numberOfPlayers.addOne(Player(nameOfPlayer))
    }
  }

  // Function to display the shuffling techniques available
  def displayShufflingTechniques(): GameInPlay = {
    for (i <- Range(0, shufflingTechnique.length)) {
      println(s"${i + 1}.${shufflingTechnique(i)}")
    }
  }

  // Function to calculate the sum of the value of the cards
  val calculateValueOfCards: Player => Int = (player:Player) => player.totalCardsOfPlayer.map(_.cardNumber.value).sum
}
