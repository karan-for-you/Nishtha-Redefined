package com.karan.nishtharedefined.networking

import com.karan.nishtharedefined.model.ModelCategory
import com.karan.nishtharedefined.model.ModelCategoryModule
import com.karan.nishtharedefined.model.ModelLanguage
import com.karan.nishtharedefined.model.ModelResourceType
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface NishthaRedefinedApiService {

    @GET
    fun getResource(@Url fileUrl: String): Call<ResponseBody>

    @POST("category.php")
    fun getCategoryAsync(@Query("lang") ln: String): Deferred<ArrayList<ModelCategory>>

    @POST("module.php")
    fun getModuleAsync(@Query("lang") lang: String,
                       @Query("cat_id") cat_id: String): Deferred<ArrayList<ModelCategoryModule>>

    @POST("avail_lang.php")
    fun getLanguageAsync(@Query("mod_id") mod_id: String): Deferred<ArrayList<ModelLanguage>>

    @POST("resource.php")
    fun getResourceTypeAsync(@Query("lang") lang: String,
    @Query("mod_id") mod_id : String): Deferred<ArrayList<ModelResourceType>>

}