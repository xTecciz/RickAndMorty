package com.teckzi.rickandmorty.data.local

import androidx.room.TypeConverter

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertIntListToString(list: List<Int>): String {
        return list.joinToString(separator)
    }

    @TypeConverter
    fun convertStringToIntList(string: String): List<Int>? {
        val list: MutableList<Int>?
        if (string == "") list = null
        else {
            list = mutableListOf()
            string.split(separator).let { listOfString ->
                listOfString.forEach {
                    list.add(it.toInt())
                }
            }
        }
        return list
    }
}