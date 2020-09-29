package br.com.brunoccbertolini.mysubscribers.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.brunoccbertolini.mysubscribers.data.db.dao.SubscriberDao
import br.com.brunoccbertolini.mysubscribers.data.db.entity.SubscriberEntity
import java.security.AccessControlContext

@Database (entities = [SubscriberEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract val subscriberDao: SubscriberDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            synchronized(this){
                var instance: AppDataBase? = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "app_database"
                    ).build()
                }
            return instance
            }
        }
    }

}