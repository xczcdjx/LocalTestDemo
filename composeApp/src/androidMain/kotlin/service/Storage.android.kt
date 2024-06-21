package service

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual class Storage(private val ctx: Context) {
    actual fun getStorage(): Settings {
        val sharedPreferences = ctx.getSharedPreferences("MySettings", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(sharedPreferences)
    }
}