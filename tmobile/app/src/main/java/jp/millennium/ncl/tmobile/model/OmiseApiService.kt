package jp.millennium.ncl.tmobile.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OmiseApiService {

    private val BASE_URL = "http://10.0.2.2:8080"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(OmiseApi::class.java)

    fun getCharities(): Single<List<Charity>> {
        return api.getCharities()
    }

    fun createDonations(donation: Donation): Single<ResOmise> {
        return api.createDonations(donation)
    }

}