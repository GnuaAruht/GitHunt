package com.thuraaung.githunt.ui.repo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
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

    private val repoAdapter : RepoAdapter by lazy {
        RepoAdapter()
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
            R.id.action_filter -> {
                findNavController().navigate(R.id.action_trendingRepos_to_languageFilter)
                true
            }
            R.id.action_daily -> {
                viewModel.filterBySince(SINCE_DAILY)
                item.isChecked = !item.isChecked
                true
            }
            R.id.action_weekly -> {
                viewModel.filterBySince(SINCE_WEEKLY)
                item.isChecked = !item.isChecked
                true
            }
            R.id.action_monthly -> {
                viewModel.filterBySince(SINCE_MONTHLY)
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
            adapter = repoAdapter
            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(ListItemDecoration(context,10, 10))
        }

        swLayout.setOnRefreshListener {
            viewModel.getTrendingRepos()
        }

        viewModel.reposList.observe(viewLifecycleOwner, Observer {
            when(it) {
                is LoadingState -> {
                    showLoading()
                }
                is ErrorState -> {
                    repoAdapter.updateItems(emptyList())
                    showErrorPlaceHolder()
                    hideLoading()
                }
                is SuccessState -> {
                    hideLoading()
                    repoAdapter.updateItems(it.data)
                    rvRepos.scheduleLayoutAnimation()
                }
            }
        })

        NetworkUtils.getNetworkLiveData(requireContext()).observe(viewLifecycleOwner, Observer { isConnected ->

            if (!isConnected) {

                tvConnection.text = getString(R.string.internet_disconnected)
                connectionLayout.apply {
                    alpha = 0f
                    show()
                    setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                    animate()
                        .alpha(1f)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(null)
                }
            }
            else {

                tvConnection.text = getString(R.string.internet_connected)
                connectionLayout.apply {

                    setBackgroundColor(getColorRes(R.color.colorStatusConnected))
                    animate()
                        .alpha(0f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })

                }
            }
        })

    }

    private fun showLoading() {
        swLayout.isRefreshing = true
        rvRepos.hide()
        tvNoRepo.hide()
    }

    private fun hideLoading() {
        swLayout.isRefreshing = false
        rvRepos.show()
    }

    private fun showErrorPlaceHolder() {
        tvNoRepo.show()
        rvRepos.hide()
    }

}
