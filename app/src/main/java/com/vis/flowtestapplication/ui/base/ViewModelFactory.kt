package com.vis.flowtestapplication.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vis.flowtestapplication.data.api.ApiHelper
import com.vis.flowtestapplication.data.local.DatabaseHelper
import com.vis.flowtestapplication.ui.completion.CompletionViewModel
import com.vis.flowtestapplication.ui.errorhandling.catch.CatchViewModel
import com.vis.flowtestapplication.ui.errorhandling.emitall.EmitAllViewModel
import com.vis.flowtestapplication.ui.filter.FilterViewModel
import com.vis.flowtestapplication.ui.flowon.FlowOnViewModel
import com.vis.flowtestapplication.ui.map.MapViewModel
import com.vis.flowtestapplication.ui.reduce.ReduceViewModel
import com.vis.flowtestapplication.ui.retrofit.parallel.ParallelNetworkCallsViewModel
import com.vis.flowtestapplication.ui.retrofit.series.SeriesNetworkCallsViewModel
import com.vis.flowtestapplication.ui.retrofit.single.SingleNetworkCallViewModel
import com.vis.flowtestapplication.ui.retry.RetryViewModel
import com.vis.flowtestapplication.ui.retryexponentialbackoff.RetryExponentialBackoffModel
import com.vis.flowtestapplication.ui.retrywhen.RetryWhenViewModel
import com.vis.flowtestapplication.ui.room.RoomDBViewModel
import com.vis.flowtestapplication.ui.task.onetask.LongRunningTaskViewModel
import com.vis.flowtestapplication.ui.task.twotasks.TwoLongRunningTasksViewModel
import com.vis.flowtestapplication.utils.DispatcherProvider

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(CatchViewModel::class.java)) {
            return CatchViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(EmitAllViewModel::class.java)) {
            return EmitAllViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(LongRunningTaskViewModel::class.java)) {
            return LongRunningTaskViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(TwoLongRunningTasksViewModel::class.java)) {
            return TwoLongRunningTasksViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(CompletionViewModel::class.java)) {
            return CompletionViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(FilterViewModel::class.java)) {
            return FilterViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(ReduceViewModel::class.java)) {
            return ReduceViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(RetryViewModel::class.java)) {
            return RetryViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(RetryWhenViewModel::class.java)) {
            return RetryWhenViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(RetryExponentialBackoffModel::class.java)) {
            return RetryExponentialBackoffModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(FlowOnViewModel::class.java)) {
            return FlowOnViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}