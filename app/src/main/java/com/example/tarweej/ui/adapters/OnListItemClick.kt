package com.example.tarweej.ui.adapters

import com.example.rocketreserver.ProductListQuery
import com.example.tarweej.domain.data.models.Advertisement

interface OnListItemClick {
    fun onItemClick(advertisement: Advertisement)
}