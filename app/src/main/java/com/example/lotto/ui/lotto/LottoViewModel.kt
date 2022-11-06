package com.example.lotto.ui.lotto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotto.data.remote.api.LottoApiResponse
import com.example.lotto.repository.LottoRepository
import kotlinx.coroutines.launch


class LottoViewModel : ViewModel() {

    val lottoRepository = LottoRepository()

    private val _lottoApiResponseLiveData = MutableLiveData<LottoApiResponse>()
    val lottoApiResponseLiveData: MutableLiveData<LottoApiResponse> get() = _lottoApiResponseLiveData

    fun getRecentWinningNumber(number: Int) = viewModelScope.launch {
        _lottoApiResponseLiveData.value = LottoApiResponse.loading()

        when (val result = lottoRepository.getRecentWinningNumber(number)) {
            is com.example.lotto.repository.Result.Success -> {
                _lottoApiResponseLiveData.value = LottoApiResponse.success(result.data)
            }
            is com.example.lotto.repository.Result.Error -> {
                _lottoApiResponseLiveData.value = LottoApiResponse.error(result.error)
            }
        }

    }

}