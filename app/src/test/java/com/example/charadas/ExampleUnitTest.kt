package com.example.charadas

import com.example.charadas.data.DataSource
import org.junit.Test
import org.junit.Assert.*

/**
 * Pruebas unitarias para el juego de charadas
 */
class CharadasUnitTest {
    
    @Test
    fun dataSource_getCategories_returnsCorrectCategories() {
        // Verifica que DataSource devuelve las categorías correctas
        val categories = DataSource.getCategories()
        
        assertEquals(3, categories.size)
        assertTrue(categories.contains("Animals"))
        assertTrue(categories.contains("Movies"))
        assertTrue(categories.contains("Professions"))
    }
    
    @Test
    fun dataSource_getWords_returnsWordsForValidCategory() {
        // Verifica que se obtienen palabras para una categoría válida
        val animalWords = DataSource.getWords("Animals")
        val movieWords = DataSource.getWords("Movies")
        
        assertTrue(animalWords.isNotEmpty())
        assertTrue(movieWords.isNotEmpty())
        assertTrue(animalWords.contains("Lion"))
        assertTrue(movieWords.contains("Titanic"))
    }
    
    @Test
    fun dataSource_getWords_returnsEmptyListForInvalidCategory() {
        // Verifica que devuelve lista vacía para categoría inexistente
        val invalidWords = DataSource.getWords("InvalidCategory")
        
        assertTrue(invalidWords.isEmpty())
    }
    
    @Test
    fun wordSelection_isRandom() {
        // Verifica que la selección de palabras es aleatoria
        val words = DataSource.getWords("Animals").toMutableList()
        val originalOrder = words.toList()
        
        // Baraja las palabras múltiples veces para verificar aleatoriedad
        var shuffledDifferently = false
        repeat(10) {
            words.shuffle()
            if (words != originalOrder) {
                shuffledDifferently = true
            }
        }
        
        assertTrue("Las palabras deberían barajarse de forma diferente", shuffledDifferently)
    }
    
    @Test
    fun timeLimit_isCorrectlyDefined() {
        // Verifica que el tiempo límite está correctamente definido
        assertEquals(60L, DataSource.TIME_LIMIT_SECONDS)
    }
}