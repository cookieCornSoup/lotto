package com.example.lotto.repository

import com.example.lotto.data.remote.api.RetrofitInstance
import com.example.lotto.data.remote.model.LottoApiModel

class LottoRepository {

    suspend fun getRecentWinningNumber(number: Int): Result<LottoApiModel> {
        val response = RetrofitInstance.api.getRecentWinningNumber(number)
        if (response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error("error")
    }
}