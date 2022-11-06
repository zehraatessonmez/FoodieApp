package com.zehraatessonmez.foodieapp.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.zehraatessonmez.foodieapp.R
import com.zehraatessonmez.foodieapp.databinding.FragmentAnasayfaBinding
import com.zehraatessonmez.foodieapp.ui.adapter.YemeklerAdapter
import com.zehraatessonmez.foodieapp.ui.viewmodel.AnasayfaViewModel
import com.zehraatessonmez.foodieapp.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() , SearchView.OnQueryTextListener {

    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var viewModel:AnasayfaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this
        tasarim.anasayfaToolbarBaslik = ""
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)


        tasarim.rv.layoutManager = GridLayoutManager(context,2)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            if(it != null) {
                val adapter = YemeklerAdapter(requireContext(),it,viewModel)
                tasarim.yemeklerAdapter = adapter
            }
        }


        tasarim.fab.setOnClickListener {
            Navigation.gecisYap(tasarim.root,R.id.sepetGecis)
        }



        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }



}