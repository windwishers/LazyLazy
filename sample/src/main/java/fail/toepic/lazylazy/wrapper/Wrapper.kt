// TODO 잘 동작하고 쓸만하다고 판단 되면 LazyLazy 로 옴길것.
/**
 *
 */
package fail.toepic.lazylazy.wrapper

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

interface Wrapper<T> {
    val value : T


    class  DiffCallback<T : Wrapper<*>> : DiffUtil.ItemCallback<T>() {
//        override fun areItemsTheSame(old: Wrapper<T>, new: Wrapper<T>): Boolean = old.value == new.value
//        @SuppressLint("DiffUtilEquals")
//        override fun areContentsTheSame(old: Wrapper<T>, new: Wrapper<T>): Boolean = old == new

        override fun areItemsTheSame(old: T, new: T): Boolean = old.value == new.value
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(old: T, new: T): Boolean = old == new
    }

}