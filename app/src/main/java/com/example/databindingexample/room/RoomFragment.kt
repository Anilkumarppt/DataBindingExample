package com.example.databindingexample.room

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ToolbarBindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.databindingexample.MainActivity
import com.example.databindingexample.R
import com.example.databindingexample.databinding.RoomFragmentBinding
import com.example.databindingexample.notification.NotificationClass
import com.example.databindingexample.room.model.Subscriber
import kotlinx.coroutines.delay

class RoomFragment : Fragment() {

    companion object {

        private const val TAG = "RoomFragment"
    }

    private lateinit var viewModel: RoomViewModel
    private lateinit var factory: SubscriberViewModelFactory
    private lateinit var mBinding:RoomFragmentBinding
    private lateinit var adapter: SubscriberRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao=SubscriberDatabase.getDataBaseInstance(this.requireContext()).subscriberDao
        val repository=SubscriberRepository(dao = dao)
        val factory=SubscriberViewModelFactory(repository)
        viewModel= ViewModelProvider(this,factory)[RoomViewModel::class.java]

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding=DataBindingUtil.inflate(inflater,R.layout.room_fragment, container, false)
        mBinding.viewModel=viewModel
        mBinding.lifecycleOwner=this
        initRecyclerView()
        return mBinding.root
    }
    private fun initRecyclerView(){
        mBinding.progressAction.show()
        adapter=SubscriberRecyclerViewAdapter(emptyList(),{selectedItem:Subscriber->onItemClickListener(selectedItem)})
        mBinding.recyclerView.adapter=adapter
        displaySubscribers()
    }
    private fun onItemClickListener( subscriber:Subscriber){
        Log.d(TAG, "onItemClickListener: ${subscriber.name}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
                //Show Notification
                NotificationClass().showNotification(context=requireContext(),it)
            }
        })

        // TODO: Use the ViewModel
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun displaySubscribers(){
        viewModel.subscriber.observe(viewLifecycleOwner, Observer { subscribers->
            Log.d(TAG, "displaySubscribers: "+subscribers)

            adapter.updateList(subscribers)
            adapter.notifyDataSetChanged()
            mBinding.progressAction.hide()
        })
    }

}