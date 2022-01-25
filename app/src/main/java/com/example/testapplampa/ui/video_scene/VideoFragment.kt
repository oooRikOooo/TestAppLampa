package com.example.testapplampa.ui.video_scene

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplampa.R
import com.example.testapplampa.databinding.VideoFragmentBinding
import com.example.testapplampa.repository.MainRepository
import com.example.testapplampa.retrofit.Api.FilmsApiService
import com.example.testapplampa.ui.RecyclerAdapter
import com.example.testapplampa.ui.viewpager_utils.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.video_fragment.*

class VideoFragment : Fragment() {

    companion object {
        fun newInstance() = VideoFragment()
    }

    private lateinit var viewModel: VideoViewModel
    private lateinit var viewPagerAdapter: PagerAdapter
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.video_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val binding = VideoFragmentBinding.bind(requireView())

        binding.apply {
            viewPagerAdapter = PagerAdapter()
            viewPagerVideo.adapter = viewPagerAdapter
            recyclerAdapter = RecyclerAdapter()
            recyclerViewVideo.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false)
            recyclerViewVideo.adapter = recyclerAdapter

        }

        TabLayoutMediator(tabLayoutVideo, viewPagerVideo) {tab, position ->

        }.attach()

        val retrofitService = FilmsApiService.invoke()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, VideoViewModelFactory(mainRepository)).get(VideoViewModel::class.java)

        viewModel.topNewsList.observe(viewLifecycleOwner,{
            viewPagerAdapter.submitList(it)
            if (it.isEmpty()){
                tabLayoutVideo.visibility = View.GONE
            }
        })

        viewModel.videoNewsList.observe(viewLifecycleOwner, {
            recyclerAdapter.submitList(it)
            Log.v("AAA", it.toString())
        })

        viewModel.getTopNews()
        viewModel.getStoriesNews()
    }

}