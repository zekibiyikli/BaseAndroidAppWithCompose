package com.base.androidappwithcompose.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<VB : ViewDataBinding, out BVM : BaseViewModel<BaseRepository>>(
    viewModelClass: KClass<BVM>
) : Fragment() {

    //Variables
    abstract val getLayoutId: Int
    open val binding: VB by lazy {
        DataBindingUtil.inflate(layoutInflater, getLayoutId, null, false)
    }
    protected open val viewModel: BVM by lazy {
        ViewModelProvider(this)[viewModelClass.java]
    }

    @Inject
    lateinit var localData: LocalSharedPreferences

    //Lifecycles
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        initListeners()
        initObservers()
        return binding.root
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    @CallSuper
    open fun initListeners() {
        /*do nothing*/
    }

    @CallSuper
    open fun initObservers() {
        /*do nothing*/
    }

    abstract fun initView()

}