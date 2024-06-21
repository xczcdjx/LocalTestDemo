package ui.router

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grid3x3
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import localtest.composeapp.generated.resources.Res
import localtest.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import service.outerCon
import service.storage
import storage.SettingsManager
import ui.components.ListG
import ui.types.PageCls
import ui.types.PageClsM
import ui.types.PageClsSet
import ui.utils.toPageCls
import ui.utils.toPageClsSet
import kotlin.random.Random

val tempArr: List<PageCls> = listOf(
    PageCls("1", "游戏前台", "https://gw100.mvkbnb.com", Res.drawable.g1),
    PageCls("2", "Aia (测试)", "https://dorabettest.mvkbnb.com/", Res.drawable.a1),
    PageCls("3", "Aia (越南)", "https://doravnd.mvkbnb.com", Res.drawable.a2),
    PageCls("4", "Aia (菲律宾)", "https://doraphp.mvkbnb.com", Res.drawable.a3),
    PageCls("5", "Aia (印度尼西亚)", "https://doraidn.mvkbnb.com ", Res.drawable.a4),
    PageCls("6", "Aia (泰国)", "https://dorathb.mvkbnb.com", Res.drawable.a5),
)

@Composable
fun First() {
    val navigator = LocalNavigator.currentOrThrow
    var listViewIs by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconToggleButton(checked = listViewIs, onCheckedChange = {
                listViewIs = it
            }) {
                Icon(
                    if (listViewIs) Icons.Default.List else Icons.Default.GridView,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            "开发网址",
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
            lineHeight = TextUnit(1f, TextUnitType.Em),
            textAlign = TextAlign.Center
        )
        Text(
            "轻触使用webview,长按使用内置游览器",
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                .padding(vertical = 15.dp),
            lineHeight = TextUnit(1f, TextUnitType.Em),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(15.dp))
        AnimatedContent(targetState = listViewIs) { isL ->
            if (isL) LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier, contentPadding = PaddingValues(10.dp)
            ) {
                items(tempArr.size) {
                    val t = tempArr[it]
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .height(100.dp)
                            .border(1.dp, Color.Blue, shape = RoundedCornerShape(5.dp))
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        navigator.push(Detail(t.url))
                                    },
                                    onLongPress = {
                                        outerCon.openUrl(t.url)
                                    }
                                )
                            },
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                    NImage(url = t.imgUrl, modifier = Modifier.width(60.dp))
                        Image(
                            modifier = Modifier.width(50.dp),
                            painter = painterResource(t.imgUrl ?: Res.drawable.i1),
                            contentDescription = null
                        )
                        Text(buildAnnotatedString {
                            val isStr = t.tit.startsWith("Aia")
                            withStyle(SpanStyle(fontSize = 13.sp)) {
                                if (isStr) {
                                    val str = t.tit.split(" ")
                                    append(str.first())
                                    withStyle(SpanStyle(fontSize = 10.sp, color = Color.Red)) {
                                        append(" " + str.last())
                                    }
                                } else append(t.tit)
                            }
                        })
                    }
                }
            }
            else LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tempArr.size) {
                    ListG(tempArr[it], false, false, tap = { url ->
                        navigator.push(Detail(url))
                    }) { url ->
                        outerCon.openUrl(url)
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Second() {
    val setting = SettingsManager(storage.getStorage())
    val list = setting.getPageList()
    val navigator = LocalNavigator.currentOrThrow
    var addShow by remember { mutableStateOf(false) }
    var delShow by remember { mutableStateOf(false) }
    var selfModal by remember {
        mutableStateOf<List<PageCls>>(list.map { it.toPageCls() })
    }
    var reflectSelf by remember {
        mutableStateOf<List<PageClsM>>(listOf())
    }
//    val list= mutableStateListOf<PageClsM>(PageClsM("","","",null,false))
    LaunchedEffect(selfModal.size) {
        println("self$selfModal")
        reflectSelf = selfModal.map { PageClsM(it.id, it.tit, it.url, it.imgUrl, false) }
        setting.savePageList(selfModal.map { it.toPageClsSet() })
    }
    //
    var titState by remember { mutableStateOf("") }
    val baseurl = "http://192.168.124."
    var ipState by remember { mutableStateOf("") }
    var portState by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = { addShow = true }) {
                titState = ""
                ipState = ""
                portState = ""
                errorMessage = ""
                Text("新增", color = Color.Green)
            }
            TextButton(onClick = {
                delShow = true
            }) {
                Text("删除", color = Color.Red)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            "自定义网址（仅局域）",
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
            lineHeight = TextUnit(1f, TextUnitType.Em),
            textAlign = TextAlign.Center
        )
        Text(
            "轻触使用webview,长按使用内置游览器",
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                .padding(vertical = 15.dp),
            lineHeight = TextUnit(1f, TextUnitType.Em),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(reflectSelf.size) { oit ->
                ListG(selfModal[oit], reflectSelf[oit].check, true, tap = { url ->
                    navigator.push(Detail(url))
                }, checkTap = { id ->
                    val i = selfModal.indexOfFirst { it.id == id }
                    reflectSelf = reflectSelf.mapIndexed { index, b ->
                        val t = b.copy()
                        /* t.check = index == i
                         t*/
                        if (i == index) t.check = !t.check
                        else t.check = false
                        t
                    }
                }) { url ->
                    outerCon.openUrl(url)
                }
            }
        }
//        Bott
        if (addShow) {
            Dialog(onDismissRequest = {
                addShow = false
            }) {
                Surface(
                    modifier = Modifier.width(500.dp).height(400.dp).padding(15.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("添加网址")
                        Column {
                            TextField(
                                value = titState,
                                onValueChange = { titState = it },
                                label = { Text("标题") },
                                modifier = Modifier.padding(horizontal = 4.dp).border(
                                    BorderStroke(1.dp, Color.Gray),
                                    RoundedCornerShape(4.dp)
                                ),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                )
                            )
                            Spacer(modifier = Modifier.padding(vertical = 5.dp))
                            TextField(
                                value = ipState,
                                onValueChange = {
                                    if (it.all { char -> char.isDigit() && it.length <= 2 }) ipState =
                                        it
                                },
                                label = { Text("端口地址") },
                                modifier = Modifier.padding(horizontal = 4.dp).border(
                                    BorderStroke(1.dp, Color.Gray),
                                    RoundedCornerShape(4.dp)
                                ),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                )
                            )
                            Spacer(modifier = Modifier.padding(vertical = 2.dp))
                            TextField(
                                value = portState,
                                onValueChange = {
                                    if (it.all { char -> char.isDigit() && it.length <= 5 }) portState =
                                        it
                                },
                                label = { Text("ip地址") },
                                modifier = Modifier.padding(horizontal = 4.dp).border(
                                    BorderStroke(1.dp, Color.Gray),
                                    RoundedCornerShape(4.dp)
                                ),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                )
                            )
                            Spacer(modifier = Modifier.padding(vertical = 3.dp))
                            Text(
                                buildAnnotatedString {
                                    append(baseurl)
                                    withStyle(SpanStyle(fontSize = 15.sp, color = Color.Red)) {
                                        append(ipState)
                                    }
                                    append(":")
                                    withStyle(SpanStyle(fontSize = 15.sp, color = Color.Blue)) {
                                        append(portState)
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            if (errorMessage.isNotEmpty()) {
                                Text(
                                    text = errorMessage,
                                    color = Color.Red,
                                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontSize = 15.sp
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            TextButton(onClick = {
                                if (titState.isBlank() || ipState.isBlank() || portState.isBlank()) {
                                    errorMessage = "数据不能为空"
                                } else {
                                    selfModal += PageCls(
                                        Random.nextInt().toString(),
                                        titState,
                                        baseurl + ipState + ":${portState}",
                                        Res.drawable.i2,
                                    )
                                    addShow = false
                                }
                            }) {
                                Text("确认")
                            }
                        }
                    }
                }
            }
        }
        if (delShow) {
            val delPage = reflectSelf.find { it.check }
            AlertDialog(onDismissRequest = {
                delShow = false
            }, title = {
                Text(text = if (delPage == null) "没有选择删除项" else "删除" + delPage.tit)
            }, confirmButton = {
                TextButton(onClick = {
                    if (delPage != null) {
                        selfModal = selfModal.filter { it.id != delPage.id }
                        reflectSelf = reflectSelf.filter { it.id != delPage.id }
                    }
                    delShow = false
                }) {
                    Text("确认")
                }
            })
        }
    }
}