package com.hafidmust.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafidmust.core.domain.model.Stories
import com.hafidmust.core.ui.MadeStoriesAdapter
import com.hafidmust.favorite.databinding.ActivityFavoriteBinding
import com.hafidmust.favorite.di.DaggerFavoriteComponent
import com.hafidmust.madestories.detail.DetailActivity
import com.hafidmust.madestories.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    @Inject
    lateinit var factory: FavoriteViewModelFactory
    private val viewmodel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext, FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .injectFavoriteStories(this)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite Stories"

        val storiesAdapter = MadeStoriesAdapter(object : MadeStoriesAdapter.IClickListener{
            override fun onClick(stories: Stories) {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, stories)
                startActivity(intent)
            }
        })


        viewmodel.favoriteStories.observe(this) { stories ->
            storiesAdapter.submitList(stories)
        }

        with(binding.rvStories) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = storiesAdapter
        }

    }

}