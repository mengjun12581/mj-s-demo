package com.mj.kotlindemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyRecyclerViewAdapter(list: ArrayList<String>, mContext: Context) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    private val mData = list
    private val mContext = mContext
    private var mListener : OnItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, p0, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = mData[position]
        holder.text.setOnClickListener{
            mListener?.onItemClick(position, holder.text)
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.item_txt)
    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        mListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position : Int, view : View)
    }

}