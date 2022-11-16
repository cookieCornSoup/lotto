package com.example.lotto

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.lotto.data.remote.api.Status
import com.example.lotto.ui.lotto.LottoViewModel

class MainActivity : ComponentActivity() {

    private val lottoViewModel by lazy {
        ViewModelProvider(this).get(LottoViewModel::class.java)
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
            Column {
                var lottoNumber by remember { mutableStateOf("") }
                var date by remember { mutableStateOf("") }


                OutlinedTextField(
                    value = lottoNumber,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { lottoNumber = it },
                    label = { Text("회차") }
                )
                Button(
                    onClick = {
                        if (lottoNumber.isEmpty()) {
                            Toast.makeText(this@MainActivity, "값을 입력해주세요", Toast.LENGTH_LONG).show()
                        } else {
                            lottoViewModel.getRecentWinningNumber(lottoNumber.toInt())

                            lottoViewModel.lottoApiResponseLiveData.observe(this@MainActivity) { response ->
                                when (response.status) {
                                    Status.LOADING -> {

                                    }
                                    Status.SUCCESS -> {
                                        response.data?.let { lotto ->
                                            date = lotto.drwNoDate
//                                            binding.txtNumber.text =
//                                                "${lotto.drwtNo1} ${lotto.drwtNo2}  " +
//                                                        "${lotto.drwtNo3} ${lotto.drwtNo4} ${lotto.drwtNo5} ${lotto.drwtNo6} "
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
                Text(text = date)
            }

        }

    }

    @Composable
    fun StringResourceText(drwNoDate: String) {
        Text(text = drwNoDate)
    }

}



