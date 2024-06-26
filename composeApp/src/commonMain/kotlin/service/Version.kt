package service

expect class Version {
    fun getVersion(): String
}
lateinit var appVersion: Version