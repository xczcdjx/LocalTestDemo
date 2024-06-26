package service

import android.content.Context

actual class Version(private val ctx: Context) {
    actual fun getVersion(): String {
        val packageInfo = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
        return packageInfo.versionName
    }
}