package com.example.parcial2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context: Context) :
    SQLiteOpenHelper(context, "usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fullName TEXT,
                email TEXT UNIQUE,
                password TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    // Registrar usuario. Devuelve true si se insertó, false si el email ya existe
    fun registrarUsuario(fullName: String, email: String, password: String): Boolean {
        // Verificar si el email ya existe
        if (existeEmail(email)) return false

        val db = writableDatabase
        val valores = ContentValues().apply {
            put("fullName", fullName)
            put("email", email)
            put("password", password)
        }
        val resultado = db.insert("usuarios", null, valores)
        db.close()
        return resultado != -1L
    }

    // Validar login: true si email + password coinciden
    fun validarLogin(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE email = ? AND password = ?",
            arrayOf(email, password)
        )
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }

    // Verificar si un email ya está registrado
    fun existeEmail(email: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE email = ?",
            arrayOf(email)
        )
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }
}