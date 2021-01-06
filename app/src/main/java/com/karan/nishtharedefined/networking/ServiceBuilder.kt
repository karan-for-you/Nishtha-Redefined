package com.karan.nishtharedefined.networking

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://n20.ncert.org.in/nishtha/v2/"

val interceptor: HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
    .protocols(Collections.singletonList(Protocol.HTTP_1_1))
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS).build()

// Prefer JSON Converter over Moshi
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val gsonConverter = GsonConverterFactory.create()

// Call Adapter to be used with Coroutines
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
    .addConverterFactory(gsonConverter)
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()

object ServiceBuilder {
    val retrofitService : NishthaRedefinedApiService by lazy{
        retrofit.create(NishthaRedefinedApiService::class.java)
    }
}