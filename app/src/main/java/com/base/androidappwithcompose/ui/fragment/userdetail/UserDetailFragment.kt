package com.base.androidappwithcompose.ui.fragment.userdetail

import com.base.androidappwithcompose.R
import com.base.androidappwithcompose.core.BaseFragment
import com.base.androidappwithcompose.databinding.FragmentUserDetailBinding
import com.base.androidappwithcompose.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding, UserDetailViewModel>(
    UserDetailViewModel::class
) {

    //Layout
    override val getLayoutId: Int
        get() = R.layout.fragment_user_detail

    //Lifecycles
    override fun initView() {

    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).showHideBottomNavigation(false)
    }

    override fun initListeners() {
        super.initListeners()
    }

    override fun initObservers() {
        super.initObservers()
    }
}