package br.com.pokedex.local_storage.prefs

interface PreferencesDataSource {

    fun getString(key: String, defValue: String? = null): String?
    fun putString(key: String, value: String?, encrypt: Boolean = false)
    fun getBoolean(key: String, defValue: Boolean): Boolean
    fun putBoolean(key: String, value: Boolean)
    fun getInt(key: String, defValue: Int): Int
    fun putInt(key: String, value: Int)
    fun getLong(key: String, defValue: Long): Long
    fun putLong(key: String, value: Long)

}