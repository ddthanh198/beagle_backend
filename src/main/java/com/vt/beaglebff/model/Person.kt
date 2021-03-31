package com.vt.beaglebff.model

data class Person (
        val name: String,
        val race: String,
        val planet: String,
        val isMistborn: Boolean,
        val age: Int,
        val sex: String
)

fun populateData() = listOf(
        Person(
                name = "Kelsier",
                race = "Half-skaa",
                planet = "Scadrial",
                isMistborn = true,
                age = 38,
                sex = "male"
        ),
        Person(
                name = "Vin",
                race = "Half-skaa",
                planet = "Scadrial",
                isMistborn = true,
                age = 20,
                sex = "female"
        ),
        Person(
                name = "TenSoon",
                race = "Kandra",
                planet = "Scadrial",
                isMistborn = false,
                age = 40,
                sex = "male"
        ),
        Person(
                name = "Kelsier",
                race = "Half-skaa",
                planet = "Scadrial",
                isMistborn = true,
                age = 38,
                sex = "male"
        ),
        Person(
                name = "Vin",
                race = "Half-skaa",
                planet = "Scadrial",
                isMistborn = true,
                age = 20,
                sex = "female"
        ),
        Person(
                name = "TenSoon",
                race = "Kandra",
                planet = "Scadrial",
                isMistborn = false,
                age = 40,
                sex = "male"
        ),
        Person(
                name = "Kelsier",
                race = "Half-skaa",
                planet = "Scadrial",
                isMistborn = true,
                age = 38,
                sex = "male"
        ),
        Person(
                name = "Vin",
                race = "Half-skaa",
                planet = "Scadrial",
                isMistborn = true,
                age = 20,
                sex = "female"
        ),
        Person(
                name = "TenSoon",
                race = "Kandra",
                planet = "Scadrial",
                isMistborn = false,
                age = 40,
                sex = "male"
        )
)