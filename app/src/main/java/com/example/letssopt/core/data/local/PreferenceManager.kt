package com.example.letssopt.core.data.local

import android.content.Context
import androidx.core.content.edit

object PreferenceManager {
    private const val PREF_NAME = "my_prefs"

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUser(context: Context, email: String, pw: String) {
        getPrefs(context).edit {
            putString("email", email)
            putString("pw", pw)
        }
    }

    fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        getPrefs(context).edit { putBoolean("is_logged_in", isLoggedIn) }
    }

    fun getLoggedIn(context: Context): Boolean =
        getPrefs(context).getBoolean("is_logged_in", false)

    fun getUserData(context: Context): Pair<String?, String?> {
        val prefs = getPrefs(context)
        return prefs.getString("email", "") to prefs.getString("pw", "")
    }
}
