package com.base.androidappwithcompose.ui.fragment.home

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.androidappwithcompose.R
import com.base.androidappwithcompose.adapter.shimmer.ShimmerAdapter
import com.base.androidappwithcompose.adapter.user.UserAdapter
import com.base.androidappwithcompose.core.BaseFragment
import com.base.androidappwithcompose.data.flow.ApiResult
import com.base.androidappwithcompose.data.flow.ApiResultHandler
import com.base.androidappwithcompose.databinding.FragmentHomeBinding
import com.base.androidappwithcompose.model.ErrorModel
import com.base.androidappwithcompose.model.UserModel
import com.base.androidappwithcompose.model.response.UserResponse
import com.base.androidappwithcompose.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    HomeViewModel::class
) {

    //Layout
    override val getLayoutId: Int
        get() = R.layout.fragment_home

    private lateinit var userAdapter: UserAdapter

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
        with(binding){

        }
    }

    override fun initObservers() {
        super.initObservers()
        viewLifecycleOwner.lifecycleScope.launch {
            launch { viewModel.usersFlow.collectLatest{it?.let { observerUsers(it) }} }
        }
    }

    private fun showShimmer(){
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = ShimmerAdapter()
    }

    private fun setList(list: ArrayList<UserModel>){
        userAdapter = UserAdapter(
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
            findNavController().navigate(R.id.action_navigation_home_to_userDetailFragment)
        }
    }

    private fun userLongClickListener(user: UserModel?) {
        user?.let {

        }
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