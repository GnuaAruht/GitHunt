package com.thuraaung.githunt.ui.language

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.ErrorState
import com.thuraaung.githunt.LoadingState
import com.thuraaung.githunt.R
import com.thuraaung.githunt.SuccessState
import com.thuraaung.githunt.base.BaseViewModelFactory
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.test.TestInjector
import com.thuraaung.githunt.ui.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LanguageFilterFragment : Fragment() {

    private lateinit var rvLanguage : RecyclerView
    private val languageAdapter : LanguageAdapter by lazy {
        LanguageAdapter()
    }

    private val repository : TrendingDataRepository by lazy {
        TestInjector.getTrendingRepository(requireContext())
    }

    private val viewModel : MainViewModel by activityViewModels { BaseViewModelFactory{ MainViewModel(repository) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_language_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvLanguage = view.findViewById(R.id.rv_language)
        rvLanguage.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = languageAdapter
        }

        viewModel.languages.observe(viewLifecycleOwner, Observer {
            when(it) {
                is LoadingState -> {
                    Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
                }
                is ErrorState -> {
                    Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
                }
                is SuccessState -> {
                    languageAdapter.updateItems(it.data)
                }
            }
        })

        viewModel.getLanguages()
    }

}
