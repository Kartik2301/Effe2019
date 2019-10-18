package com.effe19.android.effe2019

import android.view.View
import com.github.islamkhsh.CardSliderAdapter
import kotlinx.android.synthetic.main.item_card.view.*

class MovieAdapter(items : ArrayList<ProShow>) : CardSliderAdapter<ProShow>(items) {

    override fun bindView(position: Int, itemContentView: View, item: ProShow?) {

        item?.run {
            itemContentView.movie_poster.setImageResource(poster)
            itemContentView.movie_title.text = title
            itemContentView.movie_overview.text = overview
        }
    }

    override fun getItemContentLayout(position: Int) = R.layout.item_card
}