package com.base.androidappwithcompose.ui.fragment.notifications

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.base.androidappwithcompose.model.UserModel
import com.base.androidappwithcompose.ui.activity.LocalMainActivity
import com.base.androidappwithcompose.ui.item.UserItem
import com.base.androidappwithcompose.ui.item.UserShimmerItem

@Composable
fun NotificationsScreen(
    viewModel: NotificationsViewModel = hiltViewModel(),
    navController: NavController
) {
    val activity = LocalMainActivity.current

    LaunchedEffect(Unit) {
        activity.showBottomBar()
    }

    val users = viewModel.userFlow.collectAsLazyPagingItems()

    showList(users){
        navController.navigate("user_detail/${it.login.uuid}")
    }
}


@Composable
fun showList(
    users: LazyPagingItems<UserModel>,
    onUserClick: (UserModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        // Paging item'ları
        items(users.itemCount) { index ->
            val user = users[index]

            if (user != null) {
                UserItem(
                    name = "${user.name.first} ${user.name.last}",
                    imageUrl = user.picture.thumbnail,
                    city = user.location.city,
                    onClick = { onUserClick(user) }
                )
            }
        }

        // LOAD STATES
        when (users.loadState.refresh) {
            is LoadState.Loading -> {
                // Tüm ilk ekran için shimmer list
                items(7) {
                    UserShimmerItem()
                }
            }

            is LoadState.Error -> {
                item {
                    Text("Bir hata oluştu")
                }
            }

            else -> {}
        }

        when (users.loadState.append) {
            is LoadState.Loading -> {
                // Liste sonu shimmer
                item {
                    UserShimmerItem()
                }
            }
            else -> {}
        }
    }
}
