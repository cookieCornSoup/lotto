package com.example.lotto.ui.lotto

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel

class LottoComposeViewModel : ViewModel() {

    @Composable
    private fun CircularProgressAnimated(){
        val progressValue = 0.75f
        val infiniteTransition = rememberInfiniteTransition()

        val progressAnimationValue by infiniteTransition.animateFloat(
            initialValue = 0.0f,
            targetValue = progressValue,animationSpec = infiniteRepeatable(animation = tween(900))
        )

        CircularProgressIndicator(progress = progressAnimationValue)
    }
}