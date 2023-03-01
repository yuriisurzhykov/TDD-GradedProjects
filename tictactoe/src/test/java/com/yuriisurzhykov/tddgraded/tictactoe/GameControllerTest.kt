package com.yuriisurzhykov.tddgraded.tictactoe

import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.GameController
import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.GameState
import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.Move
import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.Player
import org.junit.Assert.assertEquals
import org.junit.Test

class GameControllerTest {

    @Test
    fun test_game_controller_x_win_in_row() {
        val playerX = Player.XPlayer()
        val playerO = Player.OPlayer()
        val controller: GameController = GameController.Base()

        val move: Move = Move.Base(xCoordinate = 0, yCoordinate = 0)
        var expectedState: GameState = GameState.PlayerOMove(playerO)
        var controllerState = controller.makeMove(playerX, move)
        assertEquals(expectedState, controllerState)

        val move2: Move = Move.Base(xCoordinate = 0, yCoordinate = 1)
        controllerState = controller.makeMove(playerO, move2)
        expectedState = GameState.PlayerXMove(playerX)
        assertEquals(expectedState, controllerState)

        val move3: Move = Move.Base(xCoordinate = 1, yCoordinate = 0)
        controllerState = controller.makeMove(playerX, move3)
        expectedState = GameState.PlayerOMove(playerO)
        assertEquals(expectedState, controllerState)

        val move4: Move = Move.Base(xCoordinate = 2, yCoordinate = 2)
        controllerState = controller.makeMove(playerO, move4)
        expectedState = GameState.PlayerXMove(playerX)
        assertEquals(expectedState, controllerState)

        val move5: Move = Move.Base(xCoordinate = 2, yCoordinate = 0)
        controllerState = controller.makeMove(playerX, move5)
        expectedState = GameState.PlayerXWon(playerX)
        assertEquals(expectedState, controllerState)
    }

    @Test
    fun test_game_controller_x_win_diagonal() {
        val playerX = Player.XPlayer()
        val playerO = Player.OPlayer()
        val controller: GameController = GameController.Base()

        val move: Move = Move.Base(xCoordinate = 2, yCoordinate = 0)
        var expectedState: GameState = GameState.PlayerOMove(playerO)
        var controllerState = controller.makeMove(playerX, move)
        assertEquals(expectedState, controllerState)

        val move2: Move = Move.Base(xCoordinate = 0, yCoordinate = 1)
        controllerState = controller.makeMove(playerO, move2)
        expectedState = GameState.PlayerXMove(playerX)
        assertEquals(expectedState, controllerState)

        val move3: Move = Move.Base(xCoordinate = 1, yCoordinate = 1)
        controllerState = controller.makeMove(playerX, move3)
        expectedState = GameState.PlayerOMove(playerO)
        assertEquals(expectedState, controllerState)

        val move4: Move = Move.Base(xCoordinate = 2, yCoordinate = 2)
        controllerState = controller.makeMove(playerO, move4)
        expectedState = GameState.PlayerXMove(playerX)
        assertEquals(expectedState, controllerState)

        val move5: Move = Move.Base(xCoordinate = 0, yCoordinate = 2)
        controllerState = controller.makeMove(playerX, move5)
        expectedState = GameState.PlayerXWon(playerX)
        assertEquals(expectedState, controllerState)
    }

    @Test
    fun test_game_controller_nobody_win() {
        val playerX = Player.XPlayer()
        val playerO = Player.OPlayer()
        val controller: GameController = GameController.Base()

        val move: Move = Move.Base(xCoordinate = 0, yCoordinate = 0)
        var expectedState: GameState = GameState.PlayerOMove(playerO)
        var controllerState = controller.makeMove(playerX, move)
        assertEquals(expectedState, controllerState)

        val move2: Move = Move.Base(xCoordinate = 2, yCoordinate = 0)
        controllerState = controller.makeMove(playerO, move2)
        expectedState = GameState.PlayerXMove(playerX)
        assertEquals(expectedState, controllerState)

        val move3: Move = Move.Base(xCoordinate = 1, yCoordinate = 0)
        controllerState = controller.makeMove(playerX, move3)
        expectedState = GameState.PlayerOMove(playerO)
        assertEquals(expectedState, controllerState)

        val move4: Move = Move.Base(xCoordinate = 0, yCoordinate = 1)
        controllerState = controller.makeMove(playerO, move4)
        expectedState = GameState.PlayerXMove(playerX)
        assertEquals(expectedState, controllerState)

        val move5: Move = Move.Base(xCoordinate = 1, yCoordinate = 1)
        controllerState = controller.makeMove(playerX, move5)
        expectedState = GameState.PlayerOMove(playerO)
        assertEquals(expectedState, controllerState)

        val move6: Move = Move.Base(xCoordinate = 1, yCoordinate = 2)
        controllerState = controller.makeMove(playerO, move6)
        expectedState = GameState.PlayerXMove(playerX)
        assertEquals(expectedState, controllerState)

        val move7: Move = Move.Base(xCoordinate = 2, yCoordinate = 1)
        controllerState = controller.makeMove(playerX, move7)
        expectedState = GameState.PlayerOMove(playerO)
        assertEquals(expectedState, controllerState)

        val move8: Move = Move.Base(xCoordinate = 2, yCoordinate = 2)
        controllerState = controller.makeMove(playerO, move8)
        expectedState = GameState.PlayerXMove(playerX)
        assertEquals(expectedState, controllerState)

        val move9: Move = Move.Base(xCoordinate = 0, yCoordinate = 2)
        controllerState = controller.makeMove(playerX, move9)
        expectedState = GameState.NobodyWin()
        assertEquals(expectedState, controllerState)
    }
}