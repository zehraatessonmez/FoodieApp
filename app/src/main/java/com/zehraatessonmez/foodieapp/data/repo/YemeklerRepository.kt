package com.zehraatessonmez.foodieapp.data.repo

import com.zehraatessonmez.foodieapp.data.datasource.YemeklerDataSource
import com.zehraatessonmez.foodieapp.data.entity.SepetYemekler
import com.zehraatessonmez.foodieapp.data.entity.Yemekler
import com.zehraatessonmez.foodieapp.databinding.FragmentYemekDetayBinding
import retrofit2.http.Field

class YemeklerRepository(var yds: YemeklerDataSource) {


    suspend fun yemekleriYukle(): List<Yemekler> = yds.yemekleriYukle()

    suspend fun sepeteYemekEkle(yemek_id: Int,
                                yemek_adi: String,
                                yemek_resim_adi: String,
                                yemek_fiyat: Int,
                                yemek_siparis_adet: Int,
                                kullanici_adi: String) = yds.sepeteYemekEkle(yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)


    suspend fun sepettekiYemekleriGetir(kullanici_adi: String): List<SepetYemekler> =  yds.sepettekiYemekleriGetir(kullanici_adi)


    suspend fun sepettenYemekSil( sepet_yemek_id:Int,kullanici_adi: String) = yds.sepettenYemekSil(sepet_yemek_id,kullanici_adi)


    private fun arttirAzalt(yemek_adet:Int,tasarim: FragmentYemekDetayBinding) {
        tasarim.textViewAdet.text = "$yemek_adet"
    }

    fun arttir(tasarim:FragmentYemekDetayBinding) {
        arttirAzalt(tasarim.textViewAdet.text.toString().toInt() + 1, tasarim)
    }

    fun azalt(tasarim:FragmentYemekDetayBinding) {
        arttirAzalt(tasarim.textViewAdet.text.toString().toInt() - 1, tasarim)
        if (tasarim.textViewAdet.text.toString().toInt() < 2) {
            tasarim.textViewAdet.text = "1" }
    }

    fun buttonAdetSec(tasarim:FragmentYemekDetayBinding) {
        tasarim.buttonArti.setOnClickListener {
            arttir(tasarim)
        }
        tasarim.buttonEksi.setOnClickListener {
            azalt(tasarim)
        }
    }


}