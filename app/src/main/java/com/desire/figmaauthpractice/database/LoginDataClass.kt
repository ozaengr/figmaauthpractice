package com.desire.figmaauthpractice.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "login_table")
data class LoginDataClass(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String = "",
    var emailId: String = "",
    var password: String = ""
) : Parcelable
