@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp

                val context= LocalContext.current
                val scope= rememberCoroutineScope()
                val dataStore= StoreCalculations(context)
                val savedvalue= dataStore.getCurrentValue.collectAsState(initial = "")
                DisposableEffectWithLifecycle(
                    onResume = {
                        Toast.makeText(
                            this@MainActivity,
                            "Application started $savedvalue",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onPause = {
                        scope.launch {
                            val composedText = buildString {
                                append(state.number1)
                                append(state.operation?.symbol ?: "")
                                append(state.number2)
                            }
                            dataStore.saveValue(composedText)
                            Toast.makeText(this@MainActivity, "Application closed$composedText", Toast.LENGTH_SHORT).show()
                        }
                    }
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
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
                                .padding(vertical = 52.dp),
                            fontWeight = FontWeight.Light,
                            fontSize = 80.sp,
                            color = Color.Black,
                            maxLines = 2
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .background(Color.DarkGray)
                                    .weight(1f),
                                verticalArrangement = Arrangement.SpaceEvenly, // Spread elements evenly
                                horizontalAlignment = Alignment.Start
                            ) {
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
                                        viewModel.onAction(Actions.Number(7))
                                    }
                                    CalculatorButton(
                                        symbol = "8",
                                        color = Color.DarkGray,
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .weight(1f)
                                    ) {
                                        viewModel.onAction(Actions.Number(8))
                                    }
                                    CalculatorButton(
                                        symbol = "9",
                                        color = Color.DarkGray,
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .weight(1f)
                                    ) {
                                        viewModel.onAction(Actions.Number(9))
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
                                        viewModel.onAction(Actions.Number(4))
                                    }
                                    CalculatorButton(
                                        symbol = "5",
                                        color = Color.DarkGray,
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .weight(1f)
                                    ) {
                                        viewModel.onAction(Actions.Number(5))
                                    }
                                    CalculatorButton(
                                        symbol = "6",
                                        color = Color.DarkGray,
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .weight(1f)
                                    ) {
                                        viewModel.onAction(Actions.Number(6))
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
                                        viewModel.onAction(Actions.Number(1))
                                    }
                                    CalculatorButton(
                                        symbol = "2",
                                        color = Color.DarkGray,
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .weight(1f)
                                    ) {
                                        viewModel.onAction(Actions.Number(2))
                                    }
                                    CalculatorButton(
                                        symbol = "3",
                                        color = Color.DarkGray,
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .weight(1f)
                                    ) {
                                        viewModel.onAction(Actions.Number(3))
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
                                        viewModel.onAction(Actions.Decimal)
                                    }
                                    CalculatorButton(
                                        symbol = "0",
                                        color = Color.DarkGray,
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .weight(1f)
                                    ) {
                                        viewModel.onAction(Actions.Number(0))
                                    }

                                    CalculatorButton(
                                        symbol = "=",
                                        color = Color.DarkGray,
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .weight(1f)
                                    ) {
                                        viewModel.onAction(Actions.Calculate)
                                    }
                                }
                            }

                            Column(
                                modifier = Modifier,
                            ) {
                                CalculatorButton(
                                    symbol = "DEL",
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .weight(1f)
                                        .padding(top = 50.dp)
                                ) {
                                    viewModel.onAction(Actions.Delete)
                                }
                                CalculatorButton(
                                    symbol = "/",
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .weight(1f)
                                ) {
                                    viewModel.onAction(Actions.Operation(Operations.Divide))
                                }
                                CalculatorButton(
                                    symbol = "x",
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .weight(1f)
                                ) {
                                    viewModel.onAction(Actions.Operation(Operations.Multiply))
                                }
                                CalculatorButton(
                                    symbol = "-",
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .weight(1f)
                                ) {
                                    viewModel.onAction(Actions.Operation(Operations.Subtract))
                                }
                                CalculatorButton(
                                    symbol = "+",
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .weight(1f)
                                        .padding(bottom = 60.dp)

                                ) {
                                    viewModel.onAction(Actions.Operation(Operations.Add))
                                }
                            }
                        }


                    }
                }
            }
        }
    }
    @Composable
    private fun DisposableEffectWithLifecycle(
        onResume: () -> Unit,
        onPause: () -> Unit,
    ) {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current

//        Toast.makeText(context, "DisposableEffectWithLifecycle composition ENTER", Toast.LENGTH_SHORT).show()

        val currentOnResume by rememberUpdatedState(onResume)
        val currentOnPause by rememberUpdatedState(onPause)

        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_RESUME -> {
                        currentOnResume()
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        currentOnPause()
                    }
                    else -> {}
                }
            }

            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
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
