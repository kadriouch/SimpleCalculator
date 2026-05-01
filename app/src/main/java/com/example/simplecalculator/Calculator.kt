package com.example.simplecalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Operations(val symbol: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("×"),
    DIVIDE("÷")
}

class Calculator(private val num1: Double, private val num2: Double) {
    fun compute(op: Operations): Double = when (op) {
        Operations.ADD -> num1 + num2
        Operations.SUBTRACT -> num1 - num2
        Operations.MULTIPLY -> num1 * num2
        Operations.DIVIDE -> num1 / num2
    }
}

@Composable
fun CalculatorScreen() {
    var operand1Input by remember { mutableStateOf("") }
    var operand2Input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Simple Calculator",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = operand1Input,
            onValueChange = { operand1Input = it },
            label = { Text("First Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = operand2Input,
            onValueChange = { operand2Input = it },
            label = { Text("Second Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Operations.entries.forEach { op ->
                Button(onClick = {
                    val num1 = operand1Input.toDoubleOrNull()
                    val num2 = operand2Input.toDoubleOrNull()
                    if (num1 != null && num2 != null) {
                        val calc = Calculator(num1, num2)
                        val computed = calc.compute(op)
                        result = if (computed % 1.0 == 0.0) computed.toLong().toString() else computed.toString()
                    }
                }) {
                    Text(text = op.symbol, fontSize = 20.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (result.isNotEmpty()) {
            Text(
                text = "Result: $result",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}