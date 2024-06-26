package service
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
actual class ExternalBrowserOpen {
    actual fun openUrl(url:String) {
        val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
        UIApplication.sharedApplication.openURL(nsUrl)
    }
}