package com.karan.nishtharedefined.networking

import com.karan.nishtharedefined.model.facetoface.ModelCategory
import com.karan.nishtharedefined.model.facetoface.ModelCategoryModule
import com.karan.nishtharedefined.model.facetoface.ModelLanguage
import com.karan.nishtharedefined.model.facetoface.ModelResourceType
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleDetail
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

    @GET("online_module_language.php")
    fun getNishthaOnlineLanguageAsync(): Deferred<ArrayList<NishthaLanguageModel>>

    @GET("online_module_list.php")
    fun getOnlineResourceAsync(@Query("lang") lang: String): Deferred<ArrayList<NishthaModuleModel>>

    @GET("online_module_detail.php")
    fun getOnlineResourceDetailAsync(@Query("lang") lang: String, @Query("mod_id") cat_id: String): Deferred<ArrayList<NishthaOnlineModuleDetail>>

}