package com.example.lotto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
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


    @Preview
    @Composable
    fun LottoMain() {

        MaterialTheme {
            val typography = MaterialTheme.typography
            Column{
                SimpleOutlinedTextField()
                Button(onClick = { /*TODO*/ }) {
                    
                }
            }

        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun SimpleOutlinedTextField() {
        var text by remember { mutableStateOf("회차 번호") }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("회차") }
        )
    }
}



