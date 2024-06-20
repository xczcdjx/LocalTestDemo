import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import service.outerCon
import ui.router.Index

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(Index) {
            SlideTransition(it)
        }
        /* Column {
             TextButton(onClick = {
                 outerCon.openUrl("https://baidu.com")
             }){
                 Text("open")
             }
             Index()
         }*/
    }
}