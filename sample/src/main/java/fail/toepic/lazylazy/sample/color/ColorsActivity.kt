package fail.toepic.lazylazy.sample.color

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fail.toepic.lazylazy.sample.R
import fail.toepic.lazylazy.sample.color.data.ColorData
import fail.toepic.lazylazy.sample.color.model.Color
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : AppCompatActivity() {

    private val adapter =   Listadapter()

    var data = listOf(Item(true,Color("",R.color.red)))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)

        list.layoutManager = GridLayoutManager(this,3,RecyclerView.VERTICAL,false)
        list.adapter = adapter

//        adapter.submitList(data)

        button.setOnClickListener {
            tryFilter()
        }

        reload.setOnClickListener {
            reload()
        }

        reload()

    }

    private fun reload() {
        data = ColorData.loadData(this).map {
            Item(true, it)
        }

        adapter.submitList(data)
    }

    private fun tryFilter(){
        adapter.submitList(
            data.map {
                Item( doFilter(it.value),it.value)
            }.filter { it.isVisible  }.toList()
        )
    }

    private fun doFilter(color: Color): Boolean {

        val r = filters.asSequence().map {
            it.invoke(color)
        }.filter { it }.count()
        return r > 0
    }

    private val nameFilter : (Color) -> Boolean =  { it->
        it.names.joinToString("").contains(name.text,true) && name.text.isNotBlank()
    }

    val filters: List<(Color) -> Boolean> =  listOf(
        nameFilter
    )
}
