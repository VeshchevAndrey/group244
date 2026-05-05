package com.example.application244

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.taskDataStore: DataStore<Preferences> by preferencesDataStore(name = "tasks")

class TaskRepository(private val context: Context) {
    private val TASKS_KEY = stringPreferencesKey("my_tasks")

    suspend fun saveTasks(tasks: List<Task>){
        context.taskDataStore.edit {
            val json = Json.encodeToString(tasks)
            it[TASKS_KEY] = json
        }
    }

    fun getTasks(): Flow<List<Task>> = context.taskDataStore.data.map {
        val json = it[TASKS_KEY] ?: "[]"
        try {
            Json.decodeFromString<List<Task>>(json)
        } catch (e: Exception) {
            emptyList()
        }
    }
}