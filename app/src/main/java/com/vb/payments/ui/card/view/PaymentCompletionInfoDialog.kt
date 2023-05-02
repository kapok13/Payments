package com.vb.payments.ui.card.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.vb.payments.R
import com.vb.payments.databinding.FragmentPaymentCompletionBinding


class PaymentCompletionInfoDialog : DialogFragment() {

    private val binding by lazy { FragmentPaymentCompletionBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.attributes?.windowAnimations = R.style.CompletionDialogStyle
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().popBackStack()
        }, 4000)
    }
}
