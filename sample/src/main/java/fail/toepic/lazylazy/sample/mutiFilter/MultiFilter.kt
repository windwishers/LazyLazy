package fail.toepic.lazylazy.sample.mutiFilter

import android.util.Log
import android.widget.CompoundButton

class MultiFilter<T>{
    private val items : MutableList<MultiFilterItem<T>> = mutableListOf()

    private val onChangeCallback : ((Int,CompoundButton,Boolean)->Unit)? = { id ,_ , isChecked ->
        Log.d("testiest","onChangedCallback")
        if (!isChecked) {
            if (items.filter { it.toogle.isChecked }.count() == 0) {  // 마지막 하나의 false 이면 true 로 바꿈.
                val item = items.first { it.id == id }
                item.isEnable = true
            }
        }
    }

    fun addFilter(item : MultiFilterItem<T>) : Int{
        synchronized(this) {
            items.add(item)

            item.onCheckChanged = onChangeCallback
        }
        return items.size
    }

    @Suppress("unused")
    fun removeFilter(item : MultiFilterItem<T>) : Int{

        synchronized(this){
            val target = items.find { it.id == item.id }
            items.remove(target)
        }
        return items.size
    }

    fun hasEnabled() : Boolean{
        synchronized(this){

            return items.any { it.isEnable }
        }

    }

    fun doFiltered(item: T): Boolean {
        synchronized(this){

            return items.filter { it.isEnable }.map {
                it.tryFilter(item)
            }.none { !it }
        }


    }


}