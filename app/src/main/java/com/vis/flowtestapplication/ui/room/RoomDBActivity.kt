package com.vis.flowtestapplication.ui.room

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import com.vis.flowtestapplication.R
import com.vis.flowtestapplication.data.api.ApiHelperImpl
import com.vis.flowtestapplication.data.api.RetrofitBuilder
import com.vis.flowtestapplication.data.local.DatabaseBuilder
import com.vis.flowtestapplication.data.local.DatabaseHelperImpl
import com.vis.flowtestapplication.data.local.entity.User
import com.vis.flowtestapplication.databinding.ActivityRecyclerViewBinding
import com.vis.flowtestapplication.databinding.ActivityRetryBinding
import com.vis.flowtestapplication.ui.base.UserAdapter
import com.vis.flowtestapplication.utils.DefaultDispatcherProvider
import com.vis.flowtestapplication.ui.base.UiState
import com.vis.flowtestapplication.ui.base.ViewModelFactory

class RoomDBActivity : AppCompatActivity() {

    private lateinit var viewModel: RoomDBViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_recycler_view)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding. recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            UserAdapter(
                arrayListOf()
            )
        binding.  recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.     recyclerView.context,
                ( binding. recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.  recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.   progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding. recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.    progressBar.visibility = View.VISIBLE
                            binding.   recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.   progressBar.visibility = View.GONE
                            Toast.makeText(this@RoomDBActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)),
                DefaultDispatcherProvider()
            )
        )[RoomDBViewModel::class.java]
    }
}
