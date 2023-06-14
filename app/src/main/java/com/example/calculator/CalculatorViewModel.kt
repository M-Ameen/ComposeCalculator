package com.example.calculator

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.plcoding.calculatorprep.CalculatorState

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())

    fun saveStateToSharedPreferences(context: Context) {
        val sharedPref = context.getSharedPreferences("CalculatorPreferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("Number1", state.number1)
            putString("Number2", state.number2)
            putString("Operation", state.operation?.symbol)
            apply()
        }
    }

    fun loadStateFromSharedPreferences(context: Context) {
        val sharedPref = context.getSharedPreferences("CalculatorPreferences", Context.MODE_PRIVATE)
        val number1 = sharedPref.getString("Number1", "")
        val number2 = sharedPref.getString("Number2", "")
        val operationSymbol = sharedPref.getString("Operation", null)

        val operation = when (operationSymbol) {
            "+" -> Operations.Add
            "-" -> Operations.Subtract
            "x" -> Operations.Multiply
            "/" -> Operations.Divide
            else -> null
        }

        state = CalculatorState(number1 = number1 ?: "", number2 = number2 ?: "", operation = operation)
    }
    fun onAction(action: Actions) {
        when(action) {
            is Actions.Number -> enterNumber(action.number)
            is Actions.Delete -> delete()
            is Actions.Clear -> state = CalculatorState()
            is Actions.Operation -> enterOperation(action.operation)
            is Actions.Decimal -> enterDecimal()
            is Actions.Calculate -> calculate()
        }
    }

    private fun enterOperation(operation: Operations) {
        if(state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun calculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if(number1 != null && number2 != null) {
            val result = when(state.operation) {
                is Operations.Add -> number1 + number2
                is Operations.Subtract -> number1 - number2
                is Operations.Multiply -> number1 * number2
                is Operations.Divide -> number1 / number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

    private fun delete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun enterDecimal() {
        if(state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        } else if(!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operation == null) {
            if(state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if(state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}