package com.example.databindingexample.retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.databindingexample.R
import com.example.databindingexample.databinding.PostFragmentBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class PostsFragment : Fragment() {

    companion object {
        fun newInstance() = PostsFragment()
    }

    private lateinit var viewModel: PostsViewModel
    private  val TAG = "PostsFragment"
    private lateinit var mBinding:PostFragmentBinding
    lateinit var albumLists: LiveData<Album>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding=DataBindingUtil.inflate(inflater,R.layout.post_fragment,container,false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)

        val retrofitService:AlbumService=RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
        albumLists=liveData {
            val response = retrofitService.getAlbums()
             emit(response.body() as Album)
        }
        albumLists.observe(viewLifecycleOwner, Observer {
            val list=it.iterator()
            mBinding.textView.text=""
            while (list.hasNext()){
                val albumItem:AlbumItem=list.next()
                val result="Album title : ${albumItem.title}"+"\n"+
                            "Album Id : ${albumItem.id}"+"\n"+
                            "User Id : ${albumItem.userId}"+"\n\n\n"

                mBinding.textView.append(result)

            }
        })

        /*albumLists = liveData {
            val response = retrofitService.getAlbums()
            emit(response.isSuccessful as Album)
        }
        albumLists.observe(viewLifecycleOwner, Observer {
            val list=it.iterator()
            while (list.hasNext()){
                val albumItem:AlbumItem=list.next()
                Log.d(TAG, "onActivityCreated: ${albumItem.title}")
            }
        })*/
        // TODO: Use the ViewModel
    }

}