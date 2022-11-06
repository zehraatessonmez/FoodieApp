package com.zehraatessonmez.foodieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.zehraatessonmez.foodieapp.data.repo.YemeklerRepository
import com.zehraatessonmez.foodieapp.databinding.FragmentYemekDetayBinding
import com.zehraatessonmez.foodieapp.retrofit.YemeklerDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class YemekDetayViewModel @Inject constructor(var yrepo:YemeklerRepository) : ViewModel() {

    fun sepeteYemekEkle(
        yemek_id: Int,
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet:Int,
        kullanici_adi: String){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.sepeteYemekEkle(yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        }
    }

    fun buttonAdetSec(tasarim:FragmentYemekDetayBinding) {
       yrepo.buttonAdetSec(tasarim)
    }

}