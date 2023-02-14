package com.example.databindingexample.coroutine

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.databindingexample.R
import com.example.databindingexample.UserData
import com.example.databindingexample.databinding.FragmentCoroutineDemoBinding
import com.example.databindingexample.util.CircularProgress
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main


/**
 * A simple [Fragment] subclass.
 * Use the [CoroutineDemo.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoroutineDemo : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var mBinding: FragmentCoroutineDemoBinding
    private var count = 1
    private val TAG = "CoroutineDemo"
    private lateinit var userViewModel: UsersViewModel

    private lateinit var circularProgress:CircularProgress
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        circularProgress=CircularProgress(requireActivity())
        userViewModel.usersList.observe(this, Observer {result->
            result.forEach(){
                Log.d(TAG, "onCreate: ${it.name}")
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_coroutine_demo, container, false)
        initViews()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTranslationZ(requireView(),100F)
        sharedElementEnterTransition
    }

    private fun initViews() {
        mBinding.click.setOnClickListener {
            launchCoroutineMain()
            //mBinding.textView.text= count++.toString()

        }
        mBinding.downloadData.setOnClickListener {
            //multipleTasks()
            CoroutineScope(Main).launch {
                Log.d(TAG, "Download Data Started....")
                circularProgress.showProgressBar("Download Data Started....")
                delay(5000L)
                mBinding.textView.text = UserData.getTotalUsersData().toString()
                circularProgress.hideProgressBar()
                Log.d(TAG, "Data Downloaded.")
            }

        }
    }

    private fun multipleTasks() {
        CoroutineScope(IO).launch {
            Log.i(TAG, "Download data started....... ")
            mBinding.textView.text = "Downloading Start..."
            val users = async { getData1() }
            val comments = async { getData2() }
            val result = users.await() + comments.await()
            mBinding.textView.text = "Final Result $result"
            Log.i(TAG, "Final Result $result")
        }
    }

    private suspend fun getData1(): Int {
        Log.i(TAG, "Users Data download start... ")
        delay(10000)
        Log.i(TAG, "Users Data Downloaded")
        return 129
    }

    private suspend fun getData2(): Int {
        Log.i(TAG, "Comment Data download start... ")
        delay(5000)
        Log.i(TAG, "Comment Data downloaded")
        return 1000
    }

    private fun launchCoroutineMain() {
        CoroutineScope(context = Dispatchers.Main).async {
            Log.d(TAG, "launchCoroutineMain: ")
            for (i in 1..10) {
                Log.d(TAG, "launchCoroutineMain: ${i}")
                delay(1000)
            }
            this.cancel()
        }
    }

    private suspend fun downloadData() {

        withContext(Dispatchers.Main) {
            for (i in 1..20) {
                delay(100)
                mBinding.textView.text =
                    "Coroutines run on ${Thread.currentThread().name} ${i.toString()}"
                //Log.d(TAG, "downloadData: ${i}")
            }
        }
    }
}