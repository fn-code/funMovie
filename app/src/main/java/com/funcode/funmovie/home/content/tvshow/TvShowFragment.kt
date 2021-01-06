package com.funcode.funmovie.home.content.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.funcode.core.domain.model.Film
import com.funcode.funmovie.adapter.MovieAdapter
import com.funcode.funmovie.databinding.FragmentFilmBinding
import com.funcode.funmovie.detail.DetailMovieActivity
import com.funcode.funmovie.home.content.FilmFragmentCallback
import com.funcode.funmovie.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment(),
    FilmFragmentCallback {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val tvshowAdapter = MovieAdapter(this)
            binding.progressBar.visibility = View.VISIBLE
            homeViewModel.getTvShow().observe(viewLifecycleOwner, { tvshow ->
                if (tvshow != null) {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovie.visibility = View.VISIBLE
                    tvshowAdapter.setMovie(tvshow.data)
                    tvshowAdapter.notifyDataSetChanged()
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovie.visibility = View.GONE
                    binding.messageView.lnMessage.visibility = View.VISIBLE
                    binding.messageView.tvMessage.text = "Daftar Tv Show Yang Ingin Ditampilkan Kosong"
                }
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvshowAdapter
            }
        }
    }

    override fun onClick(film: Film) {
        val intent = Intent(activity, DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.EXTRA_FILM, film.id)
        }
        activity?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}