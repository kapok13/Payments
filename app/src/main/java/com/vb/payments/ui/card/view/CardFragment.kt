package com.vb.payments.ui.card.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vb.payments.R
import com.vb.payments.databinding.FragmentCardBinding
import com.vb.payments.ui.card.presentation.CardViewModel
import com.vb.payments.ui.card.widget.CardAdapter
import com.vb.payments.ui.main.view.ProgressProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {
    private val binding by lazy { FragmentCardBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<CardViewModel>()
    private val progress by lazy { (activity as ProgressProvider).progress }
    private val adapter = CardAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.getCards().collect {
                adapter.submitList(it)
            }
        }
        setupRecyclerView()

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedCard.collect {
                binding.cardPurchase.isEnabled = it != null
            }
        }
        binding.cardAddNewCard.setOnClickListener {
            findNavController().navigate(R.id.add_card_bottom_sheet)
        }

        binding.cardPurchase.setOnClickListener {
            progress.setProgress(true)

            //Imitate background work
            Handler(Looper.getMainLooper()).postDelayed({
                progress.setProgress(false)
                findNavController().navigate(R.id.payment_completion_info_dialog)
            }, 5000)
        }
    }

    private fun setupRecyclerView() {
        adapter.onCardClickListener = {
            binding.cardCardsRecycler.children.forEach { it.isSelected = false }
            viewModel.selectedCard.value = it
        }
        binding.cardCardsRecycler.adapter = adapter
        viewModel.selectedCard.value?.let {
            binding.cardCardsRecycler.getChildAt(adapter.currentList.indexOf(viewModel.selectedCard.value)).isSelected =
                true
        }
    }
}
