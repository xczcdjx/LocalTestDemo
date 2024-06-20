package service

import androidx.compose.runtime.Composable
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
actual class ExternalBrowserOpen {
    actual fun openUrl(context: Any, url: String) {
        val nsUrl = NSURL(string = url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(nsUrl)
        }
    }
}