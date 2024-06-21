package ui.utils

import localtest.composeapp.generated.resources.Res
import localtest.composeapp.generated.resources.i2
import ui.types.PageCls
import ui.types.PageClsSet

fun PageClsSet.toPageCls(): PageCls {
    return PageCls(id, tit, url, Res.drawable.i2)
}
fun PageCls.toPageClsSet(): PageClsSet {
    return PageClsSet(id, tit, url)
}