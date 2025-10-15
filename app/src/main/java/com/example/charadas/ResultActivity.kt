package com.example.charadas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charadas.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Recibe el puntaje de la Activity anterior
        val finalScore = intent.getIntExtra("EXTRA_SCORE", 0)

        // 2. Muestra el puntaje
        binding.tvFinalScore.text = finalScore.toString()

        // 3. Configura el botón para jugar de nuevo (Reiniciar)
        binding.btnPlayAgain.setOnClickListener {
            // Vuelve a la pantalla de categorías (inicio del juego)
            val intent = Intent(this, CategoryActivity::class.java)
            // Esta bandera es crucial para borrar las Activities anteriores
            // y empezar desde cero.
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish() // Cierra la Activity de resultados
        }
    }
}