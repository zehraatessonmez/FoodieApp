package com.zehraatessonmez.foodieapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zehraatessonmez.foodieapp.data.entity.SepetYemekler
import com.zehraatessonmez.foodieapp.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SepetViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel() {

    var sepettekiYemeklerListesi =  MutableLiveData<List<SepetYemekler>>()
    val kullanici_adi:String = "zehra"


    init {
        sepettekiYemekleriGetir(kullanici_adi)

    }

    fun sepettekiYemekleriGetir(kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            sepettekiYemeklerListesi.value = yrepo.sepettekiYemekleriGetir(kullanici_adi)

        }
    }

    fun sepettenYemekSil( sepet_yemek_id:Int,kullanici_adi: String){
        CoroutineScope(Dispatchers.Main).launch{
            yrepo.sepettenYemekSil(sepet_yemek_id,kullanici_adi = "zehra")
            sepettekiYemekleriGetir(kullanici_adi)
        }
    }


}