package com.mrflaitx.shoppingapp40.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mrflaitx.shoppingapp40.R
import com.mrflaitx.shoppingapp40.databinding.ActivityDetailBinding
import com.mrflaitx.shoppingapp40.databinding.ActivityRecyclerBinding
import com.mrflaitx.shoppingapp40.domain.entity.ShopItem

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val adapter = RecyclerAdapter(this::onItemCLick)

    private fun onItemCLick(shopItem: ShopItem) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initListener()
    }

    private fun initListener() {
        binding.btnDetailActivity.setOnClickListener {
            if (binding.etTwo.text.isEmpty()) {
                Toast.makeText(this, "Ты чё быканул? укажи количество", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent()
                intent.putExtra("name", binding.etOne.text.toString())
                intent.putExtra("count", binding.etTwo.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}