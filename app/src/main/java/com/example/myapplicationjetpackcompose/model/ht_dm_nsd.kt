package com.example.myapplicationjetpackcompose.model

import androidx.constraintlayout.widget.Guideline
import com.google.type.DateTime
import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class ht_dm_nsd (

    var ma_nsd: String? = "",
    var matkhau: String? = "",


)
@Serializable
data class  response_ht_dm_nsd (
    var data: List<ht_dm_nsd>
): response_status()



