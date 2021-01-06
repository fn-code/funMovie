package com.funcode.funmovie.favorite.tvshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.funcode.funmovie.di.CoreComponent
import com.funcode.core.domain.model.Film
import com.funcode.core.ui.ViewModelFactory
import com.funcode.funmovie.favorite.adapter.FavoriteAdapter
import com.funcode.funmovie.detail.DetailMovieActivity
import com.funcode.funmovie.favorite.FavoriteFragmentCallback
import com.funcode.funmovie.favorite.FavoriteViewModel
import com.funcode.funmovie.favorite.databinding.FragmentFilmFavoriteBinding
import com.funcode.funmovie.favorite.di.DaggerAppComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteTvShowFragment: Fragment(), FavoriteFragmentCallback {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    lateinit var favoriteAdapter : FavoriteAdapter
    private var _binding: FragmentFilmFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favoriteAdapter = FavoriteAdapter(this)
        _binding = FragmentFilmFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    CoreComponent::class.java
                )
            )
            .build()
            .inject(this)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val favoriteAdapter = FavoriteAdapter(this)
            binding.progressBar.visibility = View.VISIBLE
            favoriteViewModel.getTvShowFavorite().observe(viewLifecycleOwner, { film ->
                if(film != null) {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovie.visibility = View.VISIBLE
                    favoriteAdapter.setMovie(film)
                    favoriteAdapter.notifyDataSetChanged()
                } else {
                    binding.rvMovie.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.messageView.lnMessage.visibility = View.VISIBLE
                    binding.messageView.tvMessage.text = "Daftar Movie Favorite Yang Ingin Ditampilkan Kosong."
                }
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteAdapter
            }
        }
    }

    override fun deleteFavorite(film: Film) {
        favoriteViewModel.removeFilmFavorite(film)
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