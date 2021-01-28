package com.karan.nishtharedefined.networking

import com.karan.nishtharedefined.model.ModelCategory
import com.karan.nishtharedefined.model.ModelCategoryModule
import com.karan.nishtharedefined.model.ModelLanguage
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import kotlin.collections.ArrayList

interface NishthaRedefinedApiService {
    @POST("category.php")
    fun getCategoryAsync(@Query("lang") ln: String): Deferred<ArrayList<ModelCategory>>

    @POST("module.php")
    fun getModuleAsync(@Query("lang") lang: String,
                       @Query("cat_id") cat_id: String): Deferred<ArrayList<ModelCategoryModule>>

    @POST("avail_lang.php")
    fun getLanguageAsync(@Query("mod_id") mod_id: String): Deferred<ArrayList<ModelLanguage>>


}