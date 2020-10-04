package jp.millennium.ncl.tmobile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.omise.android.api.Client
import co.omise.android.api.RequestListener
import co.omise.android.models.CardParam
import co.omise.android.models.Token
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import jp.millennium.ncl.tmobile.model.*

class CharityDonationViewModel : ViewModel() {

    private val client = Client("pkey_test_5lfek8i7s4dghroyqh3")

    private val omiseService = OmiseApiService()
    private val disposable = CompositeDisposable()

    val result = MutableLiveData<OmiseResult>()
    val loading = MutableLiveData<Boolean>()

    fun payment(
        cardName: String,
        cardNumber: String,
        expiryMonth: Int,
        expiryYear: Int,
        securityCode: String,
        amount: String
    ) {

        val cardParam = CardParam(
            cardName,
            cardNumber,
            expiryMonth,
            expiryYear,
            securityCode
        )

        createToken(cardName, cardParam, amount)
    }

    private fun createToken(cardName: String, cardParam: CardParam, amount: String) {
        loading.value = true

        val request = Token.CreateTokenRequestBuilder(cardParam).build()
        client.send(request, object : RequestListener<Token> {
            override fun onRequestSucceed(model: Token) {
                createDonation(cardName, model.id ?: "", amount)
            }

            override fun onRequestFailed(throwable: Throwable) {
                result.value = OmiseResult.Ng(throwable.message ?: "")
                loading.value = false
            }
        })
    }

    private fun createDonation(cardName: String, token: String, amount: String) {
        disposable.add(
            omiseService.createDonations(Donation(cardName, token, amount.toInt()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ResOmise>(){
                    override fun onSuccess(resOmise: ResOmise) {
                        result.value = OmiseResult.Ok
                        loading.value = false
                    }

                    override fun onError(throwable: Throwable) {
                        result.value = OmiseResult.Ng(throwable.message ?: "")
                        loading.value = false
                    }
                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
