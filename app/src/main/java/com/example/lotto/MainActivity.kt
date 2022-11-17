package com.example.lotto

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.lotto.data.remote.api.Status
import com.example.lotto.ui.lotto.LottoComposeViewModel
import com.example.lotto.ui.lotto.LottoViewModel

class MainActivity : ComponentActivity() {

    private val lottoViewModel by lazy {
        ViewModelProvider(this).get(LottoViewModel::class.java)
    }

    private val lottoComposeViewModel by lazy {
        ViewModelProvider(this).get(LottoComposeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottoMain()
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnrememberedMutableState")
    @Preview
    @Composable
    fun LottoMain() {
        MaterialTheme {
            Column(modifier = Modifier.padding(16.dp)) {

                var drawingNumber by remember { mutableStateOf("") }
                var drawingDay by remember { mutableStateOf("") }
                var num by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = drawingNumber,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { drawingNumber = it },
                    label = { Text("회차") }
                )
                Button(
                    onClick = {
                        if (drawingNumber.isEmpty()) {
                            Toast.makeText(this@MainActivity, "값을 입력해주세요", Toast.LENGTH_LONG).show()
                        } else {
                            lottoViewModel.getRecentWinningNumber(drawingNumber.toInt())

                            lottoViewModel.lottoApiResponseLiveData.observe(this@MainActivity) { response ->
                                when (response.status) {
                                    Status.LOADING -> {
                                        lottoComposeViewModel
                                    }
                                    Status.SUCCESS -> {
                                        response.data?.let { lotto ->
                                            drawingDay = lotto.drwNoDate

                                            num = "${lotto.drwtNo1} ${lotto.drwtNo2} ${lotto.drwtNo3} ${lotto.drwtNo4} ${lotto.drwtNo5} ${lotto.drwtNo6} ${lotto.bnusNo}"

                                        }
                                    }
                                    Status.ERROR -> {

                                    }
                                }

                            }
                        }

                    }
                ) {
                    Text(text = "회차 조회")
                }
                Text(text = "회차 $drawingNumber")
                Text(text = "추첨일 $drawingDay")
                Text(text = "당첨 번호 $num")
            }

        }

    }




}



