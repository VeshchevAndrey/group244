// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorScreen()
        }
    }
}

@Composable
fun CalculatorScreen(){
    val calculatorState = remember { mutableStateOf(CalculatorState()) }

    val buttons = arrayOf(
        stringArrayResource(R.array.row1),
        stringArrayResource(R.array.row2),
        stringArrayResource(R.array.row3),
        stringArrayResource(R.array.row4),
        stringArrayResource(R.array.row5),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().weight(2f),
            text = calculatorState.value.display,
            color = Color(0xFFFFFFFF),
            fontSize = 72.sp,
            textAlign = TextAlign.Right
        )

        val isOperator = arrayOf("/", "x", "-", "+", "=")

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                row.forEach { label ->
                    CalculatorButton(
                        buttonLabel = label,
                        color = (if (label in isOperator) Color(0xFFB71C1C) else Color(0xFFFFFFFF)),
                        textColor = (
                                if (label in isOperator) Color(0xFFFFFFFF) else Color(0xFF000000)
                                ),
                        modifier = Modifier.weight(if (label == "0") 2f else 1f)
                    ) { handleButtonClick(
                        button = label,
                        currentState = calculatorState.value,
                        updateState = {newState -> calculatorState.value = newState}
                    ) }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    buttonLabel: String,
    modifier: Modifier = Modifier,
    color: Color,
    textColor: Color,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = textColor),
        modifier = modifier.padding(1.dp).fillMaxHeight()
    ) {
        Text(
            text = buttonLabel,
            fontSize = 36.sp
        )
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
        "+", "-", "x", "/" -> {
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
        "x" -> a * b
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
