package com.vis.flowtestapplication.ui.flowon

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.vis.flowtestapplication.R
import com.vis.flowtestapplication.data.api.ApiHelperImpl
import com.vis.flowtestapplication.data.api.RetrofitBuilder
import com.vis.flowtestapplication.data.local.DatabaseBuilder
import com.vis.flowtestapplication.data.local.DatabaseHelperImpl
import com.vis.flowtestapplication.databinding.ActivityLongRunningTaskBinding
import com.vis.flowtestapplication.ui.base.ViewModelFactory
import com.vis.flowtestapplication.utils.DefaultDispatcherProvider

class FlowOnActivity : AppCompatActivity() {

    private lateinit var viewModel: FlowOnViewModel
    private lateinit var binding: ActivityLongRunningTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLongRunningTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextView()
        setupViewModel()
        setupTask()
    }

    private fun setupTextView() {
         binding.progressBar.visibility = View.GONE
        binding.textView.text = getString(R.string.check_logcat)
        binding.textView.visibility = View.VISIBLE
    }

    private fun setupTask() {
        viewModel.startFlowOnTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)),
                DefaultDispatcherProvider()
            )
        )[FlowOnViewModel::class.java]
    }
}
