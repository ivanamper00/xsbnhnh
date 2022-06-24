package winwin.alwn.xsbnhnh.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import winwin.alwn.xsbnhnh.R
import winwin.alwn.xsbnhnh.data.DataModel
import winwin.alwn.xsbnhnh.databinding.CustomBottomNavBinding
import winwin.alwn.xsbnhnh.databinding.ItemMenuLeftBinding
import winwin.alwn.xsbnhnh.databinding.ItemMenuRightBinding

class BottomNavAdapter(
    private val list: List<DataModel>,
    private val listener: Listener
): RecyclerView.Adapter<BottomNavAdapter.Holder>() {

    private var selectedId = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CustomBottomNavBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding){
            val predicate = selectedId == list[position].icon
            val color = if(predicate) R.color.white else R.color.yellow
            buttonMenu.setColorFilter(ContextCompat.getColor(root.context, color))
            buttonMenu.setImageDrawable(ContextCompat.getDrawable(root.context, list[position].icon))

            root.setOnClickListener {
                listener.onMenuClick(position + 1)
                selectedId = list[position].icon
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class Holder(val binding: CustomBottomNavBinding): RecyclerView.ViewHolder(binding.root)

    interface Listener{
        fun onMenuClick(position: Int)
    }
}