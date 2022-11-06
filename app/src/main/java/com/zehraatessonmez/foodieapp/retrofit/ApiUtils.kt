package com.zehraatessonmez.foodieapp.retrofit

class ApiUtils {

    companion object{
        var BASE_URL = "http://kasimadalan.pe.hu/"

        fun getYemeklerDao() : YemeklerDao {
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDao::class.java)
        }

    }
}