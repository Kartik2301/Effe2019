package com.example.android.effe2019

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_proshows.*

class ProshowsFragment : Fragment() {
    val movies = arrayListOf<ProShow>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_proshows, container, false)
        //Inflate the layout for this fragment
        val movies = arrayListOf<ProShow>()


//        for (i in resources.getStringArray(R.array.titles).indices) {
//            movies.add(
//                ProShow(
////                    posters.getResourceId(i, -1),
////                    resources.getStringArray(R.array.titles)[i],
////                    resources.getStringArray(R.array.overviews)[i]
//                R.drawable.icon,"hello","bye"
//                )
//            )
//        }
//        check_scaling.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) rootView.viewPager.smallScaleFactor =
//                0.9f else rootView.viewPager.smallScaleFactor = 1f
//        }
//
//        check_alpha_changing.setOnCheckedChangeListener { _, isChecked ->
//            rootView.viewPager.smallAlphaFactor = if (isChecked) 0.5f else 1f
//        }
//
//        check_enable_auto_sliding.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) rootView.viewPager.autoSlideTime =
//                3 else rootView.viewPager.autoSlideTime = CardSliderViewPager.STOP_AUTO_SLIDING
//        }
//        check_infinite_indicator.setOnCheckedChangeListener { _, isChecked ->
//            indicator.indicatorsToShow =
//                if (isChecked) 5 else CardSliderIndicator.UNLIMITED_INDICATORS
//        }
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val posters = resources.obtainTypedArray(R.array.posters)


        for (i in resources.getStringArray(R.array.titles).indices) {
            movies.add(
                ProShow(
                   posters.getResourceId(i, -1),
                   resources.getStringArray(R.array.titles)[i],
                   resources.getStringArray(R.array.overviews)[i]
                )
            )
        }
        posters.recycle()
        viewPager.adapter = MovieAdapter(movies)

    }
}

