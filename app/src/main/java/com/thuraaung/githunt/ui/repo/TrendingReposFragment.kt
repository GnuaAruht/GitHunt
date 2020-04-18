package com.thuraaung.githunt.ui.repo

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.*
import com.thuraaung.githunt.base.BaseFragment
import com.thuraaung.githunt.base.BaseViewModelFactory
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.test.TestInjector
import com.thuraaung.githunt.ui.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TrendingReposFragment : BaseFragment() {

    private lateinit var rvRepos : RecyclerView
    private val repoAdapter : RepoAdapter by lazy {
        RepoAdapter()
    }

    private val repository : TrendingDataRepository by lazy {
        TestInjector.getTrendingRepository(requireContext())
    }

    private val viewModel : MainViewModel by activityViewModels {
        BaseViewModelFactory {
            MainViewModel(repository)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_repo,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_trending_repos

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvRepos = view.findViewById(R.id.rv_repos)
        rvRepos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
            setHasFixedSize(true)
        }

        viewModel.reposList.observe(viewLifecycleOwner, Observer {
            when(it) {
                is LoadingState -> {
                    Toast.makeText(context,"Loading ...",Toast.LENGTH_SHORT).show()
                }
                is ErrorState -> {
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                }
                is SuccessState -> {
                    repoAdapter.updateItems(it.data)
                }
            }
        })

        viewModel.getTrendingRepos()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.action_search -> {
                findNavController().navigate(R.id.action_trendingReposFragment_to_repoFilterFragment)
                true
            }
            R.id.action_daily -> {
                Toast.makeText(context,"Daily",Toast.LENGTH_SHORT).show()
                item.isChecked = !item.isChecked
                true
            }
            R.id.action_weekly -> {
                Toast.makeText(context,"Weekly",Toast.LENGTH_SHORT).show()
                item.isChecked = !item.isChecked
                true
            }
            R.id.action_yearly -> {
                Toast.makeText(context,"Yearly",Toast.LENGTH_SHORT).show()
                item.isChecked = !item.isChecked
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }

    }
}
