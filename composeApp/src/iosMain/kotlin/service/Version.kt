package service
import platform.Foundation.NSBundle
actual class Version {
    actual fun getVersion():String{
        val version = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String
        return version ?: "Unknown"
    }
}