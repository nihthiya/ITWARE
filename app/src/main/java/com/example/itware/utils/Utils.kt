package com.example.itware.utils

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import java.util.regex.Pattern

class Utils {
    companion object {
        /**
         * method is used to show Toast messages
         */
        fun showToast(context: Context, message: String) {
            Toast.makeText(context,message,LENGTH_SHORT).show()
        }

        /**
         * method is used for checking valid email id format.
         */
        fun isValidEmail(email: CharSequence?): Boolean {
            var isValid = true
            val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            if (!matcher.matches()) {
                isValid = false
            }
            return isValid
        }
    }
}