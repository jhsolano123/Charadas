package com.example.charadas.data

object DataSource {
    const val TIME_LIMIT_SECONDS = 60L // Tiempo por ronda

    val wordsMap = mapOf(
        "Animals" to listOf(
            "Lion", "Elephant", "Monkey", "Dolphin", "Penguin", "Giraffe"
        ),
        "Movies" to listOf(
            "Titanic", "Inception", "Avatar", "Star Wars", "The Matrix", "Jaws"
        ),
        "Professions" to listOf(
            "Doctor", "Teacher", "Firefighter", "Chef", "Astronaut", "Engineer"
        )
    )

    fun getWords(category: String): List<String> {
        return wordsMap[category] ?: emptyList()
    }

    fun getCategories(): List<String> {
        return wordsMap.keys.toList()
    }
}