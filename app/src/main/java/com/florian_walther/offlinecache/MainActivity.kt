package com.florian_walther.offlinecache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.florian_walther.offlinecache.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // this 'by viewModels()' provides the same RestaurantViewModel to the Activity even after
    // screen rotation causing the Activity to be destroyed and recreated
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantAdapter = RestaurantAdapter()
        binding.apply {
            rvRestaurants.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            // observe LiveData
            viewModel.restaurants.observe(this@MainActivity) { restaurants ->
                restaurantAdapter.submitList(restaurants)
            }
        }
    }
}