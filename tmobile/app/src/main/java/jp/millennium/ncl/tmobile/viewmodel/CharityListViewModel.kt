package jp.millennium.ncl.tmobile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import jp.millennium.ncl.tmobile.model.Charity
import jp.millennium.ncl.tmobile.model.OmiseApiService

class CharityListViewModel : ViewModel() {

    private val omiseService = OmiseApiService()
    private val disposable = CompositeDisposable()

    val charities = MutableLiveData<List<Charity>>()
    val charitiesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun fetchCharitiesFromRemote() {
        loading.value = true
        disposable.add(
            omiseService.getCharities()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Charity>>(){
                    override fun onSuccess(charityList: List<Charity>) {
                        charitiesRetrieved(charityList)
                    }

                    override fun onError(e: Throwable) {
                        charitiesLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                }))
    }

    private fun charitiesRetrieved(charityList:List<Charity>){
        charities.value = charityList
        loading.value = false
        charitiesLoadError.value  = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
