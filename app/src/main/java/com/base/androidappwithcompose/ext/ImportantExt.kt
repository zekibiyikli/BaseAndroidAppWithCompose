package com.base.androidappwithcompose.ext

import kotlinx.coroutines.flow.FlowCollector

fun <T> notNullFlow(flow: (t: T) -> Unit) = FlowCollector<T> {
    it?.let { flow(it) }
}
