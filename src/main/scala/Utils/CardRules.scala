package Utils

import CardDeck.numberOfPlayers

object CardRules {
  // This checks if a player's total is < 17
  def  hit(player: Player):Boolean = player.totalCardsOfPlayer.map(_.cardNumber.value).sum < 17

  // This checks if a player's total is >= 17
  def  stick(player: Player):Boolean = player.totalCardsOfPlayer.map(_.cardNumber.value).sum >= 17


  // This checks if a player's total is > 21
  def  go_bust(player: Player):Boolean = player.totalCardsOfPlayer.map(_.cardNumber.value).sum > 21


  // Remove player from game
  val removePlayer = (player: Player) => numberOfPlayers -= player

}
