import Utlis.DowodyPlatnosci.DowodPlatnosci
import Utlis.DowodyPlatnosci.Faktura
import Utlis.DowodyPlatnosci.Paragon
import Utlis.DowodyPlatnosci.Rachunek
import Utlis.Kontrahenci.Dane.Adres
import Utlis.Kontrahenci.Firma
import Utlis.Kontrahenci.Dane.KodPocztowy
import Utlis.Kontrahenci.OsobaFizyczna
import Utlis.Manager
import Utlis.Zakup.Produkt
import Utlis.Zakup.Zakup
import Utlis.Zakup.StawkaVat
import java.util.*

fun main(args: Array<String>) {
    val firmy = listOf<Firma>(
        Firma("Zabka", "9690237080", Adres("Polska", "Slask", KodPocztowy("44-100"), "Gliwice", "Zwyciestwa", 1u), "132456789") ,
        Firma("Tesco", "4891456489", Adres("Polska", "Slask", KodPocztowy("41-800"), "Zabrze", "Karlowicza", 5u), "789456123") ,
        Firma("Orlen", "4859263135", Adres("Polska", "Slask", KodPocztowy("44-100"), "Gliwice", "Tarnogorska", 11u), "951735624")
    )

    val osobaFizyczna = OsobaFizyczna("Michal", "Wesolowski")

    val dowodyPlatnosci = listOf<DowodPlatnosci>(
        Paragon(firmy[0], Date()),
        Paragon(firmy[1], Date()),
        Rachunek(firmy[2], osobaFizyczna, Date()),
        Faktura(firmy[1], firmy[0], Date())
    )

    val produkty = listOf<Produkt>(
        Produkt("Coca-Cola", 8.99, StawkaVat.A),
        Produkt("Lech", 4.49, StawkaVat.A),
        Produkt("Chleb", 4.99, StawkaVat.B),
        Produkt("Paliwo PB100", 748.99, StawkaVat.C),
        Produkt("Woda", 4.99, StawkaVat.D),
        Produkt("Serek", 2.99, StawkaVat.B),
        Produkt("Karma dla psa", 18.99, StawkaVat.C)
    )

    dowodyPlatnosci[0].DodajZakup(Zakup(produkty[0], 2u))
    dowodyPlatnosci[0].DodajZakup(Zakup(Produkt(produkty[1]), 4u, Manager.Companion.Rabat.TYP2))
    dowodyPlatnosci[0].DodajZakup(Zakup(produkty[2], 2u))

    dowodyPlatnosci[1].DodajZakup(Zakup(produkty[5], 8u))
    dowodyPlatnosci[1].DodajZakup(Zakup(Produkt(produkty[4]), 4u, Manager.Companion.Rabat.TYP1))
    dowodyPlatnosci[1].DodajZakup(Zakup(produkty[6], 1u))
    dowodyPlatnosci[1].DodajZakup(Zakup(produkty[1], 24u))
    dowodyPlatnosci[1].DodajZakup(Zakup(Produkt(produkty[2]), 16u, Manager.Companion.Rabat.TYP3))

    dowodyPlatnosci[2].DodajZakup(Zakup(produkty[3], 1u))
    dowodyPlatnosci[2].DodajZakup(Zakup(produkty[1], 2u))

    dowodyPlatnosci[3].DodajZakup(Zakup(produkty[0], 12u))
    dowodyPlatnosci[3].DodajZakup(Zakup(produkty[1], 44u))
    dowodyPlatnosci[3].DodajZakup(Zakup(produkty[2], 800u))
    dowodyPlatnosci[3].DodajZakup(Zakup(produkty[4], 123u))
    dowodyPlatnosci[3].DodajZakup(Zakup(produkty[5], 44u))
    dowodyPlatnosci[3].DodajZakup(Zakup(produkty[6], 199u))


    for (dowodPlatnosci in dowodyPlatnosci) {
        dowodPlatnosci.Drukuj()
        println()
    }

}
