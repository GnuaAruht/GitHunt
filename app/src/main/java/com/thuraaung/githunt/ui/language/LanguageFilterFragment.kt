package com.thuraaung.githunt.ui.language

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.ErrorState
import com.thuraaung.githunt.LoadingState
import com.thuraaung.githunt.R
import com.thuraaung.githunt.SuccessState
import com.thuraaung.githunt.base.BaseFragment
import com.thuraaung.githunt.base.BaseViewModelFactory
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.test.TestInjector
import com.thuraaung.githunt.ui.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LanguageFilterFragment : BaseFragment() {

    private lateinit var rvLanguage : RecyclerView
    private lateinit var searchView : SearchView

    private val languageAdapter : LanguageAdapter by lazy {
        LanguageAdapter()
    }

    private val repository : TrendingDataRepository by lazy {
        TestInjector.getTrendingRepository(requireContext())
    }

    private val viewModel : MainViewModel by activityViewModels { BaseViewModelFactory{ MainViewModel(repository) } }

    override val layoutRes: Int
        get() = R.layout.fragment_language_filter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvLanguage = view.findViewById(R.id.rv_language)
        searchView = view.findViewById(R.id.search)

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
                    Toast.makeText(context,"No Data",Toast.LENGTH_SHORT).show()
                    languageAdapter.updateItems(emptyList())
                }
                is SuccessState -> {
                    languageAdapter.updateItems(it.data)
                }
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getLanguages(if(query != null) "$query%" else "%")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getLanguages(if(newText != null) "$newText%" else "%")
                return true
            }
        })
    }

}
