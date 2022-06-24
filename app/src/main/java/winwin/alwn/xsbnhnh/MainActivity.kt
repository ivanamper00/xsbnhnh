package winwin.alwn.xsbnhnh

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import winwin.alwn.xsbnhnh.binding.viewBinding
import winwin.alwn.xsbnhnh.data.Data
import winwin.alwn.xsbnhnh.databinding.ActivityMainBinding
import winwin.alwn.xsbnhnh.fragment.BottomNavAdapter
import winwin.alwn.xsbnhnh.fragment.DetailsFragment
import winwin.alwn.xsbnhnh.fragment.MainFragment

class MainActivity : AppCompatActivity(),
BottomNavAdapter.Listener {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var fragments: List<Fragment>

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val menuAdapter by lazy {
        BottomNavAdapter(Data.data, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        fragments = listOf(
            MainFragment(this),
            DetailsFragment(Data.data[0]),
            DetailsFragment(Data.data[1]),
            DetailsFragment(Data.data[2]),
            DetailsFragment(Data.data[3]),
            DetailsFragment(Data.data[4])
        )

        viewPagerAdapter = ViewPagerAdapter(this, fragments)

        with(binding){
            with(viewPager){
                adapter = viewPagerAdapter
                offscreenPageLimit = 5
                isUserInputEnabled = false
                registerOnPageChangeCallback( object: ViewPager2.OnPageChangeCallback(){
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        showBottomNav(position != 0)
                    }
                })
            }

            with(menuRecycler){
                adapter = menuAdapter
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false )
            }
        }
    }

    private fun showBottomNav(b: Boolean) {
        binding.linearLayout.visibility = if(b) View.VISIBLE else View.GONE
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onMenuClick(position: Int) {
        binding.viewPager.currentItem = position
    }

    override fun onBackPressed() {
        if(binding.viewPager.currentItem > 0){
            binding.viewPager.currentItem = 0
        }else AlertDialog.Builder(this)
            .setTitle("Exit Application?")
            .setMessage("Do you want to exit?")
            .setPositiveButton("Ok"){ _,_ -> super.onBackPressed() }
            .setNegativeButton("Cancel"){ d, _ -> d.dismiss()}
            .show()
    }
}