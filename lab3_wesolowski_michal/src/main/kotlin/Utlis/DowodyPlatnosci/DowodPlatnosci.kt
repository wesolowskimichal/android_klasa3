package Utlis.DowodyPlatnosci

import Utlis.Kontrahenci.Firma
import Utlis.Zakup.StawkaVat
import Utlis.Zakup.Zakup
import java.text.SimpleDateFormat
import java.util.*

abstract class DowodPlatnosci(protected val firma: Firma, protected val dataPlatnosci: Date) {
//<editor-fold desc="Elementy Dekoracyjne">
    val WHITE = "\u001b[0;97m"
    val WHITE_BOLD_BRIGHT = "\u001b[1;97m"

    protected val LEN = 50
    protected val oddzielnik = "-".repeat(LEN)
    protected val oddzielnikFirma = "_".repeat(LEN)
//</editor-fold>

    protected val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    protected val listaZakupow = mutableListOf<Zakup>()
    protected var sumaBrutto = 0.0
    protected val sumyPTU = EnumMap<StawkaVat, Double>(StawkaVat::class.java)
    protected val sumaPTU: Double

        get() {
            var result = 0.0
            for(sum in sumyPTU.values) {
                result += sum
            }
            return result
        }
    protected val sumaNetto: Double

        get() {
            var result = sumaBrutto
            for(sum in sumyPTU.values) {
                result -= sum
            }
            return result
        }

    init {
        sumyPTU[StawkaVat.A] = 0.0
        sumyPTU[StawkaVat.B] = 0.0
        sumyPTU[StawkaVat.C] = 0.0
        sumyPTU[StawkaVat.D] = 0.0
    }

    abstract fun Drukuj()

//<editor-fold desc="Zarzadzanie zakupami">
    fun DodajZakup(zakup: Zakup) {
        val wartosc = zakup.produkt.Netto * zakup.ilosc.toDouble()
        sumyPTU[zakup.produkt.stawkaVat] = sumyPTU[zakup.produkt.stawkaVat]!! + wartosc
        sumaBrutto += zakup.produkt.cenaBrutto * zakup.ilosc.toDouble()
        listaZakupow.add(zakup)
    }

    fun UsunOstatniZakup(): Boolean {
        val ostatniZakup = listaZakupow.lastOrNull()
        if (ostatniZakup != null) {
            val wartosc = ostatniZakup.produkt.Netto * ostatniZakup.ilosc.toDouble()
            sumyPTU[ostatniZakup.produkt.stawkaVat] = sumyPTU[ostatniZakup.produkt.stawkaVat]!! - wartosc
            sumaBrutto -= ostatniZakup.produkt.cenaBrutto * ostatniZakup.ilosc.toDouble()
            listaZakupow.removeLast()
            return true
        }
        return false
    }

    fun UsunZakup(zakup: Zakup): Boolean {
        if (listaZakupow.contains(zakup)) {
            val wartosc = zakup.produkt.Netto * zakup.ilosc.toDouble()
            sumyPTU[zakup.produkt.stawkaVat] = sumyPTU[zakup.produkt.stawkaVat]!! - wartosc
            sumaBrutto -= zakup.produkt.cenaBrutto * zakup.ilosc.toDouble()
            listaZakupow.remove(zakup)
            return true
        }
        return false
    }
//</editor-fold>
//<editor-fold desc="Funkcje Tekstowe">
    protected fun center(text: String, len: Int, fill: String): String {
        if(text.length > len) {
            return text.substring(0, len-2) + ". "
        }
        val spaceBefore = (len - text.length) / 2
        val spaceAfter = len - text.length - spaceBefore
        return fill.repeat(spaceBefore) + text + fill.repeat(spaceAfter)
    }

    protected fun makeWider(text: String, toLen: Int): String {
        if (text.length >= toLen) {
            return text.substring(0, toLen)
        }

        if(toLen / text.length <= 0) {
            return text
        }

        if (text.isEmpty()) {
            return text
        }

        val spacesToAdd = toLen - text.length
        val spacesBetween = spacesToAdd / (text.length - 1)
        var extraSpaces = spacesToAdd % (text.length - 1)

        val str = StringBuilder(text)
        var index = 1

        for (i in 0 until text.length - 1) {
            str.insert(index, " ".repeat(spacesBetween))
            index += spacesBetween + 1

            if (extraSpaces > 0) {
                str.insert(index, " ")
                index++
                extraSpaces--
            }
        }

        return str.toString()
    }

    protected fun printFirma(firma: Firma) {
        println(center(firma.nazwa, LEN, " "))
        println(center("${firma.adres.kodPocztowy} ${firma.adres.miasto}", LEN/2, " ") + center("ul.${firma.adres.ulica} ${firma.adres.numer}", LEN/2, " "))
        println(center("nip: ${firma.nip}", LEN/2, " ") + center("telefon: ${firma.telefon}", LEN/2, " "))
    }
//</editor-fold>
//<editor-fold desc="Uklad Dokumentu">
    protected open fun printHeader() {
        printFirma(firma)
    }

    protected open fun printBody() {
        for(zakup in listaZakupow) {
            println(zakup)
        }
        println()
        for (key in sumyPTU.keys) {
            if(sumyPTU[key] == 0.0) {
                continue
            }
            val sL = "PTU $key ${(key.wartosc * 100.0)}%"
            val sR = String.format("%.2f", sumyPTU[key])
            val mS = " ".repeat(LEN - sL.length - sR.length)
            println(sL + mS + sR)
        }
        val str_sumaPTU = String.format("%.2f", sumaPTU)
        println("SUMA PTU" + " ".repeat(LEN - str_sumaPTU.length - 8) + str_sumaPTU)
        val str_sumaPLN = String.format("%.2f", sumaBrutto)
        println( WHITE_BOLD_BRIGHT + "SUMA PLN"  + " ".repeat(LEN - str_sumaPLN.length - 8) + str_sumaPLN + WHITE)
    }

    protected  open fun printEnd() {
        println(WHITE + oddzielnik)
    }
//</editor-fold>
}