package com.thuraaung.githunt.ui.language

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.thuraaung.githunt.R
import com.thuraaung.githunt.databinding.FragmentLanguageFilterBinding
import com.thuraaung.githunt.ui.repo.RepoViewModel
import com.thuraaung.githunt.utils.hideSoftKeyboard
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LanguageFilterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val languageViewModel: LanguageViewModel by activityViewModels { viewModelFactory }
    private val repoViewModel: RepoViewModel by activityViewModels { viewModelFactory }

    private lateinit var binding: FragmentLanguageFilterBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_language_filter, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = languageViewModel

        languageViewModel.listUpdateCallback = {
            binding.rvLanguage.scheduleLayoutAnimation()
        }

        languageViewModel.itemClickCallback = { language ->
            requireActivity().hideSoftKeyboard()
            repoViewModel.filterLanguageBy(language.urlParam)
            languageViewModel.getLanguages()
            findNavController().popBackStack(R.id.trendingReposFragment, false)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                languageViewModel.getLanguages(if (query != null) "$query%" else "%")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                languageViewModel.getLanguages(if (newText != null) "$newText%" else "%")
                return true
            }
        })

//        rvLanguage.apply {
//            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
//            adapter = languageAdapter
//        }
//
//        if(languageViewModel.languages.value !is SuccessState)
//            languageViewModel.getLanguages()
//
//        languageViewModel.languages.observe(viewLifecycleOwner, Observer {
//            when(it) {
//                is LoadingState -> {
////                    Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
//                }
//                is ErrorState -> {
////                    Toast.makeText(context,"No Data",Toast.LENGTH_SHORT).show()
//                    tvNoLanguage.show()
//                    rvLanguage.hide()
//                    languageAdapter.updateItems(emptyList())
//                }
//                is SuccessState -> {
//                    tvNoLanguage.hide()
//                    rvLanguage.show()
//                    languageAdapter.updateItems(it.data)
//                    rvLanguage.scheduleLayoutAnimation()
//                }
//            }
//        })


    }

}
