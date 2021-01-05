package com.funcode.funmovie.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.funcode.funmovie.favorite.adapter.SectionFavoritePagerAdapter
import com.funcode.funmovie.favorite.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sectionPager = SectionFavoritePagerAdapter(supportFragmentManager)
        binding.favoriteViewpager.adapter = sectionPager
        binding.page2Tabs.setupWithViewPager(binding.favoriteViewpager)

        val movieLayout = LinearLayout(this)
        movieLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        movieLayout.gravity = Gravity.CENTER

        val movie = TextView(this)
        movie.text = getString(com.funcode.funmovie.R.string.movie_tab_title)
        movie.setCompoundDrawablesWithIntrinsicBounds(com.funcode.funmovie.R.drawable.ic_baseline_movie_24, 0, 0, 0)
        movie.compoundDrawablePadding = 16
        movieLayout.addView(movie)

        val tvLayout = LinearLayout(this)
        tvLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        tvLayout.gravity = Gravity.CENTER

        val tvshow = TextView(this)
        tvshow.text = getString(com.funcode.funmovie.R.string.tvshow_tab_title)
        tvshow.setCompoundDrawablesWithIntrinsicBounds(com.funcode.funmovie.R.drawable.ic_baseline_tv_24, 0, 0, 0)
        tvshow.compoundDrawablePadding = 16
        tvLayout.addView(tvshow)
        val tab0 = binding.page2Tabs.getTabAt(0)
        val tab1 = binding.page2Tabs.getTabAt(1)

        tab0?.customView = movieLayout
        tab1?.customView = tvLayout
    }
}