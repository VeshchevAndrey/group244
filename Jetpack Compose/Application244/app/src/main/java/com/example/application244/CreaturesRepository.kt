package com.example.application244

object CreaturesRepository {
    val creatures = listOf(
        Creature(
            id = 1,
            name = "Гоблин",
            description = "Маленький проворный противник",
            image = R.drawable.goblin_ffvii
        ),
        Creature(
            id = 2,
            name = "Дракон",
            description = "Древнее огнедышащее существо",
            image = R.drawable.dragon_ffvii
        ),
        Creature(
            id = 3,
            name = "Железный гигант",
            description = "Громадное непробиваемое существо",
            image = R.drawable.iron_man_ffvii
        ),
    )

    fun getCreatureById(id: Int) = creatures.find { it.id == id }
}