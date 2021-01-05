package com.funcode.funmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.funcode.core.domain.model.Film
import com.funcode.funmovie.R
import com.funcode.funmovie.home.content.FilmFragmentCallback
import kotlinx.android.synthetic.main.items_movie_list.view.*

class MovieAdapter(private val callback: FilmFragmentCallback): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovie = ArrayList<Film>()

    fun setMovie(film: List<Film>?) {
        if (film == null) return
        listMovie.clear()
        listMovie.addAll(film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movie_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film) {
            with(itemView) {
                tv_item_title.text = film.title
                tv_item_released.text = "Released ${film.releaseDate}"
                tv_item_rating.text = "Rating ${film.voteAverage}"
                tv_item_overview.text = film.overview
                Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w185${film.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_loading))
                    .into(img_poster)
            }
            itemView.setOnClickListener { callback.onClick(film) }
        }

    }
}