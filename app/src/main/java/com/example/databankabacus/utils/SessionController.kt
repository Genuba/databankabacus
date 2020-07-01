package app.aplicaction.misionservi.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

public class SessionController(cntx: Context?) {
    private val prefs: SharedPreferences
    fun settoken(token: String?) {
        prefs.edit().putString("token", token).commit()
    }

    fun gettoken(): String? {
        return prefs.getString("token", "")
    }

    init {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx)
    }
}