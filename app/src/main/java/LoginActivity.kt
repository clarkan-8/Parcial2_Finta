package com.example.parcial2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLoginTab: Button
    private lateinit var btnRegisterTab: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnEnter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a las vistas
        btnLoginTab = findViewById(R.id.btnLoginTab)
        btnRegisterTab = findViewById(R.id.btnRegisterTab)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnEnter = findViewById(R.id.btnEnter)

        // Botón principal: iniciar sesión
        btnEnter.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: validar credenciales (base de datos, API, etc.)
            login(email, password)
        }

        // Tab REGISTER -> ir a la pantalla de registro
        btnRegisterTab.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Tab LOGIN (ya estás aquí, opcional)
        btnLoginTab.setOnClickListener {
            // Ya estás en login, no hace nada o refresca
        }
    }

    private fun login(email: String, password: String) {
        // Lógica de autenticación de ejemplo
        Toast.makeText(this, "Bienvenido $email", Toast.LENGTH_SHORT).show()

        // Ejemplo: navegar a otra pantalla tras login
        // startActivity(Intent(this, RegistroCarreraActivity::class.java))
        // finish()
    }
}