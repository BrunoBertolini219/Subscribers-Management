package br.com.brunoccbertolini.mysubscribers.subscriberlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import br.com.brunoccbertolini.mysubscribers.R
import br.com.brunoccbertolini.mysubscribers.data.db.AppDataBase
import br.com.brunoccbertolini.mysubscribers.data.db.dao.SubscriberDao
import br.com.brunoccbertolini.mysubscribers.repository.DatabaseDataSource
import br.com.brunoccbertolini.mysubscribers.repository.SubscriberRepository
import br.com.brunoccbertolini.mysubscribers.ui.subscriber.SubscriberViewModel
import kotlinx.android.synthetic.main.subscriber_list_fragment.*

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private val viewModel: SubscriberListViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val subscriberDao: SubscriberDao =
                    AppDataBase.getInstance(requireContext()).subscriberDao

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDao)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModelEvents()
    }

    private fun observerViewModelEvents() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) {
            val subscriberListAdapter = SubscriberListAdapter(it)

            with(recyclerViewSubscriber) {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }

        }

    }
