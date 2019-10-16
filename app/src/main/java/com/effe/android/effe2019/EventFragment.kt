package com.effe.android.effe2019
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.ramotion.garlandview.TailLayoutManager
//import com.ramotion.garlandview.TailRecyclerView
//import com.ramotion.garlandview.TailSnapHelper
//import com.ramotion.garlandview.header.HeaderTransformer
//import io.reactivex.Single
//import io.reactivex.SingleEmitter
//import io.reactivex.SingleOnSubscribe
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.functions.Consumer
//import io.reactivex.schedulers.Schedulers
//import org.greenrobot.eventbus.EventBus
//import org.greenrobot.eventbus.Subscribe
//import java.util.ArrayList
//
//class EventFragment : Fragment() {
//    private val OUTER_COUNT = 2
//    private val INNER_COUNT = 1
//    lateinit var parent: Array<String>
//
//    private lateinit var rootView: View
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//
//        //Inflate the layout for this fragment
//
//        rootView = inflater.inflate(R.layout.fragment_event, container, false)
//        parent = arrayOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMGiD7IGWMfwV-kDlqvlPn87uPYIRtJmvNWDSggkZM5s7m2XsGMg")
//
//        Single.create(SingleOnSubscribe<List<List<InnerData>>> { e ->
//            val outerData = ArrayList<List<InnerData>>()
//            var i = 0
//            while (i < OUTER_COUNT && !e.isDisposed) {
//                val innerData = ArrayList<InnerData>()
//                var j = 0
//                while (j < INNER_COUNT && !e.isDisposed) {
//                    innerData.add(createInnerData())
//                    j++
//                }
//                outerData.add(innerData)
//                i++
//            }
//
//            if (!e.isDisposed) {
//                e.onSuccess(outerData)
//            }
//        })
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { outerData -> initRecyclerView(outerData) }
//        return rootView
//    }
//    @Subscribe
//    override fun onStart() {
//        super.onStart()
//        onFakerReady()
//        EventBus.getDefault().register(activity)
//    }
//    override fun onStop() {
//        super.onStop()
//        EventBus.getDefault().unregister(activity)
//    }
//    fun onFakerReady() {
//
//    }
//
//    private fun initRecyclerView(data: List<List<InnerData>>) {
//
//        val rv = rootView.findViewById(R.id.recycler_view) as TailRecyclerView
//        (rv.layoutManager as TailLayoutManager).setPageTransformer(HeaderTransformer())
//        rv.adapter = OuterAdapter(data)
//
//        TailSnapHelper().attachToRecyclerView(rv)
//    }
//    private fun createInnerData(): InnerData {
//        return InnerData(
//            "Informal",
//            "Some Random Event",
//            "Main Audi",
//            "6:00 pm",
//            "Important Event",
//            "https://images.unsplash.com/photo-1524368535928-5b5e00ddc76b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
//            parent[0],
//            "6 October"
//        )
//    }
//
//}
