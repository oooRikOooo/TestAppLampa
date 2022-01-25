package com.example.testapplampa.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplampa.databinding.RcItemBinding
import com.example.testapplampa.retrofit.response.FilmsDetailsItem
import com.squareup.picasso.Picasso

class RecyclerAdapter : ListAdapter<FilmsDetailsItem, RecyclerAdapter.ItemHolder>(ItemComparator()) {
    class ItemHolder(private var binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : FilmsDetailsItem) = with(binding){
            binding.textViewTextRC.text = item.title
            binding.textViewDateRC.text = item.time
            binding.textViewLinkRC.text = item.click_url
            Picasso.get().load(item.img).into(binding.imageView2)

            //if (item.img == null){
                //binding.imageView2.visibility = View.GONE
            //}
        }

        companion object{
            fun create(parent: ViewGroup):ItemHolder{
                return ItemHolder(
                    RcItemBinding
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
        return RecyclerAdapter.ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }


}