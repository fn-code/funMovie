package com.funcode.funmovie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.funcode.core.data.MovieRepository
import com.funcode.core.domain.model.Film
import com.funcode.core.domain.usecase.detail.DetailInteractor
import com.funcode.core.domain.usecase.detail.DetailUseCase
import com.funcode.core.utils.DataDummy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val movieDummy = DataDummy.generateDummyMovie()[0]
    private val filmid = movieDummy.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var detailUseCase: DetailUseCase

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Film>


    @Before
    fun setUp() {
        detailUseCase = DetailInteractor(movieRepository)
        viewModel = DetailViewModel(detailUseCase)
        viewModel.setSelectedFilm(filmid)
    }

    @Test
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun getFilm() = runBlockingTest {
        suspend {
            launch {
                val flowMovieData = flowOf(movieDummy)
                Mockito.`when`(movieRepository.getFilm(filmid)).thenReturn(flowMovieData)
                Mockito.`when`(detailUseCase.getFilm(filmid)).thenReturn(flowMovieData)
                viewModel.film.observeForever(observer)
                flowMovieData.collect {
                    Mockito.verify(observer).onChanged(it)
                    Assert.assertEquals(movieDummy, it)
                }

            }
        }

    }
}
