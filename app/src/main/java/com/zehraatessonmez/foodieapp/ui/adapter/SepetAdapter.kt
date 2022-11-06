package com.zehraatessonmez.foodieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zehraatessonmez.foodieapp.R
import com.zehraatessonmez.foodieapp.data.entity.SepetYemekler
import com.zehraatessonmez.foodieapp.databinding.AnasayfaCardTasarimBinding
import com.zehraatessonmez.foodieapp.databinding.SepetCardTasarimBinding
import com.zehraatessonmez.foodieapp.ui.viewmodel.SepetViewModel

class SepetAdapter(var mContext:Context,
                   var sepettekiYemeklerListesi: List<SepetYemekler>,
                   var viewModel: SepetViewModel)
    : RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>() {


    inner class SepetCardTasarimTutucu(tasarim: SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim: SepetCardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val tasarim:SepetCardTasarimBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.sepet_card_tasarim,parent,false)
        return SepetCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepetYemek = sepettekiYemeklerListesi.get(position)
        val sy = holder.tasarim
        sy.sepetNesnesi = sepetYemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(300,300).into(sy.imageView)


        sy.buttonSil.setOnClickListener {
            Snackbar.make(it,"${sepetYemek.yemek_adi} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET"){
                    viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id,sepetYemek.kullanici_adi)
                }.show()
        }


    }

    override fun getItemCount(): Int {
        return sepettekiYemeklerListesi.size
    }
}