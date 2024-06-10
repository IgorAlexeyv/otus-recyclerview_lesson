package ru.prike.otus_recyclerview_lesson

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class PersonItem(
    val id: Int,
    val name: String,
    val date: String,
    val message: String,
    @DrawableRes val image: Int,
    @ColorRes val background: Int
) : Item

data class DayItem(
    val id: Int,
    val name: String
) : Item