package com.mj.kotlindemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.RadioButton
import kotlinx.android.synthetic.main.second_activity.*

class SecondActivity : AppCompatActivity() {

    private var mFragments = ArrayList<Fragment>()
    private var mAdapter : MyFragmentPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.second_activity)
        initView()
    }

    private fun initView() {
        mFragments.add(FirstFragment.newInstance("", ""))
        mFragments.add(SecondFragment())
        mFragments.add(ThirdFragment())

        mAdapter = MyFragmentPagerAdapter(supportFragmentManager, mFragments)
        fragment_vp.adapter = mAdapter

        fragment_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                val radioButton = radioGroup.getChildAt(p0) as RadioButton
                radioButton.isChecked = true
                title_txt.text = radioButton.text
            }

        })

        fragment_vp.setCurrentItem(0, false)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            for(i in 0..group.childCount){
                if(group.getChildAt(i).id == checkedId){
                    fragment_vp.setCurrentItem(i ,false)
                    break
                }
            }
        }

        back.setOnClickListener{
            finish()
        }

    }


    class MyFragmentPagerAdapter(fm : FragmentManager, list : ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

        private val mList = list

        override fun getItem(position: Int): Fragment {
            return if (this.mList.isEmpty()) Fragment() else this.mList[position]
        }

        override fun getCount(): Int {
            return if (this.mList.isEmpty()) 0 else this.mList.size
        }





    }

}