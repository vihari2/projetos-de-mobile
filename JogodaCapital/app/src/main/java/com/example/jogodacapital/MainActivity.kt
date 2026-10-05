package com.example.jogodacapital

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val states = arrayOf(
        "São Paulo", "Paraná", "Rio Grande do Sul", "Distrito Federal", "Rio de Janeiro",
        "Minas Gerais", "Bahia", "Amazonas", "Pernambuco", "Ceará"
    )
    private val capitals = arrayOf(
        "São Paulo", "Curitiba", "Porto Alegre", "Brasília", "Rio de Janeiro",
        "Belo Horizonte", "Salvador", "Manaus", "Recife", "Fortaleza"
    )

    private val alreadyPromptedIndexes = mutableSetOf<Int>()
    private var currentStateIndex: Int = -1
    private var points: Int = 0
    private val totalQuestions = 5

    // UI Elements
    private lateinit var txtProgresso: TextView
    private lateinit var txtEnunciado: TextView
    private lateinit var btnAlternativa1: Button
    private lateinit var btnAlternativa2: Button
    private lateinit var btnAlternativa3: Button
    private lateinit var btnAlternativa4: Button
    
    private lateinit var cardPergunta: View
    private lateinit var layoutFimDeJogo: View
    private lateinit var txtPontuacaoFinal: TextView
    private lateinit var btnReiniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeViews()
        startNewGame()
    }

    private fun initializeViews() {
        txtProgresso = findViewById(R.id.txtProgresso)
        txtEnunciado = findViewById(R.id.txtEnunciado)
        btnAlternativa1 = findViewById(R.id.btnAlternativa1)
        btnAlternativa2 = findViewById(R.id.btnAlternativa2)
        btnAlternativa3 = findViewById(R.id.btnAlternativa3)
        btnAlternativa4 = findViewById(R.id.btnAlternativa4)
        
        cardPergunta = findViewById(R.id.cardPergunta)
        layoutFimDeJogo = findViewById(R.id.layoutFimDeJogo)
        txtPontuacaoFinal = findViewById(R.id.txtPontuacaoFinal)
        btnReiniciar = findViewById(R.id.btnReiniciar)

        val clickListener = View.OnClickListener { v ->
            if (v is Button) {
                evaluateTrial(v.text.toString())
            }
        }

        btnAlternativa1.setOnClickListener(clickListener)
        btnAlternativa2.setOnClickListener(clickListener)
        btnAlternativa3.setOnClickListener(clickListener)
        btnAlternativa4.setOnClickListener(clickListener)
        
        btnReiniciar.setOnClickListener {
            startNewGame()
        }
    }

    private fun startNewGame() {
        points = 0
        alreadyPromptedIndexes.clear()
        
        layoutFimDeJogo.visibility = View.GONE
        
        // Show quiz elements
        txtProgresso.visibility = View.VISIBLE
        cardPergunta.visibility = View.VISIBLE
        btnAlternativa1.visibility = View.VISIBLE
        btnAlternativa2.visibility = View.VISIBLE
        btnAlternativa3.visibility = View.VISIBLE
        btnAlternativa4.visibility = View.VISIBLE
        
        nextQuestion()
    }

    private fun nextQuestion() {
        if (alreadyPromptedIndexes.size >= totalQuestions) {
            showFinalResult()
            return
        }

        setButtonsEnabled(true)

        // Update progress
        txtProgresso.text = "Pergunta ${alreadyPromptedIndexes.size + 1}/$totalQuestions"

        // Pick an unprompted state
        val remainingIndexes = states.indices.filter { it !in alreadyPromptedIndexes }
        currentStateIndex = remainingIndexes.random()
        alreadyPromptedIndexes.add(currentStateIndex)

        val targetState = states[currentStateIndex]
        val correctAnswer = capitals[currentStateIndex]

        // Pick 3 wrong options randomly
        val allOtherCapitals = capitals.filterIndexed { index, _ -> index != currentStateIndex }
        val wrongOptions = allOtherCapitals.shuffled().take(3)
        val options = (wrongOptions + correctAnswer).shuffled()

        txtEnunciado.text = "Qual é a capital de $targetState?"
        btnAlternativa1.text = options[0]
        btnAlternativa2.text = options[1]
        btnAlternativa3.text = options[2]
        btnAlternativa4.text = options[3]
    }

    private fun evaluateTrial(selectedAnswer: String) {
        val correctAnswer = capitals[currentStateIndex]
        setButtonsEnabled(false)

        if (selectedAnswer == correctAnswer) {
            points++
            Toast.makeText(this, "✅ Você acertou!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "❌ Errou! A capital era $correctAnswer", Toast.LENGTH_SHORT).show()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            nextQuestion()
        }, 1000)
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        btnAlternativa1.isEnabled = enabled
        btnAlternativa2.isEnabled = enabled
        btnAlternativa3.isEnabled = enabled
        btnAlternativa4.isEnabled = enabled
    }

    private fun showFinalResult() {
        txtProgresso.visibility = View.GONE
        cardPergunta.visibility = View.GONE
        btnAlternativa1.visibility = View.GONE
        btnAlternativa2.visibility = View.GONE
        btnAlternativa3.visibility = View.GONE
        btnAlternativa4.visibility = View.GONE
        
        layoutFimDeJogo.visibility = View.VISIBLE
        txtPontuacaoFinal.text = "Você acertou $points de $totalQuestions perguntas"
    }
}