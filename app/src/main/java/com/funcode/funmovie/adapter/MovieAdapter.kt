package com.funcode.funmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.funcode.core.domain.model.Film
import com.funcode.funmovie.R
import com.funcode.funmovie.databinding.ItemsMovieListBinding
import com.funcode.funmovie.home.content.FilmFragmentCallback

class MovieAdapter(private val callback: FilmFragmentCallback): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovie = ArrayList<Film>()
    private var _binding: ItemsMovieListBinding? = null
    private val binding get() = _binding!!

    fun setMovie(film: List<Film>?) {
        if (film == null) return
        listMovie.clear()
        listMovie.addAll(film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        _binding = ItemsMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movie_list, parent, false)
        val view = binding.root
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
                binding
                binding.tvItemTitle.text = film.title
                binding.tvItemReleased.text = "Released ${film.releaseDate}"
                binding.tvItemRating.text = "Rating ${film.voteAverage}"
                binding.tvItemOverview.text = film.overview
                Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w185${film.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_loading)
                    )
                    .into(binding.imgPoster)
            }
            itemView.setOnClickListener { callback.onClick(film) }
        }

    }
}