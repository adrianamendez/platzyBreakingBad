package com.example.basicworktest.denise.data.utils

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicworktest.denise.data.utils.Extensions.REGEX_ONLY_NUMBERS
import com.example.basicworktest.denise.data.utils.NumberConstants.ZERO_DOUBLE
import com.example.basicworktest.denise.data.utils.NumberConstants.ZERO_LONG
import com.example.basicworktest.denise.data.utils.NumberConstants.ZERO_NUMB
import com.example.basicworktest.denise.domain.utils.EMPTY_STRING

object Extensions {
    const val REGEX_ONLY_NUMBERS = "[^0-9]"
}

fun String?.value(default: String = EMPTY_STRING): String = this ?: default
fun Int?.value(default: Int = ZERO_NUMB): Int = this ?: default
fun Double?.value(default: Double = ZERO_DOUBLE): Double = this ?: default
fun Long?.value(default: Long = ZERO_LONG): Long = this ?: default
fun Boolean?.value(default: Boolean = false): Boolean = this ?: default

fun String.getOnlyNumbers() = replace(REGEX_ONLY_NUMBERS.toRegex(), EMPTY_STRING)

fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {

    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(oldList[oldItemPosition], newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
    })
    diff.dispatchUpdatesTo(this)
}