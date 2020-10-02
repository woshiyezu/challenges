package jp.millennium.ncl.tmobile.model

import io.reactivex.Single
import retrofit2.http.GET

interface OmiseApi {

    @GET("charities")
    fun getCharities(): Single<List<Charity>>
}