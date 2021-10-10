package com.example.foody.features

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foody.R
import com.example.foody.databinding.ActivityMainBinding
import com.example.foody.features.dialogs.FoodDetailsFragment
import com.example.foody.repository.models.FoodResponse
import com.example.foody.utils.ResourceStates
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel : MainActivityViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var foodListAdapter: FoodListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val foodRecyclerView = findViewById<RecyclerView>(R.id.food_rv)

        mainActivityViewModel.getFood()
        mainActivityViewModel.foodLiveData.observe(this){
            when(it.resourceStates){
                ResourceStates.FAILED ->{
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                }
                ResourceStates.LOADING ->{
                    //Loading
                }
                ResourceStates.SUCCESS ->{
                    it.data?.run {
                        foodListAdapter.submitList(this)
                    }
                }
            }
        }

        foodRecyclerView?.run {
            val columnCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){ 3 }else{ 2 }
            layoutManager = StaggeredGridLayoutManager(columnCount , StaggeredGridLayoutManager.VERTICAL)
            adapter = foodListAdapter
        }

        foodListAdapter.setOnClickCityListener(object : FoodListAdapter.OnClickCityListener{
            override fun onClickCity(foodResponse: FoodResponse) {
                //FoodDetailsDialogFragment().show(supportFragmentManager.beginTransaction(), "")
                FoodDetailsFragment.add(this@MainActivity, R.id.main_layout)
                mainActivityViewModel.showDetails(foodResponse)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}