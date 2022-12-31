package com.example.tarweej.ui.advertisementList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tarweej.databinding.ActivitySecondBinding
import com.example.tarweej.domain.data.models.Advertisement
import com.example.tarweej.domain.interactors.AdvertisementListInteractor
import com.example.tarweej.domain.interactors.AdvertisementListInteractorImp
import com.example.tarweej.ui.adapters.ProductRecyclerViewAdapter

class SecondActivity : AppCompatActivity(), AdvertisementListContract.View {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var advertisementListInteractor: AdvertisementListInteractor
    private lateinit var advertisementListPresenter: AdvertisementListPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        advertisementListInteractor = AdvertisementListInteractorImp(this)
        advertisementListPresenter = AdvertisementListPresenter(this, advertisementListInteractor, this)
        advertisementListPresenter.setAdvertisementList()

    }

    override fun setAdvertisementListAdapter(productRecyclerViewAdapter: ProductRecyclerViewAdapter) {
        binding.rvProductShow.adapter = productRecyclerViewAdapter
    }

}