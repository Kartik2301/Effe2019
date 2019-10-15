package com.example.android.effe2019

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kannan.glazy.GlazyCard
import com.kannan.glazy.Utils
import com.kannan.glazy.pager.GlazyFragmentPagerAdapter
import com.kannan.glazy.transformers.GlazyPagerTransformer
import com.kannan.glazy.views.GlazyImageView.ImageCutType
import kotlinx.android.synthetic.main.fragment_proshows.*
import androidx.fragment.app.FragmentPagerAdapter

class ProshowsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_proshows, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager(view)
    }

    private fun setupViewPager(view: View) {
        val resources = context!!.resources

        val imgNeeti = resources.getIdentifier("img_neeti", "drawable", activity!!.packageName)
        val imgHumour = resources.getIdentifier("comedy_night", "drawable", activity!!.packageName)
        val imgEDM = resources.getIdentifier("edm", "drawable", activity!!.packageName)
        val imgRock = resources.getIdentifier("rockband", "drawable", activity!!.packageName)
        val imgkavyom = resources.getIdentifier("kavyom", "drawable", activity!!.packageName)

        val desc_celeb = "They say that when you become a celebrity ,you own the world and the world owns you. " +
                "So get ready to own the world as the bar has been raised up higher. After superstars like Neha " +
                "Kakkar,Benny Dayal and Farhan Akhtar, Effervescence’19 proudly presents to you Neeti Mohan performing this October !"

        val desc_edm = "EDM at Effervescence has always been exhilarating, and to continue the legacy, we present before you the EDM Sensation 'Ritviz'"+
                "Keep your heart within, for it may ebb away at the thought of how great an evening is set." +
                "Get ready to dance in the spine-chilling caravan of music"
                "Get ready to be shimmy evermore."

        val desc_kavyom = "Kavyom brings with itself the aesthetic beauty of poetry. And, this year, the Stage will be taken over by the duo of Sunil Jogi and Shlesh Gautam, who are ready to enchant you with their poetic brilliance. Keep still, for one excerpt may change it all."

        val desc_humour = " In a long-standing association with Bingo Comedy Adda, we proudly present you all, with the Man of the Night, Anubhav Singh Bassi. "+
                "So gear up for the night of 18th October to give yourselves jolts of laughter and tickles of giggles in the Main Auditorium and disregard all your woes and worries as you laugh your hearts out..."

        val desc_local = "The Rock Culture is a new herald to a Cultural Festival. Effervescence has witnessed an altogether different level of rock performances in the past, and our next lineup presents our integrity in bringing out a new dimension to Rock Music. Behold, for here comes 'When Chai Met Toast', the Renowned Indian Rock Band bringing together the North and the South."

        val pagerAdapter = GlazyFragmentPagerAdapter(childFragmentManager, context)

        pagerAdapter.addCardItem(
            GlazyCard()
                .withTitle("CELEB NIGHT")
                .withSubTitle("19th October")
                .withDescription(desc_celeb.toUpperCase())
                .withImageRes(imgNeeti)
                .withImageCutType(ImageCutType.WAVE)
                .withImageCutHeightDP(50)
        )

        pagerAdapter.addCardItem(
            GlazyCard()
                .withTitle("ROCK NIGHT")
                .withSubTitle("18th October")
                .withDescription(desc_local.toUpperCase())
                .withImageRes(imgRock)
                .withImageCutType(ImageCutType.ARC)
                .withImageCutHeightDP(50)
        )


        pagerAdapter.addCardItem(
            GlazyCard()
                .withTitle("HUMOUR NIGHT")
                .withSubTitle("18th October")
                .withDescription(desc_humour.toUpperCase())
                .withImageRes(imgHumour)
                .withImageCutType(ImageCutType.LINE_POSITIVE)
                .withImageCutHeightDP(50)
        )


        pagerAdapter.addCardItem(
            GlazyCard()
                .withTitle("EDM NIGHT")
                .withSubTitle("17th October")
                .withDescription(desc_edm.toUpperCase())
                .withImageRes(imgEDM)
                .withImageCutType(ImageCutType.WAVE)
                .withImageCutHeightDP(50)
        )
        pagerAdapter.addCardItem(
            GlazyCard()
                .withTitle("KAVYOM")
                .withSubTitle("17th October")
                .withDescription(desc_kavyom.toUpperCase())
                .withImageRes(imgkavyom)
                .withImageCutType(ImageCutType.WAVE)
                .withImageCutHeightDP(50)
        )
        pager.adapter = pagerAdapter
        pager.pageMargin = Utils.dpToPx(context, 25)
        pager.setPageTransformer(false, GlazyPagerTransformer())
    }


}
