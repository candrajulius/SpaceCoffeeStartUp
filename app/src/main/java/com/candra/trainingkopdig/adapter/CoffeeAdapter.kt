package com.candra.trainingkopdig.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.candra.trainingkopdig.R
import com.candra.trainingkopdig.activity.DetailActivity
import com.candra.trainingkopdig.databinding.ItemListCoffeeBinding
import com.candra.trainingkopdig.helper.Animation
import com.candra.trainingkopdig.model.data.Coffee

class CoffeeAdapter: RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {

    private var listDataCoffee = ArrayList<Coffee>()

    class ViewHolder(private val binding: ItemListCoffeeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Coffee){
            with(binding){
                ivItemImage.load(data.photo){
                    crossfade(true)
                    crossfade(600)
                    error(R.drawable.ic_baseline_broken_image_24)
                    placeholder(R.drawable.ic_baseline_image_24)
                }
                tvItemTitle.text = data.name
                tvItemSubtitle.text = data.growing_area

                root.setOnClickListener {
                    val optionSelected: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(ivItemImage,"photo"),
                        Pair(tvItemTitle,"title"),
                        Pair(tvItemSubtitle,"subtitle")
                    )
                    itemView.context.startActivity(Intent(itemView.context,DetailActivity::class.java).apply {
                        putExtra(Animation.EXTRA_DATA,data)
                    },optionSelected.toBundle())
                }
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Coffee>?){
        if (newListData == null) return
        listDataCoffee.clear()
        listDataCoffee.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeAdapter.ViewHolder =
        ViewHolder(ItemListCoffeeBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CoffeeAdapter.ViewHolder, position: Int) {
        holder.bind(listDataCoffee[position])
    }

    override fun getItemCount(): Int = listDataCoffee.size

}