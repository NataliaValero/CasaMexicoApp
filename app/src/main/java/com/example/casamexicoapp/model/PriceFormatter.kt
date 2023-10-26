package com.example.casamexicoapp.model

import java.math.BigDecimal
import java.math.RoundingMode

class PriceFormatter {
    companion object {

        fun getCurrencyTotal(amount: Double):String {
            return "$${getBigDecimalTotal(amount)}"
        }

        private fun getBigDecimalTotal(amount: Double) : BigDecimal {
            return (BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN))
        }

    }
}