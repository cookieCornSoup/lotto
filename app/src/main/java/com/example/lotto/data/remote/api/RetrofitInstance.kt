package com.example.lotto.data.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl("https://www.dhlottery.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }


        val api by lazy {
            retrofit.create(LottoApi::class.java)
        }
    }
}