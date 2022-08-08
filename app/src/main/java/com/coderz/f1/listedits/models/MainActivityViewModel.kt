package com.coderz.f1.listedits.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coderz.f1.listedits.data.MyItem

class MainActivityViewModel : ViewModel() {
    val myItemsLiveData: LiveData<SnapshotStateList<MyItem>>
        get() = items

    private val items = MutableLiveData<SnapshotStateList<MyItem>>()
    private val itemImpl = mutableStateListOf<MyItem>()

    fun addItem(item: MyItem){
        itemImpl.add(item)
        items.postValue(itemImpl)
    }

    fun removeItem(item:MyItem){
        itemImpl.remove(item)
        items.postValue(itemImpl)
    }
}