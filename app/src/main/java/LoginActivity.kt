package com.example.parcial2
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class loginActivity : AppCompatActivity() {

    private lateinit var btnLoginTab: Button
    private lateinit var btnRegisterTab: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnEnter: Button

    private lateinit var dbHelper: UserDbHelper

    private lateinit var txtRegister: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        dbHelper = UserDbHelper(this)
        // Referencias a las vistas (SIEMPRE después de setContentView)
        btnLoginTab = findViewById(R.id.btnLoginTab)
        btnRegisterTab = findViewById(R.id.btnRegisterTab)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnEnter = findViewById(R.id.btnEnter)
        txtRegister = findViewById(R.id.tvRegister)
        // Iniciar sesión
        btnEnter.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Aquí ocurre el login: valida contra la base de datos
            if (dbHelper.validarLogin(email, password)) {
                Toast.makeText(this, "Bienvenido $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, StadisticsActivity::class.java))

            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        // Tab REGISTER -> ir a registro
        btnRegisterTab.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Tab LOGIN (ya estás aquí)
        btnLoginTab.setOnClickListener {
            // No hace nada
        }
        txtRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}