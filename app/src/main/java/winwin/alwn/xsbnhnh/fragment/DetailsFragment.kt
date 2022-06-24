package winwin.alwn.xsbnhnh.fragment

import androidx.core.text.HtmlCompat
import winwin.alwn.xsbnhnh.R
import winwin.alwn.xsbnhnh.base.BaseFragment
import winwin.alwn.xsbnhnh.binding.viewBinding
import winwin.alwn.xsbnhnh.data.DataModel
import winwin.alwn.xsbnhnh.databinding.FragmentDetailsBinding

class DetailsFragment(
    private val content: DataModel
): BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    override val binding: FragmentDetailsBinding by viewBinding(FragmentDetailsBinding::bind)

    override fun setupViews() {
        with(binding){
            bannerContainer.banner.setBackgroundResource(content.image)
            with(menuItem){
                menuTitle.text = content.title
                menuIcon.setBackgroundResource(content.icon)
            }
            textDescription.text = HtmlCompat.fromHtml(content.desc, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun viewModelObservers() {  }
}