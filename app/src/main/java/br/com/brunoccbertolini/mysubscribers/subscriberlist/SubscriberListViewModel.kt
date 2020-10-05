package br.com.brunoccbertolini.mysubscribers.subscriberlist

import androidx.lifecycle.ViewModel
import br.com.brunoccbertolini.mysubscribers.repository.SubscriberRepository

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    val allSubscribersEvent = repository.getAllSubscribers()



}