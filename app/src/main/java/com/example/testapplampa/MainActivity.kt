package com.example.testapplampa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.testapplampa.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var vm: ViewModelMain
    private lateinit var adapter:MyAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    //private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar = findViewById(R.id.toolBar)
        //toolbar.inflateMenu(R.menu.menu)

        vm = ViewModelProvider(this).get(ViewModelMain::class.java)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        //change to true if fragment swiping is needed
        viewPager.isUserInputEnabled = false

        tabLayout.addTab(tabLayout.newTab().setText("Stories"))
        tabLayout.addTab(tabLayout.newTab().setText("Video"))
        tabLayout.addTab(tabLayout.newTab().setText("Favourites"))

        adapter = MyAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> {
                    tab.text = "Stories"
                }

                1 -> {
                    tab.text = "Video"
                }

                2 -> {
                    tab.text = "Favourites"
                }
            }
        }.attach()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}