package ui.types

import org.jetbrains.compose.resources.DrawableResource

data class PageCls(
    val id: String, val tit: String,
    val url: String, val imgUrl: DrawableResource?
)