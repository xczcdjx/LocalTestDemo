import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import service.appVersion
import service.outerCon
import service.storage
import storage.SettingsManager
import ui.router.Index

@Composable
@Preview
fun App() {
    MaterialTheme {
        val version = appVersion.getVersion()
        val setting = SettingsManager(storage.getStorage())
        var verS by remember { mutableStateOf(setting.version) }
        val hintContent =
            listOf("快捷去对应webView","宫格与列表视图", "自定义局域网地址")
        if (verS != version) {
            Dialog(onDismissRequest = {

            }) {
                Card(modifier = Modifier.size(300.dp).padding(10.dp)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("GW 1.0版本")
                        LazyColumn {
                            items(hintContent.size) {
                                Text("${it+1} .${hintContent[it]}")
                            }
                        }
                        TextButton(onClick = {
                            verS = version
                            setting.version = version
                        }) {
                            Text("我知道了")
                        }
                    }
                }
            }
        }
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