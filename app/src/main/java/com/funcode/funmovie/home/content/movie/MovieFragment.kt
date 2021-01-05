package com.funcode.funmovie.home.content.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.funcode.core.domain.model.Film
import com.funcode.funmovie.R
import com.funcode.funmovie.adapter.MovieAdapter
import com.funcode.funmovie.detail.DetailMovieActivity
import com.funcode.funmovie.home.content.FilmFragmentCallback
import com.funcode.funmovie.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_film.*
import kotlinx.android.synthetic.main.message.*


@AndroidEntryPoint
class MovieFragment : Fragment(),
    FilmFragmentCallback {

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter(this)

            progress_bar.visibility = View.VISIBLE
            homeViewModel.getAllMovie().observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    progress_bar.visibility = View.GONE
                    rv_movie.visibility = View.VISIBLE
                    movieAdapter.setMovie(movie.data)
                    movieAdapter.notifyDataSetChanged()
                } else {
                    progress_bar.visibility = View.GONE
                    rv_movie.visibility = View.GONE
                    message_view.visibility = View.VISIBLE
                    tv_message.text = "Daftar Movie Yang Ingin Ditampilkan Kosong."
                }
            })

            with(rv_movie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onClick(film: Film) {
        val intent = Intent(activity, DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.EXTRA_FILM, film.id)
        }
        activity?.startActivity(intent)
    }

}