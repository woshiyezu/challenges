package jp.millennium.ncl.tmobile.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import jp.millennium.ncl.tmobile.R
import jp.millennium.ncl.tmobile.model.OmiseResult
import jp.millennium.ncl.tmobile.viewmodel.CharityDonationViewModel
import kotlinx.android.synthetic.main.charity_donation_fragment.*

class CharityDonationFragment : Fragment() {

    private lateinit var viewModel: CharityDonationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.charity_donation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharityDonationViewModel::class.java)

        submit.setOnClickListener {
            viewModel.payment(
                cardName.cardName,
                cardNumber.cardNumber,
                expiryDate.expiryMonth,
                expiryDate.expiryYear,
                securityCode.securityCode,
                amount.text.toString()
            )
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.result.observe(this, Observer { result ->
            result?.let{
                when(it) {
                    is OmiseResult.Ok -> {
                        val action = CharityDonationFragmentDirections.actionSuccessFragment()
                        Navigation.findNavController(view!!).navigate(action)
                    }
                    is OmiseResult.Ng -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading.let {
                when(it) {
                    true ->  {
                        loadingView.visibility = View.VISIBLE
                    }
                    false -> { loadingView.visibility = View.GONE }
                }
            }
        })
    }
}
