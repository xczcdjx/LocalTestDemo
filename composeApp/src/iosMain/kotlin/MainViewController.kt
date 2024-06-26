import androidx.compose.ui.window.ComposeUIViewController
import service.Storage
import service.Version
import service.appVersion
import service.storage

fun MainViewController() = ComposeUIViewController {
    storage=Storage()
    appVersion= Version()
    App()
}