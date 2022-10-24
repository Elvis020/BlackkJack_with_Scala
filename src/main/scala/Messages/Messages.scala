package Messages

import Utils.TypeAlias.{GameInPlay, Message}

object Messages {
  val welcome:Message = "Welcome to the Black Jack Game Project"
  val availablePlayers:Message = "Available players ♠️♥️♦️♣️"
  val results:Message = "Winner(s)🥳🥳🥳🥳🥳"
  val playersWhoPassedCriteria:Message = "Players who passed the criteria"
  val shuffleCardsMessage:Message = "Cards have been shuffled"
  val dealingToPlayers:Message = "Dealing to players"
  val requestNumberOfPlayers:Message = "Please enter the number of players for this game."
  val dealingFirst2:Message = "🃏Dealing the first 2 cards to the players🃏"
  val listShufflingTechniques:Message = "Which shuffling🔀 technique would you like to be used?"



  def displayMessage(message:String): GameInPlay = {
    println("\n" + message)
    println("-" * message.length)
  }
  def displayWelcomeMessage(message:String): GameInPlay = {
    println("*" * message.length)
    println(""* message.length + message + ""* message.length )
    println("*" * message.length)
  }

}
