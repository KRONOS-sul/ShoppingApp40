package com.mrflaitx.shoppingapp40.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrflaitx.shoppingapp40.databinding.ItemNotProductBinding
import com.mrflaitx.shoppingapp40.databinding.ItemProductBinding
import com.mrflaitx.shoppingapp40.domain.entity.ShopItem

const val LAYOUT_TRUE = 0
const val LAYOUT_FALSE = 1

class RecyclerAdapter constructor(private val onItemClick: (shopItem: ShopItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _list = listOf<ShopItem>()
    var list = listOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            LAYOUT_TRUE -> {
                return TrueViewHolder(
                    ItemProductBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            LAYOUT_FALSE -> {
                return FalseViewHolder(
                    ItemNotProductBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("FUCK")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            LAYOUT_TRUE -> (holder as TrueViewHolder).bind(list[position])
            LAYOUT_FALSE -> (holder as FalseViewHolder).bind(list[position])
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position].enable) {
            true -> LAYOUT_TRUE
            false -> LAYOUT_FALSE
        }
    }

    inner class FalseViewHolder(private val binding: ItemNotProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shopItem: ShopItem) {
            binding.tvName.text = shopItem.name
            binding.tvCount.text = shopItem.count.toString()
            itemView.setOnLongClickListener {
                onItemClick(shopItem)
                return@setOnLongClickListener true
            }
        }
    }

    inner class TrueViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shopItem: ShopItem) {
            binding.tvName.text = shopItem.name
            binding.tvCount.text = shopItem.count.toString()
            itemView.setOnLongClickListener {
                onItemClick(shopItem)
                return@setOnLongClickListener true
            }
        }
    }

    fun initList(list: List<ShopItem>) {
        val callback = DiffCallBack(this._list, list)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        this._list = list
        this.list = _list
    }

}
