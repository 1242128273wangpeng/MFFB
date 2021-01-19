package com.wangpeng.firstfirebase.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wangpeng.firstfirebase.R
import com.wangpeng.firstfirebase.domain.model.DouBanModel
import com.wangpeng.firstfirebase.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_dou_ban.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.selects.select
import java.lang.ArithmeticException

class DouBanAdapter : ListAdapter<DouBanModel, DouBanAdapter.ViewHolder>(ItemCallback()) {

    private lateinit var recyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recycler: RecyclerView) {
        super.onAttachedToRecyclerView(recycler)
        recyclerView = recycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dou_ban, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(doubamModel: DouBanModel) {
            with(view) {
                nameTextView.text = doubamModel.title
                isFavoriteIv.setImageResource(
                    if (doubamModel.isFavorite) {
                        android.R.drawable.star_on
                    } else {
                        android.R.drawable.star_off
                    }
                )
                rateTextView.text = doubamModel.rate
                Glide.with(context).load(doubamModel.cover).into(cover)
                rootItem.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.MOVICE_ID, doubamModel.id)
                    context.startActivity(intent)
                }
            }
        }
    }


    class ItemCallback : DiffUtil.ItemCallback<DouBanModel>() {

        override fun areItemsTheSame(oldItem: DouBanModel, newItem: DouBanModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DouBanModel, newItem: DouBanModel): Boolean {
            return oldItem == newItem
        }
    }
}
