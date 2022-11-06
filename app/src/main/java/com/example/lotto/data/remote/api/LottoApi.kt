package com.example.lotto.data.remote.api

import com.example.lotto.data.remote.model.LottoApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoApi {

    @GET("/common.do")
    suspend fun getRecentWinningNumber(
        @Query("drwNo") drwNum: Int,
        @Query("method") method: String = "getLottoNumber"
    ): Response<LottoApiModel>
}