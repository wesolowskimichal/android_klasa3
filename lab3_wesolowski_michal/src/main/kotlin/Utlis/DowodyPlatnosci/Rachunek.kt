package Utlis.DowodyPlatnosci

import Utlis.Kontrahenci.Firma
import Utlis.Kontrahenci.OsobaFizyczna
import Utlis.Manager
import java.util.Date

class Rachunek(firmaSprzedajaca: Firma, val nabywca: OsobaFizyczna, dataPlatnosci: Date) : DowodPlatnosci(firmaSprzedajaca, dataPlatnosci ) {

    override fun printHeader() {
        val typ = makeWider("RACHUNEK", 31)
        val napis = center(typ, LEN, " ")
        println(WHITE + oddzielnik)
        println(napis)
        println(oddzielnik)
        super.printHeader()
        println(center(dateFormat.format(dataPlatnosci), LEN/2, " ") + center("nr: ${Manager.NumerRachunku}", LEN/2, " "))
        println(oddzielnikFirma)
    }

    override fun printEnd() {
        println(WHITE + oddzielnikFirma)
        println(center(makeWider("NABYWCA", 32), LEN, " "))
        println(center(nabywca.toString(), LEN, " "))
        super.printEnd()
    }

    override fun Drukuj() {
        printHeader()
        printBody()
        printEnd()
    }
}