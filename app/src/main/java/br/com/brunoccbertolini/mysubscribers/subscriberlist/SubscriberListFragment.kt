package br.com.brunoccbertolini.mysubscribers.subscriberlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.brunoccbertolini.mysubscribers.R
import br.com.brunoccbertolini.mysubscribers.data.db.entity.SubscriberEntity
import kotlinx.android.synthetic.main.subscriber_list_fragment.*

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private lateinit var viewModel: SubscriberListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subscriberListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "Bruno", "bruno@hotmail.com"),
                SubscriberEntity(2,"raphaela", "raphaela@gmail.com"),
                SubscriberEntity(3,"Mateus", "mateus@live.com")
            )
        )
        recyclerViewSubscriber.run {
            setHasFixedSize(true)
            adapter = subscriberListAdapter
        }

    }


}