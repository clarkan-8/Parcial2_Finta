package com.example.parcial2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etFullName  = findViewById<EditText>(R.id.etFullName)
        val etEmail     = findViewById<EditText>(R.id.etEmail)
        val etPassword  = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnLoginTab = findViewById<Button>(R.id.btnLoginTab)
        val txtLogin    = findViewById<TextView>(R.id.txtLogin)

        btnRegister.setOnClickListener {
            val name     = etFullName.text.toString().trim()
            val email    = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (name.isEmpty()) {
                etFullName.error = "Ingresa tu nombre"
                etFullName.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Email inválido"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6) {
                etPassword.error = "Mínimo 6 caracteres"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            Toast.makeText(this, "Cuenta creada ✓", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val irLogin = View.OnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        btnLoginTab.setOnClickListener(irLogin)
        txtLogin.setOnClickListener(irLogin)
    }
}