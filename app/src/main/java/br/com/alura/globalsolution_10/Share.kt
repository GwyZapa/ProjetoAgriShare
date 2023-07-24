package br.com.alura.globalsolution_10

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Share(
    val productName: String,
    val qtdProduct: String
//    val imagePicker: Uri
) : Parcelable
