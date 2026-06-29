package com.nj.space.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


val Context.dataStore by preferencesDataStore(name = "session_prefs")

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val DATASTORE_NAME = "session_prefs"

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

}

@Singleton
class SessionManager @Inject constructor(private val dataStore: DataStore<Preferences>){
        companion object{
            val USER_ID = stringPreferencesKey("user_id")
            val USER_EMAIL= stringPreferencesKey("user_email")
            val USERNAME = stringPreferencesKey("username")
            val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
            val THEME_MODE = stringPreferencesKey("theme_mode")
        }
    val userIdFlow: Flow<String?> = dataStore.data.map{it[USER_ID] }
    val emailFlow: Flow<String?> = dataStore.data.map { it[USER_EMAIL] }
    val usernameFlow: Flow<String?> = dataStore.data.map { it[USERNAME] }
    val isLoggedInFlow: Flow<Boolean> = dataStore.data.map { it[IS_LOGGED_IN] ?: false }
    val themeModeFlow: Flow<String> = dataStore.data.map { it[THEME_MODE] ?: "light" }

    suspend fun saveSession(userId: String, email: String, username: String) {
        dataStore.edit { prefs ->
            prefs[USER_ID] = userId
            prefs[USER_EMAIL] = email
            prefs[USERNAME] = username
            prefs[IS_LOGGED_IN] = true
        }
    }

    suspend fun clearSession() {
        dataStore.edit { it.clear() }
    }

    suspend fun setThemeMode(mode: String) {
        dataStore.edit { it[THEME_MODE] = mode }
    }
}