package com.hafidmust.madestories.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafidmust.core.domain.model.Stories
import com.hafidmust.core.ui.MadeStoriesAdapter
import com.hafidmust.madestories.R
import com.hafidmust.core.ui.StoriesAdapter
import com.hafidmust.madestories.databinding.ActivityHomeBinding
import com.hafidmust.madestories.detail.DetailActivity

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val storiesAdapter = StoriesAdapter()
        val storiesAdapter = MadeStoriesAdapter(object : MadeStoriesAdapter.IClickListener {
            override fun onClick(stories: Stories) {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, stories)
                startActivity(intent)
            }
        })
//        storiesAdapter.onItemClick = {
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra(DetailActivity.EXTRA_DATA, it)
//            startActivity(intent)
//        }

        homeViewModel.stories.observe(this) { stories ->
            if (stories != null) {
                when (stories) {
                    is com.hafidmust.core.data.Resource.Loading -> {}
                    is com.hafidmust.core.data.Resource.Success -> {
                        storiesAdapter.submitList(stories.data)
                    }
                    is com.hafidmust.core.data.Resource.Error -> {}
                }
            }
        }

        with(binding.rvStories) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            adapter = storiesAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_fav -> {
                val uri = Uri.parse("madestories://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}