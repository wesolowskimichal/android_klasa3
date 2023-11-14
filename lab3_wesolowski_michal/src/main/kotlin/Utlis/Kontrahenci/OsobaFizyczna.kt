package Utlis.Kontrahenci

data class OsobaFizyczna(val imie: String, val nazwisko: String) {
    override fun toString(): String {
        return "$imie $nazwisko"
    }
}