package com.base.androidappwithcompose.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider
import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<BVM : BaseViewModel>(
    viewModelClass: KClass<BVM>
) : ComponentActivity() {

    protected open val viewModel: BVM by lazy {
        ViewModelProvider(this)[viewModelClass.java]
    }

    @Inject
    lateinit var localData: LocalSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initView()
        initListeners()
        initObservers()
    }

    @CallSuper
    open fun initListeners() {}

    @CallSuper
    open fun initObservers() {}

    abstract fun initView()
}