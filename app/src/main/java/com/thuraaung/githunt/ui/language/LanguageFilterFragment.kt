package com.thuraaung.githunt.ui.language

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thuraaung.githunt.utils.ErrorState
import com.thuraaung.githunt.utils.LoadingState
import com.thuraaung.githunt.R
import com.thuraaung.githunt.utils.SuccessState
import com.thuraaung.githunt.base.BaseFragment
import com.thuraaung.githunt.ui.MainViewModel
import com.thuraaung.githunt.utils.hideSoftKeyboard
import kotlinx.android.synthetic.main.fragment_language_filter.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LanguageFilterFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel : MainViewModel by activityViewModels { viewModelFactory }

    private val languageAdapter : LanguageAdapter by lazy {
        LanguageAdapter { item ->
            requireActivity().hideSoftKeyboard()
            viewModel.filterLanguageBy(item.urlParam)
            viewModel.getLanguages()
            findNavController().popBackStack(R.id.trendingReposFragment,false)
        }
    }

    override val layoutRes: Int
        get() = R.layout.fragment_language_filter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvLanguage.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = languageAdapter
        }

        if(viewModel.languages.value !is SuccessState)
            viewModel.getLanguages()

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
