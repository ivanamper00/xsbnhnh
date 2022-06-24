package winwin.alwn.xsbnhnh.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import winwin.alwn.xsbnhnh.data.DataModel
import winwin.alwn.xsbnhnh.databinding.ItemMenuLeftBinding
import winwin.alwn.xsbnhnh.databinding.ItemMenuRightBinding

class MenuMainAdapter(
    private val list: List<DataModel>,
    private val listener: BottomNavAdapter.Listener
): RecyclerView.Adapter<MenuMainAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = if(viewType == 1) ItemMenuLeftBinding.inflate(inflater, parent, false)
        else ItemMenuRightBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder){
            setupSetView(list[position]){
                listener.onMenuClick(position + 1)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if(position%2 == 0) 1
        else 2
    }

    class Holder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setupSetView(content: DataModel, listener: ()-> Unit ){
            when(binding){
                is ItemMenuLeftBinding -> {
                    with(binding){
                        menuIcon.setBackgroundResource(content.icon)
                        menuTitle.text = content.title
                    }
                }
                is ItemMenuRightBinding -> {
                    with(binding){
                        menuIcon.setBackgroundResource(content.icon)
                        menuTitle.text = content.title
                    }
                }
            }
            binding.root.setOnClickListener {
                listener.invoke()
            }
        }
    }
}