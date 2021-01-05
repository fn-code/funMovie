package com.funcode.funmovie.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.funcode.core.data.Resource
import com.funcode.core.domain.model.Film
import com.funcode.core.domain.usecase.home.HomeUseCase

class HomeViewModel @ViewModelInject constructor(private val homeUseCase: HomeUseCase): ViewModel() {
    fun getAllMovie(): LiveData<Resource<List<Film>>> =
        homeUseCase.getAllMovie().asLiveData()

    fun getTvShow(): LiveData<Resource<List<Film>>> =
        homeUseCase.getAllTvShow().asLiveData()
}