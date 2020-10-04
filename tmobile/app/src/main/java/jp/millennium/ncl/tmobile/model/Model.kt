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

data class Donation(

    val name: String,
    val token: String,
    val amount: Int
)

data class ResOmise(

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("error_code")
    val errorCode: String,

    @SerializedName("error_message")
    val errorMessage: String
)
