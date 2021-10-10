package com.example.foody.features.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.foody.R
import com.example.foody.databinding.FoodDetailsLayoutBinding
import com.example.foody.features.MainActivityViewModel
import com.example.foody.utils.ResourceStates
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class FoodDetailsDialogFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    private var _binding: FoodDetailsLayoutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FoodDetailsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }



    companion object {
        const val TAG = "FoodDetailsDialogFragment"

        fun add(context: FragmentActivity, resId: Int){
            context.supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in, android.R.anim.slide_out_right, R.anim.slide_in, android.R.anim.slide_out_right)
                .replace(resId, FoodDetailsDialogFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainActivityViewModel.foodDetailLiveData.observe(viewLifecycleOwner){
            _binding?.run {
                Glide.with(requireActivity()).load(it.imageUrl).into(foodIv)
                foodMiscTv.text = getString(R.string.misc, it.from, it.nutrients)
                productNameTv.text = it.productName
                descriptionTv.text = it.description
                priceTv.text = getString(R.string.naira_amount, it.price)
                quantityTv.text = getString(R.string.title_quantity, it.quantity)
            }
        }
    }
}