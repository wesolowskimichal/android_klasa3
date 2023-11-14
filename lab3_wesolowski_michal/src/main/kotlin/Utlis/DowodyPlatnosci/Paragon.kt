package Utlis.DowodyPlatnosci

import Utlis.Kontrahenci.Firma
import Utlis.Manager
import java.util.Date

class Paragon(firmaSprzedajaca: Firma, dataPlatnosci: Date) : DowodPlatnosci(firmaSprzedajaca, dataPlatnosci ) {

    override fun printHeader() {
        val typ = makeWider("PARAGON FISKALNY", 31)
        val napis = center(typ, LEN, " ")
        println(WHITE + oddzielnik)
        println(napis)
        println(oddzielnik)
        super.printHeader()
        println(center(dateFormat.format(dataPlatnosci), LEN/2, " ") + center("nr: ${Manager.NumerParagonu}", LEN/2, " "))
        println(oddzielnikFirma)
    }

    override fun Drukuj() {
        printHeader()
        printBody()
        printEnd()
    }
}