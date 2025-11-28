package com.base.androidappwithcompose.ui.fragment.notifications

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.androidappwithcompose.R
import com.base.androidappwithcompose.adapter.shimmer.ShimmerAdapter
import com.base.androidappwithcompose.adapter.user2.User2Adapter
import com.base.androidappwithcompose.core.BaseFragment
import com.base.androidappwithcompose.databinding.FragmentNotificationsBinding
import com.base.androidappwithcompose.model.UserModel
import com.base.androidappwithcompose.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>(
    NotificationsViewModel::class
) {

    private lateinit var userAdapter: User2Adapter

    //Layout
    override val getLayoutId: Int
        get() = R.layout.fragment_notifications

    //Lifecycles
    override fun initView() {
        showShimmer()
        setList()
        getData()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).showHideBottomNavigation(true)
    }

    override fun initListeners() {
        super.initListeners()
        with(binding){

        }
    }

    override fun initObservers() {
        super.initObservers()
    }

    private fun getData(){
        lifecycleScope.launch {
            viewModel.userFlow.collectLatest { pagingData ->
                userAdapter.submitData(pagingData)
            }
        }
    }

    //Functions
    private fun showShimmer(){
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = ShimmerAdapter()
    }

    private fun setList(){
        userAdapter = User2Adapter(
            ::userClickListener,
            ::userLongClickListener
        )
        binding.rvUsers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun userClickListener(user: UserModel?) {
        user?.let {
            findNavController().navigate(R.id.action_navigation_notifications_to_userDetailFragment)
        }
    }

    private fun userLongClickListener(user: UserModel?) {
        user?.let {}
    }

}