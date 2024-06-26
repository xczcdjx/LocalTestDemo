package storage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ui.types.PageClsSet

class SettingsManager(private val settings: Settings) {
    var someString: String
        get() = settings.getString("someString", "")
        set(value) {
            settings.putString("someString", value)
        }
    var someInt: Int
        get() = settings.getInt("someInt", 0)
        set(value) {
            settings.putInt("someInt", value)
        }
    var version: String
        get() = settings.getString("version","")
        set(v)=settings.putString("version",v)
    fun clear() {
        settings.clear()
    }
    fun savePageList(pageList: List<PageClsSet>) {
        val jsonString = Json.encodeToString(pageList)
        settings.putString("pageList", jsonString)
    }
    fun getPageList(): List<PageClsSet> {
        val jsonString = settings.getString("pageList", "")
        return if (jsonString.isNotEmpty()) {
            Json.decodeFromString(jsonString)
        } else {
            emptyList()
        }
    }
}
