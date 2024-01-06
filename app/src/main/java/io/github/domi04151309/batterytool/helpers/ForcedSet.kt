package io.github.domi04151309.batterytool.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.json.JSONArray

class ForcedSet(private val preferences: SharedPreferences) : HashSet<String>() {
    companion object {
        @java.io.Serial
        private const val serialVersionUID = 1L

        @Volatile
        private var instance: ForcedSet? = null

        fun getInstance(context: Context): ForcedSet {
            return instance ?: synchronized(this) {
                instance ?: ForcedSet(PreferenceManager.getDefaultSharedPreferences(context)).also {
                    instance = it
                }
            }
        }
    }

    init {
        val forcedJson =
            JSONArray(
                preferences.getString(P.PREF_FORCED_LIST, P.PREF_FORCED_LIST_DEFAULT),
            )
        for (i in 0 until forcedJson.length()) {
            add(forcedJson.getString(i))
        }
    }

    fun save() {
        val json = JSONArray()
        forEach {
            json.put(it)
        }
        preferences.edit().putString(P.PREF_FORCED_LIST, json.toString()).apply()
    }
}