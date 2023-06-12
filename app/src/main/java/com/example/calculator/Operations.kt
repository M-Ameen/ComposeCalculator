package com.plcoding.calculatorprep

sealed class Operations(val symbol: String) {
    object Add: Operations("+")
    object Subtract: Operations("-")
    object Multiply: Operations("x")
    object Divide: Operations("/")
}
