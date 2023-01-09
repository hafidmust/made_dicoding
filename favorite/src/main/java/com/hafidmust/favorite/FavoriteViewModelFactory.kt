package com.hafidmust.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hafidmust.core.domain.usecase.StoriesUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(private val storiesUseCase: StoriesUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(storiesUseCase) as T
            }
            else -> throw Throwable("Unknown Viewmodel : " + modelClass.name)
        }

}