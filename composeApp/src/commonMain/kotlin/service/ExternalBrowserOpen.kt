package service

expect class ExternalBrowserOpen {
    fun openUrl(url: String)
}
lateinit var outerCon:ExternalBrowserOpen