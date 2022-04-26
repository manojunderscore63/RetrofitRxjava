package com.example.retrofitrxjava.Retrofit

import com.example.retrofitrxjava.model.DataModel
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface Api {

    @GET("entries")
    fun getData(): Observable<JsonObject>
}