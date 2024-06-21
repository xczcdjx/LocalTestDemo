package ui.router

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

object Index : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
//        navigator.push(Detail)
        val page =
            rememberPagerState(initialPage = 0, pageCount = { 2 })
        HorizontalPager(
            state = page,
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(
                top = 32.dp,
                end = 32.dp,
                start = 32.dp,
                bottom = 32.dp
                // 使用底部高度
//                bottom = Device.getBottomSafeAreaHeight().dp + 32.dp
            )
        ) { pageIndex ->
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                when (pageIndex) {
                    0 -> First()
                    1 -> Second()
                    else -> {}
                }
            }
        }
    }
}