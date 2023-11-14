package Utlis.Zakup

data class Produkt(val nazwa: String, var cenaBrutto: Double, val stawkaVat: StawkaVat)
{
    constructor(produkt: Produkt) : this(produkt.nazwa, produkt.cenaBrutto, produkt.stawkaVat)

    val cenaNetto: Double
        get() = cenaBrutto / (1.0 + stawkaVat.wartosc)

    val Netto: Double
        get() = cenaBrutto - cenaNetto
}