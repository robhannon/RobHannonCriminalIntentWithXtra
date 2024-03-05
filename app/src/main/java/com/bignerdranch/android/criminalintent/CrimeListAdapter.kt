package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {

        if (crime.requiresPolice) {
            binding.contactPoliceButton.visibility = View.VISIBLE
            binding.contactPoliceButton.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Call 911",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            binding.contactPoliceButton.visibility = View.GONE
        }

        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>,
    private val showSerious: Boolean
) : RecyclerView.Adapter<CrimeHolder>() {

    companion object {
        private const val TYPE_REGULAR = 0
        private const val TYPE_SERIOUS = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) TYPE_REGULAR else TYPE_SERIOUS
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        if ((getItemViewType(position) == TYPE_REGULAR) && (showSerious == true)) {
            holder.bind(crime)
        } else if ((getItemViewType(position) == TYPE_SERIOUS) && (showSerious == false)){
            holder.bind(crime)
        }
    }

    override fun getItemCount() = crimes.size
}
