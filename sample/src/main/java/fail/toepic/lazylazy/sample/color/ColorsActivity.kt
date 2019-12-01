package fail.toepic.lazylazy.sample.color

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fail.toepic.lazylazy.sample.R
import fail.toepic.lazylazy.sample.color.data.ColorData
import fail.toepic.lazylazy.sample.color.model.Color
import fail.toepic.lazylazy.sample.mutiFilter.MultiFilter
import fail.toepic.lazylazy.sample.mutiFilter.MultiFilterItem
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : AppCompatActivity() {

    private val adapter =   Listadapter()

    private var data = listOf(Item(true,Color("",R.color.red)))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)

        initLayout()

        button.setOnClickListener {
            tryFilter()
        }

        reload.setOnClickListener {
            reload()
        }

        reload()

    }

    private val multiFilter : MultiFilter<Item> = MultiFilter()

    private fun initLayout() {
        list.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        list.adapter = adapter



        multiFilter.addFilter(MultiFilterItem(hex_filter,"HEXCODE"){ filter, item ->
        true
        })
        multiFilter.addFilter(MultiFilterItem(name_filter,"NAME"){ filter, item ->
            val text = filter.value
            if(text.isEmpty())
                false
            else
                item.value.names.joinToString().contains(text,true)
        })
    }

    private fun reload() {
        data = ColorData.loadData(this).map {
            Item(true, it)
        }

        adapter.submitList(data)
    }

    private fun tryFilter(){

        if (!multiFilter.hasEnabled()) {
            return
        }
        adapter.submitList(
            data.map {
                val filtered = multiFilter.doFiltered(it)
                Item( filtered,it.value)
            }.filter { it.isVisible  }.toList()
        )
    }
}
