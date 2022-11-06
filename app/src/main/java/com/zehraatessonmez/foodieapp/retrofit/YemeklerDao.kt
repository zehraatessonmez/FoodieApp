package com.zehraatessonmez.foodieapp.retrofit

import com.zehraatessonmez.foodieapp.data.entity.CRUDCevap
import com.zehraatessonmez.foodieapp.data.entity.SepetYemeklerCevap
import com.zehraatessonmez.foodieapp.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

    // yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle() : YemeklerCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(@Field("sepet_yemek_id") yemek_id: Int,
                        @Field("yemek_adi") yemek_adi: String,
                        @Field("yemek_resim_adi") yemek_resim_adi: String,
                        @Field("yemek_fiyat") yemek_fiyat: Int,
                        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                        @Field("kullanici_adi") kullanici_adi: String): CRUDCevap



    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiYemekleriGetir(@Field("kullanici_adi") kullanici_adi: String): SepetYemeklerCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(@Field("sepet_yemek_id") sepet_yemek_id: Int,
                                 @Field("kullanici_adi") kullanici_adi: String): CRUDCevap

}

