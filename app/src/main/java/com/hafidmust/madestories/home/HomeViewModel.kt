package com.hafidmust.madestories.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hafidmust.core.domain.usecase.StoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(storiesUseCase: StoriesUseCase) : ViewModel() {
    private var token =
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLTFJX0tKNkVZaDBZWkFPcDciLCJpYXQiOjE2NzI5MzI0MzR9.bkoTIt196yyiNplk_beqPBoivgceQ4emwbjCOmP_Kps"
    val stories = storiesUseCase.getAllStories(token).asLiveData()
}