package com.example.a215_ic_projekt_szymon.database

data class Kierunki(
    var id: Int,
    val wydzial: String,
    val kierunek: String,
    val rodzaj: String,
    val opis: String,
    val czyUlubiony: Int
)
