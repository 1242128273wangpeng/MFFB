package com.wangpeng.firstfirebase.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangpeng.firstfirebase.R
import com.wangpeng.firstfirebase.domain.model.DouBanModel
import com.wangpeng.firstfirebase.utils.RecyclerLifecycleUtils
import com.wangpeng.lib_net.state.RequestResult
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private val recyclerLifecycleUtils = RecyclerLifecycleUtils()
    private val currenciesDisplayManager = DouBanDisplayManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        recyclerLifecycleUtils.handleSavedInstanceStateIfNeeded(savedInstanceState)
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = currenciesDisplayManager.adapter
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            douBanLiveData.observe(this@MainActivity, Observer {
                handlerGetDouBanResult(it)
            })
        }
    }

    private fun handlerGetDouBanResult(result: RequestResult<List<DouBanModel>>) {
        when (result) {
            is RequestResult.Success -> showDouBanList(result.data)
            is RequestResult.Loading -> showLoading()
            is RequestResult.Error -> showError()
        }
    }

    private fun showDouBanList(data: List<DouBanModel>?) {
        progressBar.isVisible = false
        syncOffImageView.isVisible = false
        recyclerView.isVisible = true
        currenciesDisplayManager.displayDouBanList(data)
        recyclerLifecycleUtils.restoreRecyclerState(recyclerView)
    }

    private fun showError() {
        progressBar.isVisible = false
        syncOffImageView.isVisible = true
        recyclerView.isVisible = false
    }

    private fun showLoading() {
        progressBar.isVisible = true
        syncOffImageView.isVisible = false
        recyclerView.isVisible = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerLifecycleUtils.saveRecyclerState(outState, recyclerView)
    }

}