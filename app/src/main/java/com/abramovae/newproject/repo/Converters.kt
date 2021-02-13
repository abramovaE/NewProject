package com.abramovae.newproject.repo

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }
    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",")
    }


        @TypeConverter
        fun gettingListFromString(genreIds: String): List<Int> {
            val list = ArrayList<Int>()
            val array = genreIds.split(",")
            for (s in array) {
                if (!s.isEmpty()) {
                    list.add(s.toInt())
                }
            }
            return list
        }

        @TypeConverter
        fun writingStringFromList(list: List<Int>): String {
            var genreIds = ""
            for (i in list) {
                genreIds += ",$i"
            }
            return genreIds
        }
}