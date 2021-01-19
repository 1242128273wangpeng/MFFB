package com.wangpeng.firstfirebase.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.wangpeng.firstfirebase.R
import com.wangpeng.firstfirebase.domain.model.DetailInfoModel
import com.wangpeng.firstfirebase.utils.state.Result
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val MOVICE_ID = "movice_id"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        observeViewModel()
        if (intent.extras?.containsKey(MOVICE_ID) == true) {
            val id = intent.getStringExtra(MOVICE_ID) ?: ""
            println("detail id-->$id")
            detailViewModel.getDetaliDouBan(id)
        }
    }

    private fun observeViewModel() {
        with(detailViewModel) {
            detailInfoLiveData.observe(this@DetailActivity, Observer {
                handlerGetDouBanDetailResult(it)
            })
        }
    }

    private fun handlerGetDouBanDetailResult(result: Result<DetailInfoModel>) {
        when (result) {
            is Result.Success -> showDouBanDetail(result.data)
            is Result.Loading -> showLoading()
            is Result.Error -> showError()
        }
    }

    private fun showDouBanDetail(data: DetailInfoModel) {
        println("showDouBanDetail data:${data}")
    }

    private fun showError() {
        progressBar.isVisible = false
        syncOffImageView.isVisible = true
    }

    private fun showLoading() {
        progressBar.isVisible = true
        syncOffImageView.isVisible = false
    }

}