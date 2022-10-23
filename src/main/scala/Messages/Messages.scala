package Messages

object Messages {
  val welcome = "Welcome to the Black Jack Game Project"
  val availablePlayers = "Available players ♠️♥️♦️♣️"
  val results = "Winner(s)🥳🥳🥳🥳🥳"
  val playersWhoPassedCriteria = "Players who passed the criteria"
  val shuffleCardsMessage = "Cards have been shuffled"
  val dealingToPlayers = "Dealing to players"
  val requestNumberOfPlayers = "Please enter the number of players for this game."
  val dealingFirst2 = "🃏Dealing the first 2 cards to the players🃏"
  val listShufflingTechniques = "Which shuffling🔀 technique would you like to be used?"



  def displayMessage(message:String): Unit = {
    println("\n" + message)
    println("-" * message.length)
  }
  def displayWelcomeMessage(message:String): Unit = {
    println("*" * message.length)
    println(""* message.length + message + ""* message.length )
    println("*" * message.length)
  }

}
