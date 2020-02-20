package kr.puze.everywear.Utils

import android.content.Context
import android.widget.Toast

class ToastUtil(context: Context) {
    var stringUtil: StringUtil = StringUtil()
    var context = context

    fun short(message: String) {
        if (stringUtil.isEmpty(message))
            return
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            // ignore
        }
    }

    fun Long(message: String) {
        if (stringUtil.isEmpty(message))
            return
        try {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            // ignore
        }
    }
}