package com.base.androidappwithcompose.ui.fragment.dashboard

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.androidappwithcompose.R
import com.base.androidappwithcompose.adapter.shimmer.ShimmerAdapter
import com.base.androidappwithcompose.adapter.user3.User3Adapter
import com.base.androidappwithcompose.core.BaseFragment
import com.base.androidappwithcompose.data.flow.ApiResult
import com.base.androidappwithcompose.data.flow.ApiResultHandler
import com.base.androidappwithcompose.databinding.FragmentDashboardBinding
import com.base.androidappwithcompose.model.ErrorModel
import com.base.androidappwithcompose.model.UserModel
import com.base.androidappwithcompose.model.response.UserResponse
import com.base.androidappwithcompose.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(
    DashboardViewModel::class
) {

    private lateinit var userAdapter: User3Adapter

    //Layout
    override val getLayoutId: Int
        get() = R.layout.fragment_dashboard

    //Lifecycles
    override fun initView() {
        showShimmer()
        viewModel.getRandomUsers()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).showHideBottomNavigation(true)
    }

    override fun initListeners() {
        super.initListeners()
    }

    override fun initObservers() {
        super.initObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            launch { viewModel.usersFlow.collectLatest{it?.let { observerUsers(it) }} }
        }
    }

    //Functions
    private fun showShimmer(){
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = ShimmerAdapter()
    }

    private fun setList(list: ArrayList<UserModel>){
        userAdapter = User3Adapter(
            list,
            ::userClickListener,
            ::userLongClickListener,
            requireContext()
        )
        binding.rvUsers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun userClickListener(user: UserModel?) {
        user?.let {
            findNavController().navigate(R.id.action_navigation_dashboard_to_userDetailFragment)
        }
    }

    private fun userLongClickListener(user: UserModel?) {
        user?.let {}
    }

    //Observers
    private fun observerUsers(data:ApiResult<UserResponse, ErrorModel>){
        ApiResultHandler<UserResponse?, ErrorModel>(
            onSuccess = {
                it?.results?.let { result->
                    setList(result)
                }
            },
            onFailure = {

            }
        ).handleApiResult(data)
    }

}