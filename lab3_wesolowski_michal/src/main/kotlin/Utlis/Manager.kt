package Utlis

class Manager {
    companion object{
        private var _numerParagonu = 1UL
        private var _numerRachunku = 1UL
        private var _numerFaktury = 1UL

        val NumerParagonu: ULong
            get() {
                val numer = _numerParagonu
                _numerParagonu++
                return numer
            }

        val NumerRachunku: ULong
            get() {
                val numer = _numerRachunku
                _numerRachunku++
                return numer
            }

        val NumerFaktury: ULong
            get() {
                val numer = _numerFaktury
                _numerFaktury++
                return numer
            }

        enum class Rabat(val wartosc: Double) {
            TYP0(0.0),
            TYP1(0.05),
            TYP2(0.1),
            TYP3(0.15)
        }
    }
}