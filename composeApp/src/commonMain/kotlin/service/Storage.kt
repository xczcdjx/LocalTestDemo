package service

import com.russhwolf.settings.Settings
expect class Storage {
   fun getStorage(): Settings
}
lateinit var storage:Storage