package br.com.brunoccbertolini.mysubscribers.ui.subscriber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.brunoccbertolini.mysubscribers.R
import br.com.brunoccbertolini.mysubscribers.data.db.AppDataBase
import br.com.brunoccbertolini.mysubscribers.data.db.dao.SubscriberDao
import br.com.brunoccbertolini.mysubscribers.extension.hideKeyboard
import br.com.brunoccbertolini.mysubscribers.repository.DatabaseDataSource
import br.com.brunoccbertolini.mysubscribers.repository.SubscriberRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.subscriber_fragment.*

class SubscriberFragment : Fragment(R.layout.subscriber_fragment) {


    private val viewModel: SubscriberViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val subscriberDao: SubscriberDao =
                    AppDataBase.getInstance(requireContext()).subscriberDao

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDao)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerEvents()
        setListeners()
    }

    private fun observerEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyBoard()
                }
            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) {stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()

        }
    }

    private fun clearFields() {
        input_email.text?.clear()
        input_name.text?.clear()
    }

    private fun hideKeyBoard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_subscriber.setOnClickListener{
            val name = input_name.text.toString()
            val email = input_email.text.toString()

            viewModel.addSubscriber(name, email)
        }
    }
}