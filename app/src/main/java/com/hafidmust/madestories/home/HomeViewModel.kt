package com.hafidmust.madestories.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hafidmust.core.domain.usecase.StoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(storiesUseCase: StoriesUseCase) : ViewModel() {
    private var token =
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLTZVdGZFZGUzb3lHV1NsQVUiLCJpYXQiOjE3MDIyMzkyMjR9.be5Y5ed3guQ5WktkRcdWrk-QC4axBcuHxA0g6ca0KC0"
    val stories = storiesUseCase.getAllStories(token).asLiveData()
}