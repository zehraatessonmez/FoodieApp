package com.zehraatessonmez.foodieapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zehraatessonmez.foodieapp.R
import com.zehraatessonmez.foodieapp.data.entity.Yemekler
import com.zehraatessonmez.foodieapp.databinding.FragmentYemekDetayBinding
import com.zehraatessonmez.foodieapp.ui.viewmodel.YemekDetayViewModel
import com.zehraatessonmez.foodieapp.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {

    private lateinit var tasarim: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayViewModel
    val kullanici_adi = "zehra"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay, container, false)

        tasarim.detayFragment = this
        tasarim.toolbarYemekDetayBaslik = "Yemek Detay"

        val bundle: YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        tasarim.yemekNesnesi = gelenYemek


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(this).load(url).override(300,300).into(tasarim.imageViewYemekResim)

        viewModel.buttonAdetSec(tasarim)


        tasarim.fab.setOnClickListener {
            Navigation.gecisYap(tasarim.root,R.id.detaydanAnasayfaya)
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YemekDetayViewModel by viewModels()
        viewModel = tempViewModel
    }


    fun buttonSepeteYemekEkle(yemek_id: Int,
                              yemek_adi: String,
                              yemek_resim_adi: String,
                              yemek_fiyat: Int,
                              yemek_siparis_adet:String){
        val onay = Snackbar.make(tasarim.root,"${yemek_adi} sepete eklendi...",Snackbar.LENGTH_SHORT)
        viewModel.sepeteYemekEkle(yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet.toInt(),kullanici_adi)
        Log.e("tuşa basıldı","message")
        onay.show()
        Navigation.gecisYap(tasarim.root,R.id.detaydanSepeteGecis)

    }



}