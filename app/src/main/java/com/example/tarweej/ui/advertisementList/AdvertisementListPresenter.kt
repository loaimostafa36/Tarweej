package com.example.tarweej.ui.advertisementList

import android.content.Context
import android.util.Log
import apolloClient
import com.example.rocketreserver.AddAdvertisementToFavouriteMutation
import com.example.rocketreserver.RemoveAdvertisementFromFavoriteMutation
import com.example.tarweej.domain.data.models.Advertisement
import com.example.tarweej.domain.interactors.AdvertisementListInteractor
import com.example.tarweej.ui.adapters.ProductRecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdvertisementListPresenter(
    private val iAdvertisementListView: AdvertisementListContract.View,
    private val advertisementListInteractor: AdvertisementListInteractor,
    private val context: Context
) : AdvertisementListContract.Presenter, ProductRecyclerViewAdapter.IFavBtnClickListener {
    private var productList: List<Advertisement> = emptyList()
    private var productRecyclerViewAdapter: ProductRecyclerViewAdapter =
        ProductRecyclerViewAdapter(this)

    override fun setAdvertisementList() {
        CoroutineScope(Dispatchers.IO).launch {
            productList = advertisementListInteractor.getAdvertisement()
            withContext(Dispatchers.Main) {
                productRecyclerViewAdapter.setList(productList)
                iAdvertisementListView.setAdvertisementListAdapter(productRecyclerViewAdapter)

            }
        }
    }

    override fun onCheckedChangeListener(id: String, isChecked: Boolean) {

        CoroutineScope(Dispatchers.Main).launch {
            if (isChecked) {
                val response = try {
                    apolloClient(context).mutation(AddAdvertisementToFavouriteMutation(id))
                        .execute()
                } catch (e: Exception) {
                    null
                }
                val code = response?.data?.addAdvertisementToFavorite?.code
                if (code == 200 && !response.hasErrors()) {
                    productRecyclerViewAdapter.setCheckedStatus(isChecked, id)
                }
                Log.d("addGraphql", "response $response")
            } else {
                val response = try {
                    apolloClient(context).mutation(
                        RemoveAdvertisementFromFavoriteMutation(id)
                    ).execute()
                } catch (e: Exception) {
                    null
                }
                val code = response?.data?.removeAdvertisementFromFavorite?.code
                if (code == 200 && !response.hasErrors()) {
                    productRecyclerViewAdapter.setCheckedStatus(isChecked, id)
                }
                Log.d("removeGraphql", "response $response")
            }
        }

    }
}

