package kr.puze.everywear.Utils

import android.widget.EditText

class StringUtil {
    fun isEmpty(str: String?): Boolean {
        return str == null || str.trim { it <= ' ' }.isEmpty()
    }

    fun isAnyEmpty(str1: String, str2: String): Boolean {
        return isEmpty(str1) || isEmpty(str2)
    }

    fun isAnyEmpty(str1: String, str2: String, str3: String, str4: String): Boolean{
        return isEmpty(str1) || isEmpty(str2) || isEmpty(str3) || isEmpty(str4)
    }

    fun isAllEmpty(str1: String, str2: String): Boolean {
        return isEmpty(str1) && isEmpty(str2)
    }

    fun makeString(str: EditText): String{
        return str.text.toString()
    }
}