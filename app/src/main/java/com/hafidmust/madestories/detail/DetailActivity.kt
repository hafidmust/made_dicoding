package com.hafidmust.madestories.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.hafidmust.madestories.R
import com.hafidmust.core.domain.model.Stories
import com.hafidmust.madestories.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailStoriesViewModel: DetailStoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailStories = intent.getParcelableExtra<Stories>(EXTRA_DATA)
        showDetail(detailStories)
    }

    private fun showDetail(detailStories: Stories?) {
        detailStories?.let {
            supportActionBar?.title = detailStories.name
            Glide.with(this@DetailActivity)
                .load(detailStories.photoUrl)
                .into(binding.ivDetail)

            binding.tvDesc.text = detailStories.description
            binding.tvDate.text = detailStories.createdAt

            var statusFavorite = detailStories.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailStoriesViewModel.setFavoriteStories(detailStories, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}