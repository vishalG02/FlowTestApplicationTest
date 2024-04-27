package com.vis.flowtestapplication.ui.retry

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import com.vis.flowtestapplication.R
import com.vis.flowtestapplication.data.api.ApiHelperImpl
import com.vis.flowtestapplication.data.api.RetrofitBuilder
import com.vis.flowtestapplication.data.local.DatabaseBuilder
import com.vis.flowtestapplication.data.local.DatabaseHelperImpl
import com.vis.flowtestapplication.databinding.ActivityRetryBinding
import com.vis.flowtestapplication.utils.DefaultDispatcherProvider
import com.vis.flowtestapplication.ui.base.UiState.*
import com.vis.flowtestapplication.ui.base.ViewModelFactory

class RetryActivity : AppCompatActivity() {

    private lateinit var viewModel: RetryViewModel
    private lateinit var binding: ActivityRetryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_retry)
        binding = ActivityRetryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupLongRunningTask() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is Success -> {
                            binding. progressBar.visibility = View.GONE
                            binding.textView.text = it.data
                            binding. textView.visibility = View.VISIBLE
                        }
                        is Loading -> {
                            binding.  progressBar.visibility = View.VISIBLE
                            binding. textView.visibility = View.GONE
                        }
                        is Error -> {
                            //Handle Error
                            binding.   progressBar.visibility = View.GONE
                            Toast.makeText(this@RetryActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
        viewModel.startTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)),
                DefaultDispatcherProvider()
            )
        )[RetryViewModel::class.java]
    }
}
