package br.com.alura.globalsolution_10

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    var userFirstName: String
) : Parcelable
