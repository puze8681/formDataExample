package kr.puze.formdata

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kr.puze.everywear.Utils.ToastUtil
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("DEPRECATION")
class ServerUtil(context: Context) {

    var progressDialog: ProgressDialog = ProgressDialog(context)
    var toastUtil: ToastUtil = ToastUtil(context)
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    lateinit var retrofitService: RetrofitService

    fun retrofitSetting() {
        val gson: Gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://soylatte.kr:3000")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    fun checkNetwork(): Boolean {
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork.isConnectedOrConnecting
    }

    fun progress() {
        progressDialog.setMessage("서버 통신 중 . . .")
        progressDialog.show()
    }

    fun dismiss() {
        progressDialog.dismiss()
    }

    fun missNetwork() {
        toastUtil.short("인터넷 연결이 필요합니다.")
    }

    fun serverFailure() {
        toastUtil.short("서버 연동 실패.")
    }

    fun serverFailure(code: String) {
        toastUtil.short("서버 연동 실패 : $code")
    }
}