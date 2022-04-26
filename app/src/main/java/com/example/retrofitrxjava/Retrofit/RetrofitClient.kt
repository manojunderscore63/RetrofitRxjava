package com.example.retrofitrxjava.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private var mInstance: RetrofitClient = RetrofitClient()
        private lateinit var retrofit: Retrofit

        @Synchronized fun getInstance(): RetrofitClient{
            if (mInstance==null){
                mInstance = RetrofitClient()
            }
            return mInstance
        }
    }

    init{
        retrofit = Retrofit.Builder().
        baseUrl("https://api.publicapis.org/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApi(): Api{
        return retrofit.create(Api::class.java)
    }
}