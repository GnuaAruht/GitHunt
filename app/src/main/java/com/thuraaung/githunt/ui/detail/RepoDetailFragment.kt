package com.thuraaung.githunt.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thuraaung.githunt.R
import kotlinx.android.synthetic.main.fragment_repo_detail.*


class RepoDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        det_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        det_toolbar.title = "Repository Detail"
        det_toolbar.inflateMenu(R.menu.menu_rep_det)
//        det_toolbar.setOnMenuItemClickListener { item ->
//
//        }
    }


}