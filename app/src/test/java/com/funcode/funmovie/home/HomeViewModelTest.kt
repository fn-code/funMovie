package com.funcode.funmovie.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.funcode.core.data.MovieRepository
import com.funcode.core.data.Resource
import com.funcode.core.domain.model.Film
import com.funcode.core.domain.usecase.home.HomeInteractor
import com.funcode.core.domain.usecase.home.HomeUseCase
import com.funcode.core.utils.DataDummy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val movieDummy = DataDummy.generateDummyMovie()
    private val tvShowDummy = DataDummy.generateDummyTvShow()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeUseCase: HomeUseCase

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<Film>>>


    @Before
    fun setUp() {
        homeUseCase = HomeInteractor(movieRepository)
        viewModel = HomeViewModel(homeUseCase)
    }

    @Test
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun getAllMovie() = runBlockingTest {
        suspend {
            launch {
                val flowMovieData = flow<Resource<List<Film>>> {
                    emit(Resource.Success(movieDummy))
                }

                Mockito.`when`(movieRepository.getAllMovie()).thenReturn(flowMovieData)
                Mockito.`when`(homeUseCase.getAllMovie()).thenReturn(flowMovieData)
                viewModel.getAllMovie().observeForever(observer)
                flowMovieData.collect {
                    Mockito.verify(observer).onChanged(it)
                    assertEquals(movieDummy, it)
                }
            }
        }
    }

    @Test
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun getTvShow() = runBlockingTest {
        suspend {
            launch {
                val flowtvShowData = flow<Resource<List<Film>>> {
                    emit(Resource.Success(tvShowDummy))
                }

                Mockito.`when`(movieRepository.getAllTvShow()).thenReturn(flowtvShowData)
                Mockito.`when`(homeUseCase.getAllTvShow()).thenReturn(flowtvShowData)
                viewModel.getTvShow().observeForever(observer)
                flowtvShowData.collect {
                    Mockito.verify(observer).onChanged(it)
                    assertEquals(movieDummy, it)
                }
            }
        }
    }
}