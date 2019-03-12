package io.djnr.backdrop.activities

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import io.djnr.backdrop.R
import io.djnr.backdrop.adapters.MainPagerAdapter
import io.djnr.backdrop.fragments.AccountFragment
import io.djnr.backdrop.fragments.AmbienceFragment
import io.djnr.backdrop.fragments.HomeFragment
import io.djnr.backdrop.fragments.SearchFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var mCompositeDisposable: CompositeDisposable? = null

    private val mTabIcons = arrayOf(
            R.drawable.ic_tab_home,
            R.drawable.ic_tab_search,
            R.drawable.ic_tab_ambience,
            R.drawable.ic_tab_account)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        Constants.sSpotifyAppRemote?.userApi
//                ?.capabilities
//                ?.setResultCallback { capabilities ->
//                    Log.i("NIGGUH", String.format("Can play on demand: %s", capabilities.canPlayOnDemand))
//                }


        var adapter = MainPagerAdapter(supportFragmentManager)
        vpMain.adapter = adapter
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(SearchFragment(), "Search")
        adapter.addFragment(AmbienceFragment(), "Ambience")
        adapter.addFragment(AccountFragment(), "Account")
        adapter.notifyDataSetChanged()
        vpMain.offscreenPageLimit = adapter.count

        tlMain.setupWithViewPager(vpMain)
        for (i in 0 until tlMain.tabCount) {
            tlMain.getTabAt(i)!!.icon = ContextCompat.getDrawable(this, mTabIcons[i])
        }

        val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
        tlMain.getTabAt(0)!!.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)

        tlMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
                tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabIconColor = ContextCompat.getColor(this@MainActivity, android.R.color.white)
                tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}
