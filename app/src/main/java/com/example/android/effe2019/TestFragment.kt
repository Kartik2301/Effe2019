package com.example.android.effe2019

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

import com.ramotion.garlandview.TailLayoutManager
import com.ramotion.garlandview.TailRecyclerView
import com.ramotion.garlandview.TailSnapHelper
import com.ramotion.garlandview.header.HeaderTransformer

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import java.util.ArrayList

import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TestFragment(var events: ArrayList<DataForEvents>?) : Fragment() {
    internal lateinit var rootView: View
    private var listViewupdates: ListView? = null
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


    @SuppressLint("CheckResult")
    fun onFakerReady() {
        Single.create(SingleOnSubscribe<List<List<InnerData>>> { e ->
            val outerData = ArrayList<List<InnerData>>()
            val outerData1 = ArrayList<List<InnerData>>()
            var i = 0
            var check = 0
            var mark = 0
            val innerDataforInformal = ArrayList<InnerData>()
            val innerDataforMusic = ArrayList<InnerData>()
            val innerDataforGaming = ArrayList<InnerData>()
            val innerDataforDance = ArrayList<InnerData>()
            val innerDataforDrama = ArrayList<InnerData>()
            val innerDataforLiterature = ArrayList<InnerData>()
            val innerDataforFineArts = ArrayList<InnerData>()
            val innerDataforMainStage = ArrayList<InnerData>()

            var j = 0
                while (j < events!!.size && !e.isDisposed) {
                    when(events!!.get(j).categories.toString()){
                        "informal" -> innerDataforInformal.add(createInnerData(8, events!!.get(j)))
                        "music" -> innerDataforMusic.add(createInnerData(2, events!!.get(j)))
                        "gaming" -> innerDataforGaming.add(createInnerData(7, events!!.get(j)))
                        "dance" -> innerDataforDance.add(createInnerData(3, events!!.get(j)))
                        "dramatics" -> innerDataforDrama.add(createInnerData(1, events!!.get(j)))
                        "literature" -> innerDataforLiterature.add(createInnerData(6, events!!.get(j)))
                        "fine arts" -> innerDataforFineArts.add(createInnerData(4, events!!.get(j)))
                        "main stage" -> innerDataforMainStage.add(createInnerData(0, events!!.get(j)))
                    }
                    j++
                }
                outerData.add(innerDataforMainStage)
                outerData.add(innerDataforDrama)
                outerData.add(innerDataforMusic)
                outerData.add(innerDataforDance)
                outerData.add(innerDataforFineArts)
                outerData.add(innerDataforLiterature)
                outerData.add(innerDataforGaming)
                outerData.add(innerDataforInformal)

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

    private fun createInnerData(i: Int, get: DataForEvents): InnerData {
        return InnerData(
            get.categories.toString(),
            get.name!!,
            get.location!!,
            get.time!!,
            get.description!!,
            get.imageUrl!!,
            parent[i],
            get.date!!,
            get.organizers
        )
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun OnInnerItemClick(item: InnerItem) {
        val itemData = item.itemData ?: return;
        if (itemData == null) {
            return;
        }
        val name : TextView
        val date : TextView
        val description : TextView
        val image : ImageView
        val myDialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myview = inflater.inflate(R.layout.custominputfield, null)
        name = myview.findViewById(R.id.name)
        date = myview.findViewById(R.id.date)
        image = myview.findViewById(R.id.imageView)
        description = myview.findViewById(R.id.profession)
        name.text = itemData.title
        date.text = itemData.date
        val organizers: ArrayList<DataForOrganizers> = itemData.organizers as ArrayList<DataForOrganizers>
        Glide.with(image.context)
            .load(itemData.Url)
            .into(image)
        listViewupdates = myview.findViewById(R.id.list) as ListView
        val userAdapter: OrganizerAdapter = activity?.let { OrganizerAdapter(it, organizers) }!!
        listViewupdates!!.adapter = userAdapter
        description.text = itemData.description
        myDialog.setView(myview)
        val dialog: AlertDialog = myDialog.create();
        dialog.show()
    }
}

