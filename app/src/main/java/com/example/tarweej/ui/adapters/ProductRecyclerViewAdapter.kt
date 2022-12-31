package com.example.tarweej.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tarweej.R
import com.example.tarweej.databinding.ProductListItemBinding
import com.example.tarweej.domain.data.models.Advertisement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ProductRecyclerViewAdapter(private var iFavBtnClickListener: IFavBtnClickListener) :
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    private var productList: List<Advertisement> = emptyList()

    fun setList(productList: List<Advertisement>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    fun setCheckedStatus(favBtnCheckedStatus: Boolean , id:String) {
        val index=productList.indexOfFirst { it?.id==id }
        if (index!=-1){
            productList[index].isFavorite=favBtnCheckedStatus
            notifyItemChanged(index)
        }
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ProductListItemBinding = ProductListItemBinding.bind(itemView)

        fun bind(advertisement: Advertisement) {
            Glide.with(itemView).load(advertisement.images?.first()).into(binding.ivProduct)
            binding.tvProductTitle.text = advertisement.title
            binding.tvProductPrice.text = advertisement.price.toString()
            if (advertisement.offerPrice != null) {
                binding.tvProductOfferPrice.text = advertisement.offerPrice.toString()
                binding.tvProductOfferPrice.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.green_offer_price
                    )
                )
            } else {

                binding.tvProductPrice.text = ""
                binding.tvProductOfferPrice.text = advertisement.price.toString()
                binding.tvProductOfferPrice.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.orange_price
                    )
                )
            }

            Glide.with(itemView).load(advertisement.store?.image).into(binding.ivStore)
            binding.tvStoreName.text = advertisement.store?.name

            val dateFormat = SimpleDateFormat("dd/MM/yyyy").format(advertisement.createdAt)
            binding.tvAddedDate.text = dateFormat


            if (Locale.getDefault().language.equals("en")) {
                binding.tvStoreCity.text = advertisement.store?.city?.enName
            } else {
                binding.tvStoreCity.text = advertisement.store?.city?.arName
            }

            if (advertisement.isFavorite==true){
                binding.favBtn.setImageResource(R.drawable.favourite_btn)
            }else{
                binding.favBtn.setImageResource(R.drawable.not_favourite_btn)
            }

            binding.favBtn.setOnClickListener {

                    iFavBtnClickListener?.onCheckedChangeListener(
                        advertisement.id.toString(),
                        if (advertisement.isFavorite == true) false else true
                    )


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val advertisement : Advertisement = productList[position]
        if (advertisement != null) {
            holder.bind(advertisement)
        }

    }

    override fun getItemCount(): Int {
        return productList.count()
    }

    interface IFavBtnClickListener {
        fun onCheckedChangeListener(id: String, isChecked: Boolean)
    }


}