package com.zehraatessonmez.foodieapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.zehraatessonmez.foodieapp.R
import com.zehraatessonmez.foodieapp.databinding.FragmentSepetBinding
import com.zehraatessonmez.foodieapp.ui.adapter.SepetAdapter
import com.zehraatessonmez.foodieapp.ui.adapter.YemeklerAdapter
import com.zehraatessonmez.foodieapp.ui.viewmodel.SepetViewModel
import com.zehraatessonmez.foodieapp.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {

    private lateinit var tasarim: FragmentSepetBinding
    private lateinit var viewModel: SepetViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet, container, false)
        tasarim.sepetFragment = this
        tasarim.sepetToolbarBaslik = "Sepet"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarSepet)

        tasarim.EvResim.setOnClickListener {
            Navigation.gecisYap(tasarim.root,R.id.sepettenAnasayfaGecis)
        }


        tasarim.textViewSepetBos.visibility = INVISIBLE

        viewModel.sepettekiYemeklerListesi.observe(viewLifecycleOwner){

            sepettekiYemeklerListesi -> var toplamSonuc = 0
            sepettekiYemeklerListesi.map { toplamSonuc += it.yemek_fiyat * it.yemek_siparis_adet }
            tasarim.textViewToplam.text = toplamSonuc.toString() + " ₺"

            if(viewModel.sepettekiYemeklerListesi.value.isNullOrEmpty()){
                tasarim.textViewSepetBos.visibility = VISIBLE
            }

            val adapter = SepetAdapter(requireContext(),sepettekiYemeklerListesi,viewModel)
            tasarim.sepetAdapter = adapter

        }

        tasarim.buttonBitir.setOnClickListener {
            if (viewModel.sepettekiYemeklerListesi.value.isNullOrEmpty()){
                Snackbar.make(it,"Sepete En Az Bir Ürün Ekleyin",Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(it,"Sepet Onaylandı",Snackbar.LENGTH_SHORT).show()
                Navigation.findNavController(it).navigate(R.id.sepettenAnasayfaGecis)
            }
        }


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:SepetViewModel by viewModels()
        viewModel = tempViewModel
    }





}