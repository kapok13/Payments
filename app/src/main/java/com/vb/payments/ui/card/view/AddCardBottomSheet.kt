package com.vb.payments.ui.card.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vb.payments.R
import com.vb.payments.data.database.card.Card
import com.vb.payments.data.database.card.CardType
import com.vb.payments.databinding.FragmentAddCardBinding
import com.vb.payments.getCardTypeByNumber
import com.vb.payments.isExpirationDateValid
import com.vb.payments.ui.card.presentation.AddCardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class AddCardBottomSheet : BottomSheetDialogFragment() {
    private val binding by lazy { FragmentAddCardBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<AddCardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var isNumberValid = false
        var isCvvValid = false
        var isDateValid = false


        binding.addCardNumberEdit.doAfterTextChanged {
            if (it.toString().filterNot { it.isWhitespace() }.length == 16) {
                getCardTypeByNumber(it.toString().filterNot { it.isWhitespace() }).let { cardType ->
                    if (cardType == CardType.Unknown) {
                        binding.addCardNumberInputContainer.error =
                            context?.getString(R.string.invalid_card_number)
                        isNumberValid = false
                        checkSaveCardEnabled(isNumberValid, isCvvValid, isDateValid)
                    } else {
                        binding.addCardNumberInputContainer.error = null
                        isNumberValid = true
                        checkSaveCardEnabled(isNumberValid, isCvvValid, isDateValid)
                    }
                }
            } else if (it.toString().filterNot { it.isWhitespace() }.length != 16) {
                binding.addCardNumberInputContainer.error = null
                isNumberValid = false
                checkSaveCardEnabled(isNumberValid, isCvvValid, isDateValid)
            }
        }


        binding.addCardCvvEdit.doAfterTextChanged {
            if (it.toString().length < 3) {
                isCvvValid = false
                checkSaveCardEnabled(isNumberValid, isCvvValid, isDateValid)
            } else {
                isCvvValid = true
                checkSaveCardEnabled(isNumberValid, isCvvValid, isDateValid)
            }
        }


        binding.addCardDateEdit.setOnClickListener {
            DatePickerDialog(requireContext()).apply {
                setOnDateSetListener { datePicker, i, i2, i3 ->
                    val expirationDate = Calendar.getInstance()
                    expirationDate[Calendar.YEAR] = i
                    expirationDate[Calendar.MONTH] = i2
                    expirationDate[Calendar.DAY_OF_MONTH] = i3
                    if (isExpirationDateValid(expirationDate)) {
                        isDateValid = true
                        binding.addCardDateInputContainer.error = null
                    } else {
                        isDateValid = false
                        binding.addCardDateInputContainer.error =
                            context.getString(R.string.invalid_expiration_date)
                    }
                    binding.addCardDateEdit.setText(SimpleDateFormat("MM/yy").format(expirationDate.time))
                    checkSaveCardEnabled(isNumberValid, isCvvValid, isDateValid)
                }
                show()
            }
        }

        binding.addCardSave.setOnClickListener {
            viewModel.addCard(
                Card(
                    getCardTypeByNumber(
                        binding.addCardNumberEdit.text.toString()
                            .filterNot { it.isWhitespace() }).name,
                    binding.addCardNumberEdit.text.toString().filterNot { it.isWhitespace() },
                    binding.addCardDateEdit.text.toString(),
                    binding.addCardCvvEdit.text.toString().toInt()
                )
            )
            findNavController().popBackStack()
        }
    }

    private fun checkSaveCardEnabled(
        isNumberValid: Boolean,
        isCvvValid: Boolean,
        isDateValid: Boolean
    ) {
        binding.addCardSave.isEnabled = isNumberValid && isCvvValid && isDateValid
    }
}
