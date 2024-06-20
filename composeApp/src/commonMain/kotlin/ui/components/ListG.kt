package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import localtest.composeapp.generated.resources.Res
import localtest.composeapp.generated.resources.i2
import org.jetbrains.compose.resources.painterResource
import service.outerCon
import ui.router.Detail
import ui.types.PageCls

@Composable
fun ListG(
    p: PageCls,
    f: Boolean,
    sf: Boolean,
    tap: (url: String) -> Unit = {},
    checkTap:(id: String) -> Unit = {},
    logTap: (url: String) -> Unit = {},
) {
    val pl = if (p.id.toInt() % 2 == 0) Color(0xd6e4faff) else Color(0x8EB6F8FF)
    ListItem(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    tap(p.url)
                },
                onLongPress = {
                    logTap(p.url)
                }
            )
        },
        colors = ListItemDefaults.colors(pl),
        leadingContent = {
            Image(
                painter = painterResource(p.imgUrl ?: Res.drawable.i2),
                contentDescription = null,
                modifier =
                Modifier.size(50.dp)
            )
        },
        headlineContent = {
            Text(text = p.tit)
        },
        supportingContent = {
            Text(text = p.url, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }, trailingContent = {
            if (sf) Checkbox(checked = f, onCheckedChange = {
                checkTap(p.id)
            })
        })
}