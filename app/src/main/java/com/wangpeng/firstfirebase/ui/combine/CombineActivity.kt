package com.wangpeng.firstfirebase.ui.combine

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.wangpeng.firstfirebase.R
import com.wangpeng.firstfirebase.domain.model.DetailInfoModel
import com.wangpeng.firstfirebase.utils.state.Result
import kotlinx.android.synthetic.main.activity_combine.*
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class CombineActivity : AppCompatActivity() {
    private val combineViewModel: CombineViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combine)
        observeViewModel()
//        combineViewModel.getCombineDouBanFlow()
    }

    private fun observeViewModel() {
        with(combineViewModel) {
            detailInfoLiveData.observe(this@CombineActivity, Observer {
                handlerCombineDouBanResult(it)
            })
        }
    }

    private fun handlerCombineDouBanResult(result: Result<List<DetailInfoModel>>) {
        when (result) {
            is Result.Success -> showCombineDouBan(result.data)
            is Result.Loading -> showLoading()
            is Result.Error -> showError()
        }
    }

    private fun showCombineDouBan(detailListData: List<DetailInfoModel>) {
        println("showDouBanCombine detailListData:${detailListData}")
    }

    private fun showError() {
        progressBar.isVisible = false
        syncOffImageView.isVisible = true
    }

    private fun showLoading() {
        progressBar.isVisible = true
        syncOffImageView.isVisible = false
    }

    override fun onResume() {
        super.onResume()
    }

}