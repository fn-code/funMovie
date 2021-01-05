package com.funcode.funmovie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.funcode.core.domain.model.Film
import com.funcode.funmovie.R
import com.funcode.funmovie.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }
    private val detailViewModel: DetailViewModel by viewModels()
    private var menu: Menu? = null
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val extras = intent.extras
        if (extras != null) {
            val filmid = extras.getInt(EXTRA_FILM, 0)
            detailViewModel.setSelectedFilm(filmid)
            detailViewModel.film.observe(this, { film ->
                if (film == null) {
                    binding.scrollView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.messageView.lnMessage.visibility = View.VISIBLE
                    binding.messageView.tvMessage.text = "Gagal menampilkan daftar film"
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.scrollView.visibility = View.VISIBLE
                    populateFilm(film)
                }
            })
        }
    }

    private fun populateFilm(film: Film) {
        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w500${film.backdropPath}")
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_loading)
            )
            .into(binding.imgPoster)

        binding.rating.text = "Rating ${film.voteAverage}"
        binding.filmTitle.text = film.title
        binding.filmReleased.text = "Released ${film.releaseDate}"
        binding.filmDesc.text = film.overview
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        detailViewModel.film.observe(this, { film ->
            if(film != null) {
                if(film.favorite == 1) {
                    setFavoritedState(true)
                } else {
                    setFavoritedState(false)
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            detailViewModel.setFavorited()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoritedState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_fill_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_white)
        }
    }
}