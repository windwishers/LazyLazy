package fail.toepic.lazylazy.sample.color

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fail.toepic.lazylazy.sample.R
import fail.toepic.lazylazy.sample.color.model.Color
import fail.toepic.lazylazy.wrapper.Wrapper



data class Item(val isVisible : Boolean, override val value : Color) : Wrapper<Color>

fun View.inflate(@LayoutRes layoutRes : Int): View = LayoutInflater.from(this.context).inflate(layoutRes, null, false)

class Holder(v : View) : RecyclerView.ViewHolder(v.inflate(R.layout.item_color)) {
    @SuppressLint("SetTextI18n")
    fun binding(item: Item?) {
        item ?: return

        background.setBackgroundResource(item.value.colorResId)
        label.text = makeLabel(item)
    }

    private fun makeLabel(item: Item): CharSequence? {

        return   "${item.value.hex} \n" + item.value.names + " \n  category  \n " + item.value.categories
    }

    private val background = itemView.findViewById<View>(R.id.background)
    private val label = itemView.findViewById<TextView>(R.id.label)
}

val differ = Wrapper.DiffCallback<Item>()


class Listadapter : ListAdapter<Item, Holder>(differ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(parent)

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.binding(getItem(position))
    }
}



