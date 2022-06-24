package winwin.alwn.xsbnhnh.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import winwin.alwn.xsbnhnh.R
import winwin.alwn.xsbnhnh.base.BaseFragment
import winwin.alwn.xsbnhnh.binding.viewBinding
import winwin.alwn.xsbnhnh.data.Data
import winwin.alwn.xsbnhnh.databinding.FragmentMainBinding

class MainFragment(
    private val listener: BottomNavAdapter.Listener
) : BaseFragment<FragmentMainBinding>(R.layout.fragment_main){

    override val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    private val menuAdapter by lazy {
        MenuMainAdapter(Data.data, listener)
    }

    override fun setupViews() {
        with(binding){
            with(menuRecycler){
                adapter = menuAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            bannerContainer.banner.setBackgroundResource(R.drawable.banner1)
        }
    }

    override fun viewModelObservers() {
    }
}