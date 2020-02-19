package com.mj.kotlindemo


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_third.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ThirdFragment : Fragment() {

    private var index: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView.setBindView(recyclerView3)
        loadingView.showLoading()
        loadingView.setRefreshListener {
            when {
                index > 0 -> {
                    loadingView.showSuccess()
                    initView()
                    index = 0
                }
                index % 2 == 0 -> {
                    loadingView.showEmpty()
                    index++
                }
                else -> {
                    loadingView.showError()
                    index++
                }
            }
        }
        Thread(Runnable {
            Thread.sleep(3000)
            activity?.runOnUiThread {
                loadingView.showError()
            }
        }).start()

    }

    private fun initView() {
        val data = ArrayList<String>()
        for (i in 0..100) {
            data.add("我的关注$i")
        }

        recyclerView3.layoutManager = LinearLayoutManager(activity)
        recyclerView3.isNestedScrollingEnabled = true
        recyclerView3.setHasFixedSize(true)

        val mAdapter = MyRecyclerViewAdapter(data, activity as Context)
        mAdapter.setOnItemClickListener(object : MyRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                activity?.finish()
            }

        })
        recyclerView3.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        recyclerView3.adapter = mAdapter


    }


}
