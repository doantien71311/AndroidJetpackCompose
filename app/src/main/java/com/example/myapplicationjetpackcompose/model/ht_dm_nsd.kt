package com.example.myapplicationjetpackcompose.model

import androidx.constraintlayout.widget.Guideline
import com.google.type.DateTime
import java.util.UUID

data class ht_dm_nsd (

    var ma_nsd: String? = "",
    var matkhau: String? = "",

    var asd: DateTime? = null,
    var Deasd: Boolean? = null,

)

data class  response_ht_dm_nsd (
    var data: List<ht_dm_nsd>
): response_status()



