package com.base.androidappwithcompose.ext

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.base.androidappwithcompose.R

inline fun <reified T> ComponentActivity.navigateTo(
    isNewTask: Boolean = false,
    finishWhenOpen: Boolean = true,
    bundle: Bundle? = null
) {
    Intent(this, T::class.java).apply {
        if (isNewTask)
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        bundle?.let {
            putExtras(bundle)
        }
    }.also {
        startActivity(it)
        if (finishWhenOpen)
            finish()
    }
}

fun Fragment.navigateTo(destinationId: Int, bundle: Bundle? = null) {
    findNavController().navigate(destinationId, bundle)
}

fun Fragment.navigateDeeplink(deepLink: String) {
    Intent().apply {
        action = Intent.ACTION_VIEW
        data = Uri.parse(deepLink)
    }.also {
        startActivity(it)
    }
}

fun NavController.navigateForward(destinationId: Int, bundle: Bundle?=null){
    val navOptions =
        NavOptions.Builder().setEnterAnim(R.anim.anim_enter).setExitAnim(R.anim.anim_exit).
        setPopEnterAnim(R.anim.anim_popenter).setPopExitAnim(R.anim.anim_popexit).build()
    this.navigate(destinationId,bundle,navOptions)
}
fun NavController.navigateBackward(destinationId: Int, bundle: Bundle?=null){
    val navOptions =
        NavOptions.Builder().setEnterAnim(R.anim.anim_popenter).setExitAnim(R.anim.anim_popexit).
        setPopEnterAnim(R.anim.anim_enter).setPopExitAnim(R.anim.anim_exit).build()
    this.navigate(destinationId,bundle,navOptions)
}
fun NavController.navigateForwardWithPopBackStack(destinationId: Int, backStackId:Int, bundle: Bundle?=null){
    val navOptions =
        NavOptions.Builder().setEnterAnim(R.anim.anim_enter).setExitAnim(R.anim.anim_exit).
        setPopEnterAnim(R.anim.anim_popenter).setPopExitAnim(R.anim.anim_popexit).setPopUpTo(backStackId,true).build()
    this.navigate(destinationId,bundle,navOptions)
}
fun NavController.navigateForwardWithClearState(destinationId: Int, backStackId:Int, bundle: Bundle?=null){
    val navOptions =
        NavOptions.Builder().setEnterAnim(R.anim.anim_enter).setExitAnim(R.anim.anim_exit).
        setPopEnterAnim(R.anim.anim_popenter).setPopExitAnim(R.anim.anim_popexit).setPopUpTo(backStackId,inclusive = true,saveState = false).build()
    this.navigate(destinationId,bundle,navOptions)
}

fun NavController.navigateBackWardWithClearState(destinationId: Int, backStackId: Int, bundle: Bundle?=null){
    val navOptions =
        NavOptions.Builder().setEnterAnim(R.anim.anim_popenter).setExitAnim(R.anim.anim_popexit).
        setPopEnterAnim(R.anim.anim_enter).setPopExitAnim(R.anim.anim_exit).setPopUpTo(backStackId,inclusive = true,saveState = false).build()
    this.navigate(destinationId,bundle,navOptions)
}

fun NavController.changeNodeDestination(nodeId: Int, destinationId: Int): NavController {
    val graph = graph.findNode(nodeId) as NavGraph
    graph.setStartDestination(destinationId)
    //graph.startDestination = destinationId
    return this
}

fun NavController.changeNodeDestination(vararg nodeIds: Int, destinationId: Int): NavController {
    var currentNode = graph

    nodeIds.forEachIndexed { index, i ->
        currentNode = currentNode.findNode(nodeIds[index]) as NavGraph
    }
    currentNode.setStartDestination(destinationId)
    return this
}

inline fun <reified T : Fragment> Fragment.bindFragment(
    containerView: Int,
    bundle: Bundle = Bundle()
) {
    val target: Fragment = T::class.java.newInstance()
    target.arguments = bundle
    val fm = childFragmentManager
    val ts = fm.beginTransaction()
    ts.replace(containerView, target).commitAllowingStateLoss()

}
