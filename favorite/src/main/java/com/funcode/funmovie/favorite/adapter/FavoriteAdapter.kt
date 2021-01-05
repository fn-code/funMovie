package com.funcode.funmovie.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.funcode.core.domain.model.Film
import com.funcode.funmovie.favorite.FavoriteFragmentCallback
import com.funcode.funmovie.favorite.R
import kotlinx.android.synthetic.main.items_favorite_list.view.*

class FavoriteAdapter(private val callback: FavoriteFragmentCallback): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var listMovie = ArrayList<Film>()

    fun setMovie(film: List<Film>?) {
        if (film == null) return
        listMovie.clear()
        listMovie.addAll(film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_favorite_list, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film) {
            with(itemView) {
                tv_item_title.text = film.title
                tv_item_released.text = "Released ${film.releaseDate}"
                tv_item_rating.text = "Rating ${film.voteAverage}"
                tv_item_overview.text = film.overview
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185${film.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_loading))
                    .into(img_poster)

                setOnClickListener { callback.onClick(film) }
                btn_remove_favorite.setOnClickListener { callback.deleteFavorite(film) }
            }
        }
    }
}