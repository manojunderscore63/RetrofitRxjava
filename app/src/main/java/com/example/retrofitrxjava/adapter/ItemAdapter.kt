package com.example.retrofitrxjava.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitrxjava.R
import com.example.retrofitrxjava.databinding.ItemTypeBinding
import com.example.retrofitrxjava.model.DataModel

class itemAdapter(

    var context: Context, var list: ArrayList<DataModel>) : RecyclerView.Adapter<itemAdapter.myviewholder>() {

    class myviewholder(var binding: ItemTypeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val v: ItemTypeBinding = DataBindingUtil.inflate(LayoutInflater.from(context)
            , R.layout.item_type, parent, false)
        return myviewholder(v)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val model = list.get(position)
        holder.binding.model = model
        // comment
    }

    override fun getItemCount(): Int {
        return list.size
    }
}