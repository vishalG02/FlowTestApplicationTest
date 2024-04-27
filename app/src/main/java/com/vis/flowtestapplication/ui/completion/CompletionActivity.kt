package com.vis.flowtestapplication.ui.completion

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
import com.vis.flowtestapplication.databinding.ActivityCompletionBinding
import com.vis.flowtestapplication.databinding.ActivityRecyclerViewBinding
import com.vis.flowtestapplication.ui.base.ApiUserAdapter
import com.vis.flowtestapplication.utils.DefaultDispatcherProvider
import com.vis.flowtestapplication.ui.base.UiState
import com.vis.flowtestapplication.ui.base.ViewModelFactory

class CompletionActivity : AppCompatActivity() {

    private lateinit var viewModel: CompletionViewModel
    private lateinit var adapter: ApiUserAdapter
    private lateinit var binding: ActivityCompletionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_completion)
        binding = ActivityCompletionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                             binding.progressBar.visibility = View.GONE
                            binding.textView.text = it.data
                            binding.textView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                             binding.progressBar.visibility = View.VISIBLE
                            binding.textView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                             binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@CompletionActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)),
                DefaultDispatcherProvider()
            )
        )[CompletionViewModel::class.java]
    }
}