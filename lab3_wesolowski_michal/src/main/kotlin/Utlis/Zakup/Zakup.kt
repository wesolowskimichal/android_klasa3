package Utlis.Zakup

import Utlis.Manager

data class Zakup(val produkt: Produkt, val ilosc: UInt, val rabat: Manager.Companion.Rabat = Manager.Companion.Rabat.TYP0) {

    init {
        if (rabat != Manager.Companion.Rabat.TYP0) {
            produkt.cenaBrutto -= (produkt.cenaBrutto * rabat.wartosc)
        }
    }

    override fun toString(): String {
        val right = buildString {
            append(ilosc.toString())
            append(" * ")
            append(String.format("%.2f", produkt.cenaBrutto))
            append(" = ")
            append(String.format("%.2f", ilosc.toDouble() * produkt.cenaBrutto))
            append(" ")
            append(produkt.stawkaVat.name)
        }
        val diff = 50 - right.length
        val leftLen = if(diff > 0) {
            diff
        } else {
            4
        }
        val left = if(produkt.nazwa.length > leftLen) {
            produkt.nazwa.substring(leftLen - 3) + "..."
        } else {
            produkt.nazwa
        }
        val spaceLen = 50 - right.length - left.length
        val space = " ".repeat(spaceLen)

        return buildString {
            append(left)
            append(space)
            append(right)
        }
    }
}