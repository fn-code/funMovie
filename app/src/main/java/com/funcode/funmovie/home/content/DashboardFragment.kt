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
import com.funcode.funmovie.databinding.FragmentDashboardBinding
import com.funcode.funmovie.home.SectionPagerAdapter


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val sectionPager = SectionPagerAdapter(childFragmentManager)

            binding.dashboardViewpager.adapter = sectionPager
            binding.page1Tabs.setupWithViewPager(binding.dashboardViewpager)

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
            val tab0 = binding.page1Tabs.getTabAt(0)
            val tab1 = binding.page1Tabs.getTabAt(1)

            tab0?.customView = movieLayout
            tab1?.customView = tvLayout

        }
    }
}