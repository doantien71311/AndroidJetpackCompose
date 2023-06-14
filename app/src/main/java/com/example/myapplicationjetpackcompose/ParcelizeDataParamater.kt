package com.example.myapplicationjetpackcompose

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object EnumParcelizeDataParamater {

   const val ParcelizeData : String = "ParcelizeData"

}

@Parcelize
class ParcelizeDataParamater (

   val ma_chucnang: String = "",
   val tungay: String = "",
   val denngay: String = "",
   val m_title: String = "",
   val m_text: String = "",
   val key_id: String = "",
   val array_id: Array<String> =  arrayOf()

        ): Parcelable