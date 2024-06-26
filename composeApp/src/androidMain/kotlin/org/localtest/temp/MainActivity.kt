package org.localtest.temp

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import service.ExternalBrowserOpen
import service.Storage
import service.Version
import service.appVersion
import service.outerCon
import service.storage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outerCon = ExternalBrowserOpen(this)
        storage = Storage(this)
        appVersion = Version(this)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}