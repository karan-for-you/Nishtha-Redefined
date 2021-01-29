package com.karan.nishtharedefined.networking

import com.karan.nishtharedefined.model.ModelCategory
import com.karan.nishtharedefined.model.ModelCategoryModule
import com.karan.nishtharedefined.model.ModelLanguage
import com.karan.nishtharedefined.model.ModelResourceType
import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.http.Query

interface NishthaRedefinedApiService {
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