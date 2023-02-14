package com.example.databindingexample.util

import android.app.Activity
import android.app.AlertDialog
import com.example.databindingexample.R
import com.example.databindingexample.databinding.CircularProgressViewBinding

class CircularProgress(val activity:Activity) {
    private lateinit var mDialog: AlertDialog
    private lateinit var mBinding:CircularProgressViewBinding
    fun showProgressBar( message:String){
        val inflater=activity.layoutInflater
        mBinding=CircularProgressViewBinding.inflate(inflater)
        mBinding.textView2.text=message
        val builder=AlertDialog.Builder(activity).setView(mBinding.root).
            setCancelable(false)
        mDialog=builder.create()
        mDialog.show()

    }
    fun hideProgressBar(){
        if(mDialog!=null)
            mDialog.hide()
    }
}