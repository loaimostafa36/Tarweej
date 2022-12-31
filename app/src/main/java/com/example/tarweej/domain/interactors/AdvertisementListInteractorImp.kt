package com.example.tarweej.domain.interactors

import android.content.Context
import android.util.Log
import apolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.rocketreserver.ProductListQuery
import com.example.tarweej.domain.data.models.Advertisement
import com.example.tarweej.domain.mappers.AdvertisementMapper

class AdvertisementListInteractorImp(val context: Context) : AdvertisementListInteractor {
    private var productListGraphql: List<ProductListQuery.Item?> = emptyList()
    private var productList: List<Advertisement> = emptyList()
    override suspend fun getAdvertisement() : List<Advertisement> {
        val response = try {
            apolloClient(context).query(ProductListQuery()).execute()
        } catch (e: ApolloException) {
            Log.d("ProductList", "Failure", e)
            null
        }
        if(response?.hasErrors()!=true) {
            productListGraphql = response?.data?.advertisements?.data?.items ?: emptyList()
            productList = productListGraphql.map {
                Advertisement(
                    it?.id,
                    it?.storeId,
                    it?.title,
                    it?.price,
                    it?.offerPrice,
                    it?.images,
                    it?.isFavorite,
                    it?.createdAt,
                    AdvertisementMapper.convertToStore(it?.store)
                )
            }
        }
        return productList
    }
}