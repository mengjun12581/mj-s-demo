package com.mj.kotlindemo


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_third.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val data = ArrayList<String>()
        for (i in 0..100) {
            data.add("办公大楼$i")
        }

        recyclerView2.layoutManager = LinearLayoutManager(activity)
        recyclerView2.isNestedScrollingEnabled = true
        recyclerView2.setHasFixedSize(true)

        val mAdapter = MyRecyclerViewAdapter(data, activity as Context)
        mAdapter.setOnItemClickListener(object : MyRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                activity?.finish()
            }

        })
        recyclerView2.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        recyclerView2.adapter = mAdapter


    }

}
