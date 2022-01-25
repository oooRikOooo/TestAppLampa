package com.example.testapplampa.ui.stories_scene

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplampa.R
import com.example.testapplampa.databinding.StoriesFragmentBinding
import com.example.testapplampa.repository.MainRepository
import com.example.testapplampa.retrofit.Api.FilmsApiService
import com.example.testapplampa.ui.RecyclerAdapter
import com.example.testapplampa.ui.viewpager_utils.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.stories_fragment.*

class StoriesFragment : Fragment() {

    companion object {
        fun newInstance() = StoriesFragment()
    }

    private lateinit var viewModel: StoriesViewModel
    private lateinit var viewPagerAdapter: PagerAdapter
    private lateinit var recyclerAdapter: RecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.stories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val binding = StoriesFragmentBinding.bind(requireView())

        binding.apply {
            viewPagerAdapter = PagerAdapter()
            viewPager.adapter = viewPagerAdapter
            recyclerAdapter = RecyclerAdapter()
            recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            recyclerView.adapter = recyclerAdapter

        }

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->

        }.attach()

        val retrofitService = FilmsApiService.invoke()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, ViewModelFactory(mainRepository)).get(StoriesViewModel::class.java)

        viewModel.newsList.observe(viewLifecycleOwner,{
            //Log.v("AAA", it.toString())

        })

        viewModel.topNewsList.observe(viewLifecycleOwner,{
            viewPagerAdapter.submitList(it)
            if (it.isEmpty()){
                tabLayout.visibility = View.GONE
            }
            Log.v("ans", it.toString())
        })

        viewModel.storiesNewsList.observe(viewLifecycleOwner, {
            recyclerAdapter.submitList(it)
            //Log.v("AAA", it.toString())
        })

        viewModel.getAllNews()
        viewModel.getTopNews()
        viewModel.getStoriesNews()
        //recyclerAdapter.notifyDataSetChanged()

    }



}