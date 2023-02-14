package com.example.databindingexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databindingexample.databinding.FragmentDataBindingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DataBinding.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataBinding : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var mBinding:FragmentDataBindingBinding
    lateinit var viewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory=MainViewModelFactory(128)
        viewModel= ViewModelProvider(this,factory).get(MainActivityViewModel::class.java)
        // Inflate the layout for this fragment
        mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_data_binding, container, false)
        mBinding.myViewModel=viewModel
        mBinding.lifecycleOwner=this

        return mBinding.root
    }

    fun initviews(){

    }
}