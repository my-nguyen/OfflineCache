package com.florian_walther.offlinecache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(service: RestaurantService): ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    // upon construction, this ViewModel launches a coroutine to make an API request. when the
    // request finishes, it sets the _restaurants' LiveData value to the list of restaurants just
    // retrieved, and the Activity can then observe the restaurants LiveData in a lifecycle-aware
    // fashion. if this ViewModel gets destroyed, as when we leave the app, then the viewModelScope
    // is automatically canceled together with the API request, so there won't be any unnecessary
    // background thread
    init {
        viewModelScope.launch {
            val data = service.getRestaurants()
            // deliberately delay 2 seconds in order to see the ProgressBar
            delay(2000)
            _restaurants.value = data
        }
    }
}