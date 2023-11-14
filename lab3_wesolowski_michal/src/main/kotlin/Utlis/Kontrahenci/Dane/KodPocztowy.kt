package Utlis.Kontrahenci.Dane

import java.lang.StringBuilder

class KodPocztowy(private var _kodPocztowy: String) {
    private var _isGood = false
    val isGood: Boolean
        get() = _isGood
    val kodPocztowy: String
        get() = _kodPocztowy
    init {
        val contains = kodPocztowy.contains("-")
        when(kodPocztowy.length) {
            5 -> {
                _isGood = !contains
            }
            6 -> {
                _isGood = contains
            }
            else -> {}
        }
        if(isGood && !contains) {
            val str = StringBuilder(kodPocztowy)
            str.insert(2, "-")
            _kodPocztowy = str.toString()
        }
    }
    override fun toString(): String {
        return if (isGood) kodPocztowy else "Niepoprawny kod pocztowy"
    }
}