package com.effe.android.effe2019

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramotion.garlandview.header.HeaderDecorator
import com.ramotion.garlandview.header.HeaderItem
import com.ramotion.garlandview.inner.InnerLayoutManager
import com.ramotion.garlandview.inner.InnerRecyclerView
import java.util.ArrayList


class OuterItem(itemView: View, pool: RecyclerView.RecycledViewPool) : HeaderItem(itemView) {

    private val mHeader: View
    private val mHeaderAlpha: View

    private val mRecyclerView: InnerRecyclerView

    private val mAvatar: ImageView
    private val mHeaderCaption1: TextView
    private val mHeaderCaption2: TextView
    private val mName: TextView
    private val mInfo: TextView

    private val mMiddle: View
    private val mMiddleAnswer: View
    private val mFooter: View

    private val mMiddleCollapsible = ArrayList<View>(2)

    private val m10dp: Int
    private val m120dp: Int
    private val mTitleSize1: Int
    private val mTitleSize2: Int

    private var mIsScrolling: Boolean = false

    init {

        // Init header
        m10dp = itemView.context.resources.getDimensionPixelSize(R.dimen.dp10)
        m120dp = itemView.context.resources.getDimensionPixelSize(R.dimen.dp120)
        mTitleSize1 =
            itemView.context.resources.getDimensionPixelSize(R.dimen.header_title2_text_size)
        mTitleSize2 =
            itemView.context.resources.getDimensionPixelSize(R.dimen.header_title2_name_text_size)

        mHeader = itemView.findViewById(R.id.header)
        mHeaderAlpha = itemView.findViewById(R.id.header_alpha)

        mHeaderCaption1 = itemView.findViewById(R.id.header_text_1) as TextView
        mHeaderCaption2 = itemView.findViewById(R.id.header_text_2) as TextView
        mName = itemView.findViewById(R.id.event_name) as TextView
        mInfo = itemView.findViewById(R.id.event_info) as TextView
        mAvatar = itemView.findViewById(R.id.avatar) as ImageView

        mMiddle = itemView.findViewById(R.id.header_middle)
        mMiddleAnswer = itemView.findViewById(R.id.header_middle_answer)
        mFooter = itemView.findViewById(R.id.header_footer)

        mMiddleCollapsible.add(mAvatar.parent as View)
        mMiddleCollapsible.add(mName.parent as View)

        // Init RecyclerView
        mRecyclerView = itemView.findViewById(R.id.recycler_view) as InnerRecyclerView
        mRecyclerView.setRecycledViewPool(pool)
        mRecyclerView.setAdapter(InnerAdapter())

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                mIsScrolling = newState != RecyclerView.SCROLL_STATE_IDLE
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                onItemScrolled(recyclerView, dx, dy)
            }
        })

        mRecyclerView.addItemDecoration(
            HeaderDecorator(
                itemView.context.resources.getDimensionPixelSize(R.dimen.inner_item_height),
                itemView.context.resources.getDimensionPixelSize(R.dimen.inner_item_offset)
            )
        )

        // Init fonts
    }

    override fun isScrolling(): Boolean {
        return mIsScrolling
    }

    override fun getViewGroup(): InnerRecyclerView {
        return mRecyclerView
    }

    override fun getHeader(): View {
        return mHeader
    }

    override fun getHeaderAlphaView(): View {
        return mHeaderAlpha
    }

    internal fun setContent(innerDataList: List<InnerData>) {
        val context = itemView.context

        val header = innerDataList.subList(0, 1)[0]
        val tail = innerDataList.subList(1, innerDataList.size)

        mRecyclerView.layoutManager = InnerLayoutManager()
        (mRecyclerView.adapter as InnerAdapter).addData(tail)
        GlideApp.with(context)
            .load(header.ParentUrl)
            .placeholder(R.drawable.avatar_placeholder)
            .into(mAvatar)

        val title1 = header.Orgaizser + "?"

        val title2 = SpannableString("  "+header.Orgaizser+"  ")
        title2.setSpan(AbsoluteSizeSpan(mTitleSize1), 0, title1.length, SPAN_INCLUSIVE_INCLUSIVE)
        title2.setSpan(
            AbsoluteSizeSpan(mTitleSize2),
            title1.length,
            title2.length,
            SPAN_INCLUSIVE_INCLUSIVE
        )
        title2.setSpan(
            ForegroundColorSpan(Color.argb(204, 255, 255, 255)),
            title1.length,
            title2.length,
            SPAN_INCLUSIVE_INCLUSIVE
        )

        mHeaderCaption1.text = title1
        mHeaderCaption2.text = title2

        mName.text = String.format("%s %s", header.title, context.getString(R.string.asked))
        mInfo.text = String.format(
            "%s %s · %s",
            header.date,
            context.getString(R.string.years),
            header.Location
        )
    }

    internal fun clearContent() {
        Glide.with(mAvatar.context).clear(mAvatar)
        (mRecyclerView.adapter as InnerAdapter).clearData()
    }

    private fun computeRatio(recyclerView: RecyclerView): Float {
        val child0 = recyclerView.getChildAt(0)
        val pos = recyclerView.getChildAdapterPosition(child0)
        if (pos != 0) {
            return 0f
        }

        val height = child0.height
        val y = Math.max(0f, child0.y)
        return y / height
    }

    private fun onItemScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val ratio = computeRatio(recyclerView)

        val footerRatio =
            Math.max(0f, Math.min(FOOTER_RATIO_START, ratio) - FOOTER_RATIO_DIFF) / FOOTER_RATIO_MAX
        val avatarRatio =
            Math.max(0f, Math.min(AVATAR_RATIO_START, ratio) - AVATAR_RATIO_DIFF) / AVATAR_RATIO_MAX
        val answerRatio =
            Math.max(0f, Math.min(ANSWER_RATIO_START, ratio) - ANSWER_RATIO_DIFF) / ANSWER_RATIO_MAX
        val middleRatio =
            Math.max(0f, Math.min(MIDDLE_RATIO_START, ratio) - MIDDLE_RATIO_DIFF) / MIDDLE_RATIO_MAX

        ViewCompat.setPivotY(mFooter, 0f)
        ViewCompat.setScaleY(mFooter, footerRatio)
        ViewCompat.setAlpha(mFooter, footerRatio)

        ViewCompat.setPivotY(mMiddleAnswer, mMiddleAnswer.height.toFloat())
        ViewCompat.setScaleY(mMiddleAnswer, 1f - answerRatio)
        ViewCompat.setAlpha(mMiddleAnswer, 0.5f - answerRatio)

        ViewCompat.setAlpha(mHeaderCaption1, answerRatio)
        ViewCompat.setAlpha(mHeaderCaption2, 1f - answerRatio)

        val mc2 = mMiddleCollapsible[1]
        ViewCompat.setPivotX(mc2, 0f)
        ViewCompat.setPivotY(mc2, (mc2.height / 2).toFloat())

        for (view in mMiddleCollapsible) {
            ViewCompat.setScaleX(view, avatarRatio)
            ViewCompat.setScaleY(view, avatarRatio)
            ViewCompat.setAlpha(view, avatarRatio)
        }

        val lp = mMiddle.layoutParams
        lp.height = m120dp - (m10dp * (1f - middleRatio)).toInt()
        mMiddle.layoutParams = lp
    }

    companion object {

        private val AVATAR_RATIO_START = 1f
        private val AVATAR_RATIO_MAX = 0.25f
        private val AVATAR_RATIO_DIFF = AVATAR_RATIO_START - AVATAR_RATIO_MAX

        private val ANSWER_RATIO_START = 0.75f
        private val ANSWER_RATIO_MAX = 0.35f
        private val ANSWER_RATIO_DIFF = ANSWER_RATIO_START - ANSWER_RATIO_MAX

        private val MIDDLE_RATIO_START = 0.7f
        private val MIDDLE_RATIO_MAX = 0.1f
        private val MIDDLE_RATIO_DIFF = MIDDLE_RATIO_START - MIDDLE_RATIO_MAX

        private val FOOTER_RATIO_START = 1.1f
        private val FOOTER_RATIO_MAX = 0.35f
        private val FOOTER_RATIO_DIFF = FOOTER_RATIO_START - FOOTER_RATIO_MAX
    }

}