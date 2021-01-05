package com.funcode.funmovie.home.content


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.funcode.funmovie.R
import com.funcode.funmovie.home.SectionPagerAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val sectionPager = SectionPagerAdapter(childFragmentManager)
            dashboard_viewpager.adapter = sectionPager
            page1_tabs.setupWithViewPager(dashboard_viewpager)

            val movieLayout = LinearLayout(activity)
            movieLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            movieLayout.gravity = Gravity.CENTER

            val movie = TextView(activity)
            movie.text = getString(R.string.movie_tab_title)
            movie.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_movie_24, 0, 0, 0)
            movie.compoundDrawablePadding = 16
            movieLayout.addView(movie)

            val tvLayout = LinearLayout(activity)
            tvLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            tvLayout.gravity = Gravity.CENTER

            val tvshow = TextView(activity)
            tvshow.text = getString(R.string.tvshow_tab_title)
            tvshow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_tv_24, 0, 0, 0)
            tvshow.compoundDrawablePadding = 16
            tvLayout.addView(tvshow)
            val tab0 = page1_tabs.getTabAt(0)
            val tab1 = page1_tabs.getTabAt(1)

            tab0?.customView = movieLayout
            tab1?.customView = tvLayout

        }
    }
}