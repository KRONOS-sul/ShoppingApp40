package com.mrflaitx.shoppingapp40.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mrflaitx.shoppingapp40.R
import com.mrflaitx.shoppingapp40.databinding.ActivityRecyclerBinding
import com.mrflaitx.shoppingapp40.domain.entity.ShopItem

class RecyclerActivity : AppCompatActivity(R.layout.activity_recycler) {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityRecyclerBinding by viewBinding()
    private val adapter = RecyclerAdapter(this::onItemCLick)

    private fun onItemCLick(shopItem: ShopItem) {
        viewModel.editShopItem(shopItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initAdapter()
        initListener()
    }

    private fun initListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.list[viewHolder.absoluteAdapterPosition]
                viewModel.removeShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recycler)

        binding.fabBtn.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun initViewModel() {
        viewModel.getShopList().observe(this) {
            adapter.initList(it)
        }
    }

    private fun initAdapter() {
        binding.recycler.adapter = adapter
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val name = intent?.getStringExtra("name")
            println(name)
            val count = intent?.getStringExtra("count")
            println(count)
            viewModel.addShopItem(
                ShopItem(name.toString(), count.toString().toInt(), false)
            )
        }
    }

}