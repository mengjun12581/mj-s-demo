package com.mj.kotlindemo


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.second_activity.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mLineData = makeLineData()
        setChartStyle(chart, mLineData, Color.WHITE)

        ft_radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val lineData = makeLineData()
            setChartStyle(chart, lineData, Color.WHITE)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {

        }
    }

    private fun makeLineData(): LineData {
        val x = ArrayList<String>()
        for (i: Int in 1..12) {
            x.add(i.toString() + "月")
        }

        val entries = ArrayList<Entry>()
        for (i: Int in 0..12) {
            val entry = Entry(i.toFloat(), Math.ceil(Math.random() * 12).toFloat())
            entries.add(entry)
        }

        val mLineDataSet = LineDataSet(entries, "测试数据")
        mLineDataSet.lineWidth = 2.0f
        mLineDataSet.circleSize = 5.0f
        mLineDataSet.color = Color.RED
        mLineDataSet.setCircleColor(Color.BLUE)
        mLineDataSet.circleHoleColor = Color.GREEN
        mLineDataSet.setDrawHighlightIndicators(true)
        mLineDataSet.highLightColor = Color.RED
        mLineDataSet.valueTextSize = 12f
        mLineDataSet.valueTextColor = Color.BLUE
        mLineDataSet.setDrawCircleHole(true)

        mLineDataSet.valueFormatter = (object : ValueFormatter() {
            override fun getFormattedValue(
                value: Float,
                entry: Entry?,
                dataSetIndex: Int,
                viewPortHandler: ViewPortHandler?
            ): String {
                return "y = $value"

            }
        })

        val mLineDataSets: MutableList<LineDataSet> = ArrayList()
        mLineDataSets.add(mLineDataSet)

        val mLineData = LineData(mLineDataSets as List<ILineDataSet>?)

        return mLineData

    }

    private fun setChartStyle(mLineChart: LineChart, mLineData: LineData, color: Int) {

        mLineChart.setDrawBorders(true)
        mLineChart.description.isEnabled = true
        val desc = Description()
        desc.text = "异常撤防同比增长率"
        desc.textSize = 14f
        desc.textColor = Color.BLUE
        mLineChart.description = desc
        mLineChart.setNoDataText("暂无数据")

        mLineChart.setTouchEnabled(true)
        mLineChart.isDragEnabled = true
        mLineChart.setScaleEnabled(true)
        mLineChart.setPinchZoom(true)

        mLineChart.setBackgroundColor(color)
        mLineChart.data = mLineData

        val mLegend = mLineChart.legend
        // 样式,默认为方形 150         mLegend.setFormSize(20.0f);// 样式图标大小
        mLegend.form = Legend.LegendForm.CIRCLE
        mLegend.textColor = Color.BLUE
        // 设置字体大小
        mLegend.textSize = 20.0f
        // 沿x轴动画，时间2000毫秒。
        mLineChart.animateX(1000)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
