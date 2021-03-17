package com.karan.nishtharedefined.networking

import com.karan.nishtharedefined.const.ApiConstants
import com.karan.nishtharedefined.model.facetoface.ModelCategory
import com.karan.nishtharedefined.model.facetoface.ModelCategoryModule
import com.karan.nishtharedefined.model.facetoface.ModelLanguage
import com.karan.nishtharedefined.model.facetoface.ModelResourceType
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleResourceModel
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

    @POST(ApiConstants.CATEGORY)
    fun getCategoryAsync(
        @Query(ApiConstants.LANG) ln: String
    ): Deferred<ArrayList<ModelCategory>>

    @POST(ApiConstants.MODULES)
    fun getModuleAsync(
        @Query(ApiConstants.LANG) lang: String,
        @Query(ApiConstants.CAT_ID) cat_id: String
    ): Deferred<ArrayList<ModelCategoryModule>>

    @POST(ApiConstants.AVAILABLE_LANG)
    fun getLanguageAsync(
        @Query(ApiConstants.MOD_ID) mod_id: String
    ): Deferred<ArrayList<ModelLanguage>>

    @POST(ApiConstants.RESOURCE)
    fun getResourceTypeAsync(
        @Query(ApiConstants.LANG) lang: String,
        @Query(ApiConstants.MOD_ID) mod_id: String
    ): Deferred<ArrayList<ModelResourceType>>

    @GET(ApiConstants.ONLINE_MODULE_LANGUAGE)
    fun getNishthaOnlineLanguageAsync(): Deferred<ArrayList<NishthaLanguageModel>>

    @GET(ApiConstants.ONLINE_MODULE_LIST)
    fun getOnlineResourceAsync(
        @Query(ApiConstants.LANG) lang: String
    ): Deferred<ArrayList<NishthaModuleModel>>

    @GET(ApiConstants.ONLINE_MODULE_DETAIL)
    fun getOnlineResourceDetailAsync(
        @Query(ApiConstants.LANG) lang: String?,
        @Query(ApiConstants.MOD_ID) modId: String?
    ): Deferred<ArrayList<NishthaOnlineModuleResourceModel>>

}