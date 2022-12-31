package com.example.tarweej.domain.interactors

import com.example.tarweej.domain.data.models.Advertisement

interface AdvertisementListInteractor {
    suspend fun getAdvertisement() : List<Advertisement>
}