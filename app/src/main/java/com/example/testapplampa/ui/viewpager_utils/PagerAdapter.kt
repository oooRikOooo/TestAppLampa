package com.example.testapplampa.ui.viewpager_utils


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplampa.databinding.BaseViewpagerFragmentBinding
import com.example.testapplampa.retrofit.response.FilmsDetailsItem
import com.squareup.picasso.Picasso



class PagerAdapter : ListAdapter<FilmsDetailsItem, PagerAdapter.ItemHolder>(ItemComparator()) {
    class ItemHolder(private var binding: BaseViewpagerFragmentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : FilmsDetailsItem) = with(binding){
            textViewText.text = item.title
            textViewLink.text = item.click_url
            textViewDate.text = item.time
            Picasso.get().load(item.img).into(binding.imageView)
        }

        companion object{
            fun create(parent: ViewGroup):ItemHolder{
                return ItemHolder(BaseViewpagerFragmentBinding
                    .inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<FilmsDetailsItem>(){
        override fun areItemsTheSame(
            oldItem: FilmsDetailsItem,
            newItem: FilmsDetailsItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FilmsDetailsItem,
            newItem: FilmsDetailsItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}