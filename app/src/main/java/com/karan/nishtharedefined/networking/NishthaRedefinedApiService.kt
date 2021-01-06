package com.karan.nishtharedefined.networking

import com.karan.nishtharedefined.model.ModelCategory
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import kotlin.collections.ArrayList

interface NishthaRedefinedApiService {
    @POST("category.php")
    fun getCategoryAsync(@Query("lang") ln: String): Deferred<ArrayList<ModelCategory>>
}