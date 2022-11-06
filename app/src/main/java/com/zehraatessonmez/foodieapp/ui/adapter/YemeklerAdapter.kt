package com.zehraatessonmez.foodieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.zehraatessonmez.foodieapp.R
import com.zehraatessonmez.foodieapp.data.entity.Yemekler
import com.zehraatessonmez.foodieapp.databinding.AnasayfaCardTasarimBinding
import com.zehraatessonmez.foodieapp.ui.fragment.AnasayfaFragmentDirections
import com.zehraatessonmez.foodieapp.ui.viewmodel.AnasayfaViewModel
import com.zehraatessonmez.foodieapp.utils.gecisYap


class YemeklerAdapter(var mContext: Context,
                      var yemeklerListesi:List<Yemekler>,
                      var viewModel: AnasayfaViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    //card tasarımı temsil eder
    inner class CardTasarimTutucu(tasarim:AnasayfaCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:AnasayfaCardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim:AnasayfaCardTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.anasayfa_card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val y = holder.tasarim
        y.yemekNesnesi = yemek


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(300,300).into(y.imageViewYemekResim)

        y.yemekCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemek)
            Navigation.gecisYap(it,gecis)
        }

        y.buttonSepeteEkle.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemek)
            Navigation.gecisYap(it,gecis)  }



    }


    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }




}