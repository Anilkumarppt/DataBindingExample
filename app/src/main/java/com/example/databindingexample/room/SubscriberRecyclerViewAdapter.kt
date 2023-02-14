package com.example.databindingexample.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingexample.R
import com.example.databindingexample.databinding.SubscriberItemBinding
import com.example.databindingexample.room.model.Subscriber

class SubscriberRecyclerViewAdapter(private var subscriberList:List<Subscriber>,private val clickListener:(Subscriber)->Unit): RecyclerView.Adapter<SubscriberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {
        val layoutInflater:LayoutInflater= LayoutInflater.from(parent.context)
        val binding:SubscriberItemBinding=DataBindingUtil.inflate(layoutInflater, R.layout.subscriber_item,parent,false)
        return SubscriberViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bindData(subscriberList.get(position),clickListener)

    }

    override fun getItemCount(): Int {
        return subscriberList.size

    }
    fun updateList( subscriberList:List<Subscriber>){
        this.subscriberList=subscriberList
    }
}
class SubscriberViewHolder(val mBinding: SubscriberItemBinding) :RecyclerView.ViewHolder(mBinding.root){

    fun bindData( subscriber:Subscriber,clickListener: (Subscriber) -> Unit){
        this.mBinding.txtSubscriberName.text=subscriber.name
        this.mBinding.txtSubscriberEmail.text=subscriber.email
        this.mBinding.mainItem.setOnClickListener{
            clickListener(subscriber)
        }
    }
}