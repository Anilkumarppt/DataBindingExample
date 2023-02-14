package com.example.databindingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.databindingexample.databinding.ActivityMainBinding
import com.example.databindingexample.databinding.ToolbarBinding

class MainActivity : AppCompatActivity() {
    lateinit var mBinding:ActivityMainBinding
    lateinit var navController:NavController
    lateinit var appbarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        //mBinding.includedToolbar.title="Home Page"
       setupActionBar(mBinding.includedToolbar)
    }
    private fun setupActionBar(toolBar: ToolbarBinding) {


        setSupportActionBar(toolBar.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()||super.onSupportNavigateUp()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.base_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_home)
            navController.navigate(R.id.homeFragment)
        return super.onOptionsItemSelected(item)
    }
}