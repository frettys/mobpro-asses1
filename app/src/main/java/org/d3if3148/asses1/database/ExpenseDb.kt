package org.d3if3148.asses1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import org.d3if3148.asses1.model.Calculate

@Database(entities = [Calculate::class], version = 1, exportSchema = false)
abstract class ExpenseDb : RoomDatabase() {
    abstract val dao: ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDb? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): ExpenseDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExpenseDb::class.java,
                        "expense.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}