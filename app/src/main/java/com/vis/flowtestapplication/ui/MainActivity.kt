package com.vis.flowtestapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vis.flowtestapplication.R
import com.vis.flowtestapplication.ui.completion.CompletionActivity
import com.vis.flowtestapplication.ui.errorhandling.catch.CatchActivity
import com.vis.flowtestapplication.ui.errorhandling.emitall.EmitAllActivity
import com.vis.flowtestapplication.ui.filter.FilterActivity
import com.vis.flowtestapplication.ui.flowon.FlowOnActivity
import com.vis.flowtestapplication.ui.map.MapActivity
import com.vis.flowtestapplication.ui.reduce.ReduceActivity
import com.vis.flowtestapplication.ui.retrofit.parallel.ParallelNetworkCallsActivity
import com.vis.flowtestapplication.ui.retrofit.series.SeriesNetworkCallsActivity
import com.vis.flowtestapplication.ui.retrofit.single.SingleNetworkCallActivity
import com.vis.flowtestapplication.ui.retry.RetryActivity
import com.vis.flowtestapplication.ui.retryexponentialbackoff.RetryExponentialBackoffActivity
import com.vis.flowtestapplication.ui.retrywhen.RetryWhenActivity
import com.vis.flowtestapplication.ui.room.RoomDBActivity
import com.vis.flowtestapplication.ui.search.SearchActivity
import com.vis.flowtestapplication.ui.task.onetask.LongRunningTaskActivity
import com.vis.flowtestapplication.ui.task.twotasks.TwoLongRunningTasksActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSingleNetworkCallActivity(view: View) {
        startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
    }

    fun startSeriesNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
    }

    fun startParallelNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
    }

    fun startRoomDatabaseActivity(view: View) {
        startActivity(Intent(this@MainActivity, RoomDBActivity::class.java))
    }

    fun startCatchActivity(view: View) {
        startActivity(Intent(this@MainActivity, CatchActivity::class.java))
    }

    fun startEmitAllActivity(view: View) {
        startActivity(Intent(this@MainActivity, EmitAllActivity::class.java))
    }

    fun startCompletionActivity(view: View) {
        startActivity(Intent(this@MainActivity, CompletionActivity::class.java))
    }

    fun startLongRunningTaskActivity(view: View) {
        startActivity(Intent(this@MainActivity, LongRunningTaskActivity::class.java))
    }

    fun startTwoLongRunningTasksActivity(view: View) {
        startActivity(Intent(this@MainActivity, TwoLongRunningTasksActivity::class.java))
    }

    fun startFilterActivity(view: View) {
        startActivity(Intent(this@MainActivity, FilterActivity::class.java))
    }

    fun startMapActivity(view: View) {
        startActivity(Intent(this@MainActivity, MapActivity::class.java))
    }

    fun startReduceActivity(view: View) {
        startActivity(Intent(this@MainActivity, ReduceActivity::class.java))
    }

    fun startSearchActivity(view: View) {
        startActivity(Intent(this@MainActivity, SearchActivity::class.java))
    }

    fun startRetryActivity(view: View) {
        startActivity(Intent(this@MainActivity, RetryActivity::class.java))
    }

    fun startRetryWhenActivity(view: View) {
        startActivity(Intent(this@MainActivity, RetryWhenActivity::class.java))
    }

    fun startRetryExponentialBackoffActivity(view: View) {
        startActivity(Intent(this@MainActivity, RetryExponentialBackoffActivity::class.java))
    }

    fun startFlowOnActivity(view: View) {
        startActivity(Intent(this@MainActivity, FlowOnActivity::class.java))
    }

}
