package com.kotlinstudy.kotlin_pydio

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {
    @FormUrlEncoded
    @POST("android_log_inset_php.php")
    fun getUser(
        @Field("useremail")
        email: String
    ): Call<User>
}