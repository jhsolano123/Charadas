package com.example.charadas


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charadas.data.DataSource
import com.example.charadas.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    // Declaración del binding
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialización del binding
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Asigna listeners a los botones de categoría
        setupCategoryButtons()
    }

    private fun setupCategoryButtons() {
        // Obtiene la lista de categorías del DataSource
        val categories = DataSource.getCategories()

        // Agrupa los botones del layout para facilitar la asignación
        val buttons = listOf(binding.btnCategory1, binding.btnCategory2, binding.btnCategory3)

        buttons.forEachIndexed { index, button ->
            if (index < categories.size) {
                val categoryName = categories[index]

                // 1. Asigna el nombre de la categoría al botón
                button.text = categoryName

                // 2. Asigna el listener al botón
                button.setOnClickListener {
                   /// startGame(categoryName)
                }
            } else {
                // Oculta botones si no hay suficientes categorías en el DataSource
                button.visibility = android.view.View.GONE
            }
        }
    }

    /**
     * Lanza la GameActivity y le pasa la categoría seleccionada como un 'Extra'.
     */
/**private fun startGame(category: String) {
        val intent = Intent(this, GameActivity::class.java).apply {
            // El String "EXTRA_CATEGORY" será la clave para recuperar el valor
            putExtra("EXTRA_CATEGORY", category)
        }
        startActivity(intent)
    }*/
}