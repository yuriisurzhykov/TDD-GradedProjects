package com.yuriisurzhykov.tddgraded.tictactoe.data

interface GameState {
    class CrossPlayerMove : GameState
    class ZeroPlayerMove : GameState
    class CrossPlayerWin : GameState
    class ZeroPlayerWin : GameState
}