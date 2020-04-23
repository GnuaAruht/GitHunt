package com.thuraaung.githunt.ui.repo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thuraaung.githunt.R
import com.thuraaung.githunt.base.BaseFragment
import com.thuraaung.githunt.ui.MainViewModel
import com.thuraaung.githunt.utils.*
import kotlinx.android.synthetic.main.fragment_trending_repos.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TrendingReposFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel : MainViewModel by activityViewModels { viewModelFactory }

    private val repoAdapter: RepoAdapter by lazy {
        RepoAdapter { repo -> Toast.makeText(context,"${repo.name}",Toast.LENGTH_SHORT).show() }
    }

//    private val repoAdapter : MyAdapter by lazy {
//        MyAdapter()
//    }

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
            R.id.action_filter -> {
                findNavController().navigate(R.id.action_trendingReposFragment_to_repoFilterFragment)
                true
            }
            R.id.action_daily -> {
                viewModel.filterSinceBy(SINCE_DAILY)
                item.isChecked = !item.isChecked
                true
            }
            R.id.action_weekly -> {
                viewModel.filterSinceBy(SINCE_WEEKLY)
                item.isChecked = !item.isChecked
                true
            }
            R.id.action_monthly -> {
                viewModel.filterSinceBy(SINCE_MONTHLY)
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

        if (viewModel.reposList.value !is SuccessState)
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
                    Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                    showErrorPlaceHolder()
                    repoAdapter.updateItems(emptyList())
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
