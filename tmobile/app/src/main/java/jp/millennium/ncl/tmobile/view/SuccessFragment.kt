package jp.millennium.ncl.tmobile.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import jp.millennium.ncl.tmobile.R
import kotlinx.android.synthetic.main.success_fragment.*

class SuccessFragment : Fragment() {

    companion object {
        fun newInstance() = SuccessFragment()
    }

    private lateinit var viewModel: SuccessViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.success_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SuccessViewModel::class.java)
        // TODO: Use the ViewModel

        button2.setOnClickListener {
            val action = SuccessFragmentDirections.actionCharityListFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
