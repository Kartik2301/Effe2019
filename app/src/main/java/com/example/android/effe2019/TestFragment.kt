package com.example.android.effe2019

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.ramotion.garlandview.TailLayoutManager
import com.ramotion.garlandview.TailRecyclerView
import com.ramotion.garlandview.TailSnapHelper
import com.ramotion.garlandview.header.HeaderTransformer

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import java.util.ArrayList

import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class TestFragment : Fragment() {
    internal lateinit var rootView: View
    lateinit var parent: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_event, container, false)
         parent = arrayOf(
             "https://images.unsplash.com/photo-1524368535928-5b5e00ddc76b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
             "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnN4_QOgIJ8xDmUIgVkdvQ2VA0dzaoDoDpDEwQPV_v3P6jJyZ7",
             "https://images.pexels.com/photos/534283/pexels-photo-534283.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
             "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMGiD7IGWMfwV-kDlqvlPn87uPYIRtJmvNWDSggkZM5s7m2XsGMg",
             "https://images.pexels.com/photos/1047540/pexels-photo-1047540.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
             "http://media.virbcdn.com/cdn_images/resize_1024x1365/7d/e10cf2f1e83e8aad-oldprojector.jpg",
             "https://images.pexels.com/photos/1437872/pexels-photo-1437872.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
             "https://image.redbull.com/rbcom/052/2018-11-05/b32a8a5c-450c-47a6-8b5a-f0131bb916fd/0010/1/1050/656/1/gamespot.jpg",
             "https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/69289630_2909606012442658_9070012618869047296_n.jpg?_nc_cat=106&_nc_oc=AQn8MYayfhipgRbOAdxoR-YqxOHFEYxm8i0voxXErqioQXn454GhT8JVStXGPmqqVoA&_nc_ht=scontent.fdel29-1.fna&oh=1ca5f8775a6447ef55bde00293fcf65c&oe=5DF5BEA9")
        return rootView
    }

    override fun onStart() {
        super.onStart()
        onFakerReady()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


    fun onFakerReady() {
        Single.create(SingleOnSubscribe<List<List<InnerData>>> { e ->
            val outerData = ArrayList<List<InnerData>>()
            var i = 0
            while (i < 9 && !e.isDisposed) {
                val innerData = ArrayList<InnerData>()
                var j = 0
                while (j < 4 && !e.isDisposed) {
                    innerData.add(createInnerData(i))
                    j++
                }
                outerData.add(innerData)
                i++
            }

            if (!e.isDisposed) {
                e.onSuccess(outerData)
            }
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { outerData -> initRecyclerView(outerData) }
    }

    private fun initRecyclerView(data: List<List<InnerData>>) {

        val rv = rootView.findViewById<View>(R.id.recycler_view) as TailRecyclerView
        (rv.layoutManager as TailLayoutManager).setPageTransformer(HeaderTransformer())
        rv.adapter = OuterAdapter(data)

        TailSnapHelper().attachToRecyclerView(rv)
    }

    private fun createInnerData(i: Int): InnerData {
        return InnerData(
            "Informal",
            "Some Random Event",
            "Main Audi",
            "6:00 pm",
            "Important Event",
            "https://github.com/Effervescence-IIITA/Effervescence18/raw/master/images/events/cognoscentia.jpg",
            parent[i],
            "6 October"
        )
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun OnInnerItemClick(item: InnerItem) {
        val itemData = item.itemData ?: return
//
        //        DetailsActivity.start(this,
        //                item.getItemData().name, item.mAddress.getText().toString(),
        //                item.getItemData().avatarUrl, item.itemView, item.mAvatarBorder);
    }

}

