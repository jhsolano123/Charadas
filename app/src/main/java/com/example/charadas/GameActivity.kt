<<<<<<< HEAD
package com.example.charadesapp
=======
package com.example.charadas
>>>>>>> 92c91b6 (aca introdusco la pantalla de resultados y el juego como tal Recibe la categoría seleccionada de CategoryActivity,Obtiene las palabras,Baraja las palabras ,Inicia un temporizador,Muestra palabras una por una)

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import com.example.charadesapp.data.DataSource
import com.example.charadesapp.databinding.ActivityGameBinding
import java.util.*
=======
import com.example.charadas.data.DataSource
import com.example.charadas.databinding.ActivityGameBinding
import java.util.*
import kotlin.apply
import kotlin.collections.isNotEmpty
import kotlin.collections.shuffle
import kotlin.collections.toMutableList
import kotlin.jvm.java
import kotlin.text.format
>>>>>>> 92c91b6 (aca introdusco la pantalla de resultados y el juego como tal Recibe la categoría seleccionada de CategoryActivity,Obtiene las palabras,Baraja las palabras ,Inicia un temporizador,Muestra palabras una por una)

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var countDownTimer: CountDownTimer

    private var category: String = ""
    private var score: Int = 0
    private lateinit var words: MutableList<String>
    private var currentWord: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Obtiene la categoría seleccionada de la Activity anterior
        category = intent.getStringExtra("EXTRA_CATEGORY") ?: "Categoría Desconocida"

        initGame()
        setupButtons()
        startTimer()
    }

    private fun initGame() {
        // 2. Obtener palabras, barajarlas y establecer el puntaje inicial
        words = DataSource.getWords(category).toMutableList()
        words.shuffle() // Aleatoriedad
        score = 0
        updateWord()
    }

    private fun updateWord() {
        if (words.isNotEmpty()) {
            currentWord = words.removeAt(0)
            binding.tvWord.text = currentWord
        } else {
            // Manejar caso cuando se acaban las palabras
            binding.tvWord.text = "¡FIN DE PALABRAS!"
            // Si el tiempo no ha terminado, terminamos el juego de inmediato
            endGame()
        }
        binding.tvScore.text = "Puntaje: $score"
    }

    private fun setupButtons() {
        binding.btnCorrect.setOnClickListener {
            score++ // Suma punto
            updateWord()
        }

        binding.btnSkip.setOnClickListener {
            // No suma punto, solo pasa a la siguiente palabra
            updateWord()
        }
    }

    private fun startTimer() {
        val timeLimitMillis = DataSource.TIME_LIMIT_SECONDS * 1000

        countDownTimer = object : CountDownTimer(timeLimitMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 3. Control del temporizador
                val seconds = millisUntilFinished / 1000
                binding.tvTimer.text = String.format(Locale.getDefault(), "%02d", seconds)

                // Efecto visual opcional: cambia el color del timer cuando queda poco tiempo
                if (seconds <= 10) {
                    binding.tvTimer.setTextColor(Color.RED)
                }
            }

            override fun onFinish() {
                // 4. Fin del juego
                binding.tvTimer.text = "00"
                binding.root.setBackgroundColor(Color.parseColor("#FFC7C7")) // Fondo rojo claro
                endGame()
            }
        }.start()
    }

    private fun endGame() {
        // Detiene el temporizador y lanza la pantalla de resultados
        countDownTimer.cancel()

        val intent = Intent(this, ResultActivity::class.java).apply {
<<<<<<< HEAD
            putExtra("EXTRA_SCORE", score)
=======
            Intent.putExtra("EXTRA_SCORE", score)
>>>>>>> 92c91b6 (aca introdusco la pantalla de resultados y el juego como tal Recibe la categoría seleccionada de CategoryActivity,Obtiene las palabras,Baraja las palabras ,Inicia un temporizador,Muestra palabras una por una)
        }
        startActivity(intent)
        // Cierra esta Activity para que el usuario no pueda volver con el botón "Atrás"
        finish()
    }

    override fun onStop() {
        super.onStop()
        // Cancela el temporizador si la Activity se detiene por cualquier razón
        countDownTimer.cancel()
    }
}