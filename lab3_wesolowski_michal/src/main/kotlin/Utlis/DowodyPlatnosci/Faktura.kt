package Utlis.DowodyPlatnosci

import Utlis.Kontrahenci.Firma
import Utlis.Manager
import java.util.Date

class Faktura(firmaSprzedajaca: Firma, val nabywca: Firma, dataPlatnosci: Date) : DowodPlatnosci(firmaSprzedajaca, dataPlatnosci ) {

    override fun printHeader() {
        val typ = makeWider("FAKTURA", 31)
        val napis = center(typ, LEN, " ")

        println(WHITE + oddzielnik)
        println(napis)
        println(oddzielnik)
        super.printHeader()
        println(center(dateFormat.format(dataPlatnosci), LEN/2, " ") + center("nr: ${Manager.NumerFaktury}", LEN/2, " "))
        println(oddzielnikFirma)
    }

    override fun printBody() {
        for(zakup in listaZakupow) {
            println(zakup)
            val str_cenaNetto = String.format("%.2f", zakup.produkt.cenaNetto * zakup.ilosc.toDouble())
            println(" ".repeat(LEN - str_cenaNetto.length - 2) + str_cenaNetto)
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
        val str_sumaNetto = String.format("%.2f", sumaNetto)
        println("SUMA NETTO" + " ".repeat(LEN - str_sumaNetto.length - 10) + str_sumaNetto)
        val str_sumaPLN = String.format("%.2f", sumaBrutto)
        println( WHITE_BOLD_BRIGHT + "SUMA PLN"  + " ".repeat(LEN - str_sumaPLN.length - 8) + str_sumaPLN)
        println(WHITE + oddzielnikFirma)
    }

    override fun printEnd() {
        println(center(makeWider("NABYWCA", 32), LEN, " "))
        printFirma(nabywca)
        super.printEnd()
    }

    override fun Drukuj() {
        printHeader()
        printBody()
        printEnd()
    }
}