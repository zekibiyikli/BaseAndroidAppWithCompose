package com.base.androidappwithcompose.core

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<VB : ViewDataBinding, BVM : BaseViewModel<*>>(
    viewModelClass: KClass<BVM>
) : ComponentActivity() {

    //Variables
    protected open val binding: VB by lazy {
        DataBindingUtil.setContentView(this, getLayoutId)
    }

    protected open val viewModel: BVM by lazy {
        ViewModelProvider(this)[viewModelClass.java]
    }

    @Inject
    lateinit var localData: LocalSharedPreferences

    abstract val getLayoutId: Int

    //Lifecycles
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        initView()
        initListeners()
        initObservers()
    }
    @CallSuper
    open fun initListeners() {
        //callSuperClass
    }

    @CallSuper
    open fun initObservers() {
        //callSuperClass
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun initView()

}