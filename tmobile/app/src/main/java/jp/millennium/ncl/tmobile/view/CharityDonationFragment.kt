package jp.millennium.ncl.tmobile.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import jp.millennium.ncl.tmobile.R
import kotlinx.android.synthetic.main.charity_donation_fragment.*

class CharityDonationFragment : Fragment() {

    companion object {
        fun newInstance() = CharityDonationFragment()
    }

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
        // TODO: Use the ViewModel

        submit.setOnClickListener {
            val action = CharityDonationFragmentDirections.actionSuccessFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
