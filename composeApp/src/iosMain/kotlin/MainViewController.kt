import androidx.compose.ui.window.ComposeUIViewController
import service.Storage
import service.storage

fun MainViewController() = ComposeUIViewController {
    storage=Storage()
    App()
}