package com.thuraaung.githunt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.*
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.test.TestInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TrendingReposFragment : Fragment() {

    private lateinit var rvRepos : RecyclerView
    private val repoAdapter : ReposAdapter by lazy {
        ReposAdapter()
    }

    private val repository : TrendingDataRepository by lazy {
        TestInjector.getTrendingRepository(requireContext())
    }

    private val viewModel : MainViewModel by activityViewModels { BaseViewModelFactory{ MainViewModel(repository) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending_repos, container, false)
    }

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
                    repoAdapter.updateList(it.data)
                }
            }
        })

        viewModel.getTrendingRepos()
    }
}
