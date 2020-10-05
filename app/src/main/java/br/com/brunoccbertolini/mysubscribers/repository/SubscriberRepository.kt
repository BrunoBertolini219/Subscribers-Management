package br.com.brunoccbertolini.mysubscribers.repository

import androidx.lifecycle.LiveData
import br.com.brunoccbertolini.mysubscribers.data.db.entity.SubscriberEntity

interface SubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updateSubscriber(id: Long, name: String, email: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscriber()

    fun getAllSubscribers(): LiveData<List<SubscriberEntity>>
}