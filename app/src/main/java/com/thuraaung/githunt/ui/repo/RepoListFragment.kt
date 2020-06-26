package com.thuraaung.githunt.ui.repo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.thuraaung.githunt.R
import com.thuraaung.githunt.databinding.FragmentTrendingReposBinding
import com.thuraaung.githunt.utils.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RepoListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RepoViewModel by activityViewModels { viewModelFactory }

    private lateinit var binding : FragmentTrendingReposBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_trending_repos,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_repo, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_filter -> {
                findNavController().navigate(R.id.action_trendingRepos_to_languageFilter)
                true
            }
            R.id.action_daily -> {
                viewModel.filterBySince(SinceBy.DAILY)
                item.isChecked = !item.isChecked
                true
            }
            R.id.action_weekly -> {
                viewModel.filterBySince(SinceBy.WEEKLY)
                item.isChecked = !item.isChecked
                true
            }
            R.id.action_monthly -> {
                viewModel.filterBySince(SinceBy.MONTHLY)
                item.isChecked = !item.isChecked
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.listUpdateCallback = {
            binding.rvRepos.scheduleLayoutAnimation()
        }

        viewModel.itemClickCallback = { repo  ->
            val action = RepoListFragmentDirections.actionTrendingReposToRepoDetail(repo = repo)
            findNavController().navigate(action)
        }

        NetworkUtils.getNetworkLiveData(requireContext())
            .observe(viewLifecycleOwner, Observer { isConnected ->

                if (!isConnected) {

                    binding.tvConnection.text = getString(R.string.internet_disconnected)
                    binding.connectionLayout.apply {
                        alpha = 0f
                        show()
                        setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                        animate()
                            .alpha(1f)
                            .setDuration(ANIMATION_DURATION)
                            .setListener(null)
                    }
                } else {

                    binding.tvConnection.text = getString(R.string.internet_connected)
                    binding.connectionLayout.apply {

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

//    private fun showLoading() {
//        swLayout.isRefreshing = true
//        rvRepos.hide()
////        tvNoRepo.hide()
//    }
//
//    private fun hideLoading() {
//        swLayout.isRefreshing = false
//        rvRepos.show()
//    }
//
//    private fun showErrorPlaceHolder() {
////        tvNoRepo.show()
//        rvRepos.hide()
//    }

}
