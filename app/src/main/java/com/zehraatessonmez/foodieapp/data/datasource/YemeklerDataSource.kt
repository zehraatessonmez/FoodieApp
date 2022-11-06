package com.zehraatessonmez.foodieapp.data.datasource

import com.zehraatessonmez.foodieapp.data.entity.SepetYemekler
import com.zehraatessonmez.foodieapp.data.entity.Yemekler
import com.zehraatessonmez.foodieapp.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Field
import java.io.EOFException

class YemeklerDataSource(var ydao: YemeklerDao) {

    suspend fun yemekleriYukle() : List<Yemekler> =
        withContext(Dispatchers.IO){
            ydao.yemekleriYukle().yemekler
        }

    suspend fun sepeteYemekEkle(yemek_id: Int,
                                yemek_adi: String,
                                yemek_resim_adi: String,
                                yemek_fiyat: Int,
                                yemek_siparis_adet: Int,
                                kullanici_adi: String) = ydao.sepeteYemekEkle(yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)


   suspend fun sepettekiYemekleriGetir(kullanici_adi: String) : List<SepetYemekler> =
       withContext(Dispatchers.IO) {
           try {
               ydao.sepettekiYemekleriGetir(kullanici_adi).sepet_yemekler
           }catch (e: EOFException){
               emptyList()
           }

       }

    suspend fun sepettenYemekSil( sepet_yemek_id:Int,kullanici_adi: String) = ydao.sepettenYemekSil(sepet_yemek_id,kullanici_adi)


}