package kr.puze.formdata

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MultipartBody
import com.google.gson.JsonArray
import com.google.gson.JsonObject

class MainActivity : AppCompatActivity() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var serverUtil: ServerUtil
        lateinit var call: Call<Data>
        lateinit var call_string: Call<String>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serverUtil = ServerUtil(this@MainActivity)
        button.setOnClickListener {
            button(edit.text.toString())
        }
        button_json.setOnClickListener {
            postJson(edit_json.text.toString())
        }
    }

    private fun button(text: String) {
        if (serverUtil.checkNetwork()) {
            serverUtil.retrofitSetting()
            serverUtil.progress()

            val requestBodyMulti = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("sibal", text)
                    .build()

            call = serverUtil.retrofitService.post_id_check(requestBodyMulti)
            call.enqueue(object : Callback<Data> {
                override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                    serverUtil.dismiss()
                    if (response!!.isSuccessful) {
                        Log.d("ServerResponse", response.body()!!.sibal)
                        text_main.text = response.body()!!.sibal
                    } else {
                        serverUtil.serverFailure(response.code().toString())
                    }
                }

                override fun onFailure(call: Call<Data>?, t: Throwable?) {
                    Log.d("ServerFailure", t.toString())
                    serverUtil.dismiss()
                    serverUtil.serverFailure()
                }
            })
        } else {
            serverUtil.missNetwork()
        }
    }

    private fun postJson(id: String){
//        val jsonObject = JSONObject()
//        val data: ArrayList<Int> = ArrayList()
//        data.add(id.toInt())
//        jsonObject.put("productId", data)

        val jsonObject = JsonObject()
        val citiesArray = JsonArray()
        citiesArray.add(id.toInt())
        jsonObject.add("productId", citiesArray)

        Log.d("LOGTAG", jsonObject.toString())
        serverUtil.retrofitSetting()
        serverUtil.progress()
        call_string = serverUtil.retrofitService.post_json("application/json", jsonObject)
        call_string.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                serverUtil.dismiss()
                if (response!!.isSuccessful) {
                    Log.d("LOGTAG", response.body()!!.toString())
                    text_main_json.text = response.body()!!.toString()
                } else {
                    serverUtil.serverFailure(response.code().toString())
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                serverUtil.dismiss()
                serverUtil.serverFailure()
            }
        })
    }
}
