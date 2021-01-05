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
import com.funcode.funmovie.favorite.databinding.ItemsFavoriteListBinding

class FavoriteAdapter(private val callback: FavoriteFragmentCallback): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var listMovie = ArrayList<Film>()
    private var _binding: ItemsFavoriteListBinding? = null
    private val binding get() = _binding!!

    fun setMovie(film: List<Film>?) {
        if (film == null) return
        listMovie.clear()
        listMovie.addAll(film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_favorite_list, parent, false)
        _binding = ItemsFavoriteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
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
                binding.tvItemTitle.text = film.title
                binding.tvItemReleased.text = "Released ${film.releaseDate}"
                binding.tvItemRating.text = "Rating ${film.voteAverage}"
                binding.tvItemOverview.text = film.overview
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185${film.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_loading))
                    .into(binding.imgPoster)

                setOnClickListener { callback.onClick(film) }
                binding.btnRemoveFavorite.setOnClickListener { callback.deleteFavorite(film) }
            }
        }
    }
}