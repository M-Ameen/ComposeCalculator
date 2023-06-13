package com.plcoding.calculatorprep

import com.example.calculator.Operations

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: Operations? = null
)