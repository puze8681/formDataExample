package kr.puze.formdata

import com.google.gson.JsonObject
import okhttp3.FormBody
import retrofit2.http.*
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call


interface RetrofitService {

    @POST("/")
    fun post_id_check(
            @Body body: RequestBody
    ): Call<Data>

    @POST("/")
    fun post_json(
            @Header("Content-Type") type: String,
            @Body body: JsonObject
    ): Call<String>
}