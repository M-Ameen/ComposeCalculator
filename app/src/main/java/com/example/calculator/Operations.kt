package com.example.calculator

sealed class Operations(var symbol: String) {
    object Add: Operations("+")
    object Subtract: Operations("-")
    object Multiply: Operations("x")
    object Divide: Operations("/")
}
