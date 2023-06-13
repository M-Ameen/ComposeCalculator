package com.example.calculator

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.plcoding.calculatorprep.CalculatorState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreCalculations(private val context: Context) {
    //make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("CurrentValue")
        val CURRENT_VALUE_KEY = stringPreferencesKey("current_value")


    }

//    get value
    val getCurrentValue: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[CURRENT_VALUE_KEY] ?: ""
        }


//    save value
    suspend fun saveValue(value: String) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_VALUE_KEY] = value.toString()
        }
    }

}