package br.com.reconecta.core

import android.content.Context
import android.content.SharedPreferences
import br.com.reconecta.R
import br.com.reconecta.api.model.auth.UserResponse

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_SESSION = "user_session"
    }

    fun clear(){
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.apply()
    }

    fun saveUserSession(user: UserResponse){
        val editor = prefs.edit()
        editor.putString(USER_SESSION, createGsonSerializer().toJson(user))
        editor.apply()
    }

    fun fetchUserInfo(): UserResponse? {
        val sessionUser =  prefs.getString(USER_SESSION, null)
        return sessionUser?.let { createGsonSerializer().fromJson(it, UserResponse::class.java) }
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}