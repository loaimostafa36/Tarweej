package com.example.tarweej.domain.mappers

import com.example.rocketreserver.ProductListQuery
import com.example.tarweej.domain.data.models.Advertisement
import com.example.tarweej.domain.data.models.City
import com.example.tarweej.domain.data.models.Store

object AdvertisementMapper {
    fun convertToAdvertisement(productListQueryItem: ProductListQuery.Item) : Advertisement{
        return Advertisement(
            id = productListQueryItem.id,
            storeId = productListQueryItem.storeId,
            title = productListQueryItem.title,
            price = productListQueryItem.price,
            offerPrice = productListQueryItem.offerPrice,
            images = productListQueryItem.images,
            isFavorite = productListQueryItem.isFavorite,
            createdAt = productListQueryItem.createdAt,
            store = convertToStore(productListQueryItem.store)
        )
    }

    fun convertToStore(productListQueryStore : ProductListQuery.Store?):Store{
        return Store(
            id = productListQueryStore?.id,
            image = productListQueryStore?.image,
            name = productListQueryStore?.name,
            city = convertToCity(productListQueryStore?.city)
        )
    }
    private fun convertToCity(productListQueryCity: ProductListQuery.City? ):City{
        return City(
            id = productListQueryCity?.id,
            arName = productListQueryCity?.arName,
            enName = productListQueryCity?.enName,
            createdAt = productListQueryCity?.createdAt
        )
    }
}