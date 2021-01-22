package com.wangpeng.firstfirebase.ui.combine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.wangpeng.firstfirebase.R
import com.wangpeng.firstfirebase.domain.model.DetailInfoModel
import com.wangpeng.lib_net.state.RequestResult
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


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

    private fun handlerCombineDouBanResult(result: RequestResult<List<DetailInfoModel>>) {
        when (result) {
            is RequestResult.Success -> showCombineDouBan(result.data)
            is RequestResult.Loading -> showLoading()
            is RequestResult.Error -> showError()
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