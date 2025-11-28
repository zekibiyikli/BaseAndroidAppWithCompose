package com.base.androidappwithcompose.ext

import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.FlowCollector

fun <T> notNullObserver(observer: (t: T) -> Unit) = Observer<T> {
    it?.let { observer(it) }
}

fun <T> notNullFlow(flow: (t: T) -> Unit) = FlowCollector<T> {
    it?.let { flow(it) }
}

fun <T> notNullApiResult(observer: (t: T) -> Unit) = Observer<T> {
    it?.let { observer(it) }
}
