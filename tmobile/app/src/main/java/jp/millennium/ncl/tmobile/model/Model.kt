package jp.millennium.ncl.tmobile.model

import com.google.gson.annotations.SerializedName

data class Charity(

    @SerializedName("id")
    val charityId: String,

    @SerializedName("name")
    val charityName: String,

    @SerializedName("logo_url")
    val logoUrl: String
)