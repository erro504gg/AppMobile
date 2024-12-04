package com.example.teste

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.teste.ui.theme.TesteTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)

           loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (authenticateUser(username, password)) {

                val intent = Intent(this, ProductActivity::class.java)
                startActivity(intent)
            } else {

                Toast.makeText(this, "Usuário ou senha inválida", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun authenticateUser(username: String, password: String): Boolean {
        return username == "walter" && password == "1234"
    }
}
