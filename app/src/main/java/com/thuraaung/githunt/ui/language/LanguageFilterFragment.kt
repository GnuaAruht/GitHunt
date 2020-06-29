package com.thuraaung.githunt.ui.language

import androidx.appcompat.widget.SearchView
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.thuraaung.githunt.R
import com.thuraaung.githunt.base.BaseFragment
import com.thuraaung.githunt.databinding.FragmentLanguageFilterBinding
import com.thuraaung.githunt.ui.repo.RepoViewModel
import com.thuraaung.githunt.utils.hideSoftKeyboard
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LanguageFilterFragment : BaseFragment<FragmentLanguageFilterBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: LanguageViewModel by activityViewModels { viewModelFactory }
    private val repoViewModel: RepoViewModel by activityViewModels { viewModelFactory }

    override val layoutRes: Int
        get() = R.layout.fragment_language_filter

    override val viewModelVariable: Int
        get() = BR.viewModel

    override val callbackOnDataBinding = { binding: FragmentLanguageFilterBinding ->

        viewModel.listUpdateCallback = {
            binding.rvLanguage.scheduleLayoutAnimation()
        }

        viewModel.itemClickCallback = { language ->
            requireActivity().hideSoftKeyboard()
            repoViewModel.filterLanguageBy(language.urlParam)
            viewModel.getLanguages()
            findNavController().popBackStack(R.id.trendingReposFragment, false)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getLanguages(if (query != null) "$query%" else "%")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getLanguages(if (newText != null) "$newText%" else "%")
                return true
            }
        })

    }


}
