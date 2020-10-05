package jp.millennium.ncl.tmobile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import jp.millennium.ncl.tmobile.R
import kotlinx.android.synthetic.main.success_fragment.*

/**
 * Success screen
 */
class SuccessFragment : Fragment() {

    private var amount: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.success_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            amount = SuccessFragmentArgs.fromBundle(it).amount
        }
        paymentTitle.text = "THB $amount"

        goHome.setOnClickListener {
            val action = SuccessFragmentDirections.actionCharityListFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
