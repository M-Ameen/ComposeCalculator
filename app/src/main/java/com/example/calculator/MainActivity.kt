@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme
import com.plcoding.calculatorprep.CalculatorAction
import com.plcoding.calculatorprep.CalculatorButton
import com.plcoding.calculatorprep.CalculatorOperation
import com.plcoding.calculatorprep.CalculatorViewModel

@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                        verticalArrangement = Arrangement.spacedBy(buttonSpacing),
                    ) {
                        Text(
                            text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp),
                            fontWeight = FontWeight.Light,
                            fontSize = 80.sp,
                            color = Color.White,
                            maxLines = 2
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                        ) {
                            CalculatorButton(
                                symbol = "AC",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(2f)
                                    .weight(2f)
                            ) {
                                viewModel.onAction(CalculatorAction.Clear)
                            }
                            CalculatorButton(
                                symbol = "Del",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Delete)
                            }
                            CalculatorButton(
                                symbol = "/",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                        ) {
                            CalculatorButton(
                                symbol = "7",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(7))
                            }
                            CalculatorButton(
                                symbol = "8",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(8))
                            }
                            CalculatorButton(
                                symbol = "9",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(9))
                            }
                            CalculatorButton(
                                symbol = "x",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                        ) {
                            CalculatorButton(
                                symbol = "4",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(4))
                            }
                            CalculatorButton(
                                symbol = "5",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(5))
                            }
                            CalculatorButton(
                                symbol = "6",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(6))
                            }
                            CalculatorButton(
                                symbol = "-",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                        ) {
                            CalculatorButton(
                                symbol = "1",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(1))
                            }
                            CalculatorButton(
                                symbol = "2",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(2))
                            }
                            CalculatorButton(
                                symbol = "3",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(3))
                            }
                            CalculatorButton(
                                symbol = "+",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                        ) {
                            CalculatorButton(
                                symbol = ".",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Decimal)
                            }
                            CalculatorButton(
                                symbol = "0",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(2f)
                                    .weight(2f)
                            ) {
                                viewModel.onAction(CalculatorAction.Number(0))
                            }

                            CalculatorButton(
                                symbol = "=",
                                color = Color.DarkGray,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .weight(1f)
                            ) {
                                viewModel.onAction(CalculatorAction.Calculate)
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun CalculatorLayout() {
//    var amountInput by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
//            .fillMaxWidth()
//    ) {
//        Text(
//            text = "amountInput",
//            style = TextStyle(
//                // color is used to provide green
//                // color to our text view.
//                color = Color.DarkGray,
//                fontWeight = FontWeight.DarkGray,
//            ),
//            modifier = Modifier.padding(all = Dp(20.0F)),
//
//            )
//        Row() {
//            Column() {
//                Row() {
//                    NumbersRow(number = "7")
//                    NumbersRow(number = "8")
//                    NumbersRow(number = "9")
//                }
//                Row() {
//                    NumbersRow(number = "6")
//                    NumbersRow(number = "5")
//                    NumbersRow(number = "4")
//                }
//                Row() {
//                    NumbersRow(number = "3")
//                    NumbersRow(number = "2")
//                    NumbersRow(number = "1")
//                }
//                Row() {
//                    NumbersRow(number = ".")
//                    NumbersRow(number = "0")
//                    NumbersRow(number = "=")
//                }
//            }
//            SymbolRow(clear = "ClR", div = "/", mul = "*", sub = "-", add = "+")
//        }
//
//
//    }
//}
//
//@Composable
//fun SymbolRow(clear: String, div: String, mul: String, sub: String, add: String) {
//    Column() {
//        Surface(
//            onClick = { /* Handle button click */ },
//            color = Color.Transparent,
//            modifier = Modifier.padding(all = Dp(4.0F))
//        ) {
//            Text(
//                text = clear,
//                style = TextStyle(
//                    color = Color.DarkGray,
//                    fontWeight = FontWeight.DarkGray,
//                ),
//                modifier = Modifier.padding(Dp(16.0F))
//            )
//        }
//        Surface(
//            onClick = { /* Handle button click */ },
//            color = Color.Transparent,
//            modifier = Modifier.padding(all = Dp(4.0F))
//        ) {
//            Text(
//                text = div,
//                style = TextStyle(
//                    color = Color.DarkGray,
//                    fontWeight = FontWeight.DarkGray,
//                ),
//                modifier = Modifier.padding(Dp(16.0F))
//            )
//        }
//        Surface(
//            onClick = { /* Handle button click */ },
//            color = Color.Transparent,
//            modifier = Modifier.padding(all = Dp(4.0F))
//        ) {
//            Text(
//                text = mul,
//                style = TextStyle(
//                    color = Color.DarkGray,
//                    fontWeight = FontWeight.DarkGray,
//                ),
//                modifier = Modifier.padding(Dp(16.0F))
//            )
//        }
//        Surface(
//            onClick = { /* Handle button click */ },
//            color = Color.Transparent,
//            modifier = Modifier.padding(all = Dp(4.0F))
//        ) {
//            Text(
//                text = sub,
//                style = TextStyle(
//                    color = Color.DarkGray,
//                    fontWeight = FontWeight.DarkGray,
//                ),
//                modifier = Modifier.padding(Dp(16.0F))
//            )
//        }
//        Surface(
//            onClick = { /* Handle button click */ },
//            color = Color.Transparent,
//            modifier = Modifier.padding(all = Dp(4.0F))
//        ) {
//            Text(
//                text = add,
//                style = TextStyle(
//                    color = Color.DarkGray,
//                    fontWeight = FontWeight.DarkGray,
//                ),
//                modifier = Modifier.padding(Dp(16.0F))
//            )
//        }
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun CalculatorPreview() {
//    CalculatorTheme {
//        CalculatorLayout()
//    }
//}
//
//@Composable
//fun NumbersRow(number: String) {
//    Surface(
//        onClick = { /* Handle button click */ },
//        color = Color.Transparent,
//        modifier = Modifier
//            .padding(all = Dp(4.0F))
//    ) {
//        Text(
//            text = number,
//            style = TextStyle(
//                color = Color.DarkGray,
//                fontWeight = FontWeight.DarkGray,
//            ),
//            modifier = Modifier.padding(Dp(16.0F))
//        )
//    }
//}