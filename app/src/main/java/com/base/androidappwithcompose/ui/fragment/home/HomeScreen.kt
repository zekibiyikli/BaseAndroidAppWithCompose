package com.base.androidappwithcompose.ui.fragment.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.base.androidappwithcompose.data.flow.ApiResult
import com.base.androidappwithcompose.model.UserModel
import com.base.androidappwithcompose.ui.activity.LocalMainActivity
import com.base.androidappwithcompose.ui.item.UserItem
import com.base.androidappwithcompose.ui.item.UserShimmerItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val activity = LocalMainActivity.current

    LaunchedEffect(Unit) {
        activity.showBottomBar()
    }

    val usersResult by viewModel.usersFlow.collectAsState()

    when (val result = usersResult) {
        is ApiResult.Success -> {
            Log.e("Zeki","Success")
            val list = result.data?.results
            list?.let {
                showList(users = it,onUserClick={user->
                    navController.navigate("user_detail/${user.login.uuid}")
                })
            }
        }
        is ApiResult.Error -> {
            Log.e("Zeki","Error")
        }
        is ApiResult.Loading -> {
            Log.e("Zeki","Loadinggg")
            LoadingShimmerList()
        }
        else -> {}
    }

}


@Composable
fun showList(users: ArrayList<UserModel>,onUserClick: (UserModel) -> Unit){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(users) { user ->
            UserItem(
                name = user.name.first + " " +user.name.last,
                city = user.location.city,
                imageUrl = user.picture.thumbnail,
                onClick = { onUserClick(user) }
            )
        }
    }
}

@Composable
fun LoadingShimmerList() {
    LazyColumn {
        items(7) {
            UserShimmerItem()
        }
    }
}

