package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

interface GameState {

    abstract class AbstractPlayerState(private val player: Player) : GameState {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as AbstractPlayerState
            if (player != other.player) return false
            return true
        }

        override fun hashCode(): Int {
            return player.hashCode()
        }
    }

    abstract class PlayerMove(player: Player) : AbstractPlayerState(player)
    class PlayerXMove(player: Player) : PlayerMove(player)
    class PlayerOMove(player: Player) : PlayerMove(player)


    abstract class PlayerWon(player: Player) : AbstractPlayerState(player)
    class NobodyWin : GameState
    class PlayerXWon(xPlayer: Player.XPlayer) : PlayerWon(xPlayer)
    class PlayerOWon(oPlayer: Player.OPlayer) : PlayerWon(oPlayer)

}