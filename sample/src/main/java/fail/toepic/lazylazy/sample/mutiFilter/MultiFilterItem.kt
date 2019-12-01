package fail.toepic.lazylazy.sample.mutiFilter

import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.linear_label_text.view.*

class MultiFilterItem<T>(root: View, label : String? ="", isChecked : Boolean = false, private var filter : ((MultiFilterItem<T>, T)->Boolean)? = null) {

    val id = root.id
    val toogle: ToggleButton = root.label_button
    val text = root.text!!
    var onCheckChanged : ((Int,CompoundButton,Boolean) -> Unit)? = null
    var isEnable: Boolean
        get() = text.isEnabled
        set(value) {
            toogle.isChecked = value
            text.isEnabled = value
        }

    val value: String
        get() = text.text.toString()

    init {

        label.let {
            toogle.text = it
            toogle.textOff = it
            toogle.textOn = it

        }

        if (isChecked) {
            text.isEnabled = true
        } else {
            text.text = null
            text.isEnabled = false
        }

        if (isChecked) {
            toogle.isChecked = isChecked
        }


        toogle.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("Testiest","  ${buttonView.id} $isChecked ")
            when{
                isChecked && !text.isEnabled -> {
                    text.isEnabled = true
                    onCheckChanged?.invoke(id,buttonView,isChecked)
                }
                isChecked && text.isEnabled -> { Unit }
                !isChecked && !text.isEnabled -> { Unit }
                !isChecked && text.isEnabled -> {
                    text.isEnabled = false
                    onCheckChanged?.invoke(id,buttonView,isChecked)
                }
            }
        }
    }

    fun tryFilter(item: T): Boolean {
        return filter?.invoke(this,item) ?: false
    }

}