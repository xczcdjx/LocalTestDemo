package ui.types

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

data class PageCls(
    val id: String, val tit: String,
    val url: String, val imgUrl: DrawableResource?
)
data class PageClsM(
    val id: String, val tit: String,
    val url: String, val imgUrl: DrawableResource?,
    var check:Boolean
)
@Serializable
data class PageClsSet(
    val id: String, val tit: String,
    val url: String
)