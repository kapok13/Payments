package com.vb.payments

import com.vb.payments.data.database.card.CardType
import java.util.Calendar

fun getCardTypeByNumber(number: CharSequence) = when {
    number.startsWith("4") -> CardType.Visa
    number.startsWith("51")
            || number.startsWith("52")
            || number.startsWith("53")
            || number.startsWith("54")
            || number.startsWith("55") -> CardType.MasterCard
    else -> CardType.Unknown
}

fun isExpirationDateValid(date: Calendar) = Calendar.getInstance().before(date)
