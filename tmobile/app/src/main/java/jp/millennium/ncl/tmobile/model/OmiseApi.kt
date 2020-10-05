package jp.millennium.ncl.tmobile.model

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface OmiseApi {

    @GET("charities")
    fun getCharities(): Single<List<Charity>>

    @Headers("Content-Type: application/json")
    @POST("donations")
    fun createDonations(@Body donation: Donation): Single<ResposeOmise>
}