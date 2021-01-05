package com.funcode.funmovie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.funcode.core.domain.model.Film
import com.funcode.funmovie.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.message_view
import kotlinx.android.synthetic.main.activity_detail_movie.progress_bar
import kotlinx.android.synthetic.main.message.*

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private val detailViewModel: DetailViewModel by viewModels()
    private var menu: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val extras = intent.extras
        if (extras != null) {
            val filmid = extras.getInt(EXTRA_FILM, 0)
            detailViewModel.setSelectedFilm(filmid)
            detailViewModel.film.observe(this, { film ->
                if (film == null) {
                    scrollView.visibility = View.GONE
                    progress_bar.visibility = View.GONE
                    message_view.visibility = View.VISIBLE
                    tv_message.text = "Gagal menampilkan daftar film"
                } else {
                    progress_bar.visibility = View.GONE
                    scrollView.visibility = View.VISIBLE
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
            .into(img_poster)

        rating.text = "Rating ${film.voteAverage}"
        film_title.text = film.title
        film_released.text = "Released ${film.releaseDate}"
        film_desc.text = film.overview
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