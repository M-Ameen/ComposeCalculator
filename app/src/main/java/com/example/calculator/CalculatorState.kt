package com.plcoding.calculatorprep

import com.example.calculator.Operations

data class CalculatorState(
    var number1: String = "",
    var number2: String = "",
    var operation: Operations? = null
)