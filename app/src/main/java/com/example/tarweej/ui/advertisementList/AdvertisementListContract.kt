package com.example.tarweej.ui.advertisementList

import com.example.tarweej.domain.data.models.Advertisement
import com.example.tarweej.ui.adapters.ProductRecyclerViewAdapter

interface AdvertisementListContract {
    interface View{
        fun setAdvertisementListAdapter(productRecyclerViewAdapter: ProductRecyclerViewAdapter)
    }
    interface Presenter{
        fun setAdvertisementList()

    }
}