package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

fun handleButtonClick(
    button: String, 
    currentState: CalculatorState, 
    updateState: (CalculatorState) -> Unit
){
    var newState = currentState

    when (button){
        ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
            if (newState.isNewNumber) {
                newState = newState.copy(
                    display = if (button == ".") "0." else button,
                    isNewNumber = false
                )
            }
            else {
                val newDisplay = if ((newState.display == "0") and (button != ".")) button else newState.display + button
                newState = newState.copy(display = newDisplay)
            }
        }
        "+", "-", "*", "/" -> {
            val currentNumber = newState.display.toDoubleOrNull() ?: 0.0

            newState = if ((newState.previousNumber == null) or (newState.currentOperator == null)){
                newState.copy(
                    previousNumber = currentNumber,
                    currentOperator = button,
                    isNewNumber = true
                )
            }
            else {
                val result = calculate(newState.previousNumber!!, currentNumber, newState.currentOperator!!)
                newState.copy(
                    display = result.toString().removeSuffix(".0"),
                    previousNumber = result,
                    currentOperator = button,
                    isNewNumber = true
                )
            }
        }
        "=" -> {
            val currentNumber = newState.display.toDoubleOrNull() ?: 0.0
            val result = calculate(newState.previousNumber!!, currentNumber, newState.currentOperator!!)

            newState = newState.copy(
                display = result.toString().removeSuffix(".0"),
                previousNumber = null,
                currentOperator = null,
                isNewNumber = true
            )
        }
        "+/-" -> {
            if (newState.display != "0"){
                val number = newState.display.toDoubleOrNull() ?: 0.0
                newState = newState.copy(display = (-number).toString())
            }
        }
        "%" -> {
            val number = newState.display.toDoubleOrNull() ?: 0.0
            newState = newState.copy(display = (number/100).toString())
        }
        "C" -> newState = CalculatorState()
    }
    updateState(newState)
}

fun calculate(a: Double, b: Double, operator: String): Double{
    return when (operator){
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b != 0.0) a / b else 0.0
        else -> b
    }
}

data class CalculatorState(
    val display: String = "0",
    val previousNumber: Double? = null,
    val currentOperator: String? = null,
    val isNewNumber: Boolean = true
)

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {

}
