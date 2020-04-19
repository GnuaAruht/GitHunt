package com.thuraaung.githunt.ui.repo

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thuraaung.githunt.*
import com.thuraaung.githunt.base.BaseFragment
import com.thuraaung.githunt.base.BaseViewModelFactory
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.test.TestInjector
import com.thuraaung.githunt.ui.MainViewModel
import com.thuraaung.githunt.utils.hide
import com.thuraaung.githunt.utils.show
import kotlinx.android.synthetic.main.fragment_trending_repos.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.random.Random

@ExperimentalCoroutinesApi
class TrendingReposFragment : BaseFragment() {

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

    override val layoutRes: Int
        get() = R.layout.fragment_trending_repos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_repo,menu)
        super.onCreateOptionsMenu(menu, inflater)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvRepos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
            setHasFixedSize(true)
        }

        viewModel.getTrendingRepos()
        swLayout.setOnRefreshListener {
            viewModel.getTrendingRepos()
        }

        viewModel.reposList.observe(viewLifecycleOwner, Observer {
            when(it) {
                is LoadingState -> {
                    showLoading()
                }
                is ErrorState -> {
                    hideLoading()
                    showErrorPlaceHolder()
                }
                is SuccessState -> {
                    hideLoading()
                    repoAdapter.updateItems(it.data)
                }
            }
        })

    }

    private fun showLoading() {
        swLayout.isRefreshing = true
        rvRepos.hide()
    }

    private fun hideLoading() {
        swLayout.isRefreshing = false
        rvRepos.show()
    }

    private fun showErrorPlaceHolder() {
        Toast.makeText(context,"Error ",Toast.LENGTH_SHORT).show()
    }

}
