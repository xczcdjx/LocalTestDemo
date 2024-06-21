package service

import com.russhwolf.settings.Settings
import com.russhwolf.settings.AppleSettings
import platform.Foundation.NSUserDefaults
actual class Storage {
    actual fun getStorage(): Settings {
        val userDefaults = NSUserDefaults.standardUserDefaults()
        return AppleSettings(userDefaults)
    }

}