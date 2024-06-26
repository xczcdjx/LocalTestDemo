package ui.router

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState

class Detail(val str: String) : Screen {
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val navigator = rememberWebViewNavigator()
        Column(modifier = Modifier.fillMaxSize()) {
            val state = rememberWebViewState(str)
            DisposableEffect(Unit) {
                state.webSettings.apply {
                    isJavaScriptEnabled = true
                    androidWebSettings.domStorageEnabled = true
                }
                onDispose {

                }
            }
            /*TopAppBar(
                title = { Text(text = "Sample") },
                navigationIcon = {
                    if (navigator.canGoBack) {
                        IconButton(onClick = { navigator.navigateBack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )*/
            Box(modifier = Modifier.background(Color.White)) {
                val loadingState = state.loadingState
                WebView(
                    state = state,
                    modifier = Modifier
                        .fillMaxSize(),
                    navigator = navigator,
                )
                when (loadingState) {
                    is LoadingState.Initializing -> Text(text = "Initial")
                    is LoadingState.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            /*CircularProgressIndicator(
                                progress = loadingState.progress,
                                color = Color.Red, strokeWidth = 3.dp
                            )*/
                            CircularProgressIndicator(color = Color.Red, strokeWidth = 3.dp)
                        }
                    }

                    else -> {
//                    Text(text = "1111")
                    }
                }
            }
        }
    }
}
