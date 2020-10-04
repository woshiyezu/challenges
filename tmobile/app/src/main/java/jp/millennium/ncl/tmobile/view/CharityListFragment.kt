package jp.millennium.ncl.tmobile.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.millennium.ncl.tmobile.R
import jp.millennium.ncl.tmobile.viewmodel.CharityListViewModel
import kotlinx.android.synthetic.main.charity_list_fragment.*

class CharityListFragment : Fragment() {

    private lateinit var viewModel: CharityListViewModel
    private val charityListAdapter = CharityListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.charity_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharityListViewModel::class.java)
        viewModel.fetchCharitiesFromRemote()

        charityList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charityListAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(context).orientation))
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.charities.observe(this, Observer { charities ->
            charities?.let{
                charityList.visibility = View.VISIBLE
                charityListAdapter.updateCharityList(it)
            }
        })

        viewModel.charitiesLoadError.observe(this, Observer { isError ->
            isError?.let {
                when(it) {
                    true -> listError.visibility = View.VISIBLE
                    false -> listError.visibility = View.GONE
                }
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading.let {
                when(it) {
                    true ->  {
                        loadingView.visibility = View.VISIBLE
                        listError.visibility = View.GONE
                        charityList.visibility = View.GONE
                    }
                    false -> { loadingView.visibility = View.GONE }
                }
            }
        })
    }

}
