package com.thuraaung.githunt.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<DataBinding : ViewDataBinding> : Fragment() {

    abstract val viewModel : ViewModel
    private lateinit var mBinding : DataBinding
    abstract val viewModelVariable : Int

    abstract val callbackOnDataBinding : (DataBinding) -> Unit

    protected abstract val layoutRes : Int

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,layoutRes,container,false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.setVariable(viewModelVariable,viewModel)
        mBinding.executePendingBindings()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callbackOnDataBinding.invoke(mBinding)
    }
}