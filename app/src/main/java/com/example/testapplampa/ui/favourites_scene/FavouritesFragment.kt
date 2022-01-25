package com.example.testapplampa.ui.favourites_scene

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplampa.R
import com.example.testapplampa.databinding.FavouritesFragmentBinding
import com.example.testapplampa.databinding.VideoFragmentBinding
import com.example.testapplampa.repository.MainRepository
import com.example.testapplampa.retrofit.Api.FilmsApiService
import com.example.testapplampa.ui.RecyclerAdapter
import com.example.testapplampa.ui.video_scene.VideoViewModel
import com.example.testapplampa.ui.viewpager_utils.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.favourites_fragment.*
import kotlinx.android.synthetic.main.video_fragment.*

class FavouritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavouritesFragment()
    }

    private lateinit var viewModel: FavouritesViewModel
    private lateinit var viewPagerAdapter: PagerAdapter
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val binding = FavouritesFragmentBinding.bind(requireView())

        binding.apply {
            viewPagerAdapter = PagerAdapter()
            viewPagerFavourites.adapter = viewPagerAdapter
            recyclerAdapter = RecyclerAdapter()
            recyclerViewFavourites.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false)
            recyclerViewFavourites.adapter = recyclerAdapter

        }

        TabLayoutMediator(tabLayoutFavourites, viewPagerFavourites) {tab, position ->

        }.attach()

        val retrofitService = FilmsApiService.invoke()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, FavouritesViewModelFactory(mainRepository)).get(FavouritesViewModel::class.java)

        viewModel.topNewsList.observe(viewLifecycleOwner,{
            viewPagerAdapter.submitList(it)
            if (it.isEmpty()){
                tabLayoutFavourites.visibility = View.GONE
            }
        })

        viewModel.favouritesNewsList.observe(viewLifecycleOwner, {
            recyclerAdapter.submitList(it)
        })

        viewModel.getTopNews()
        viewModel.getFavouritesNews()
    }

}