package jp.millennium.ncl.tmobile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import jp.millennium.ncl.tmobile.R
import jp.millennium.ncl.tmobile.databinding.ItemCharityBinding
import jp.millennium.ncl.tmobile.model.Charity

class CharityListAdapter(private val charityList: ArrayList<Charity>) : RecyclerView.Adapter<CharityListAdapter.CharityViewHolder>() {

    fun updateCharityList(newCharityList: List<Charity>) {
        charityList.clear()
        charityList.addAll(newCharityList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCharityBinding>(inflater, R.layout.item_charity, parent, false)
        return CharityViewHolder(view)
    }

    override fun getItemCount(): Int = charityList.size


    override fun onBindViewHolder(holder: CharityViewHolder, position: Int) {
        holder.bindData(charityList[position])
    }

    class CharityViewHolder(var view: ItemCharityBinding) : RecyclerView.ViewHolder(view.root), CharityClickListener {

        fun bindData(item: Charity) {
            view.charity = item
            view.listener = this
        }

        override fun onCharityClicked(v: View) {
            val action = CharityListFragmentDirections.actionCharityDonationFragment()
            Navigation.findNavController(v).navigate(action)
        }
    }

}