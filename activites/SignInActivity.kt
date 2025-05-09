package com.example.promanagement.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.promanagement.R
import com.example.promanagement.database.AppDatabase
import com.example.promanagement.database.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var alreadyUserTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        emailEditText = findViewById(R.id.login_name)
        usernameEditText = findViewById(R.id.logins_name)
        passwordEditText = findViewById(R.id.password_name)
        signInButton = findViewById(R.id.signin_button)
        alreadyUserTextView = findViewById(R.id.createaccount)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Tolong Isi Semuanya", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format Email Salah", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(email = email, username = username, password = password)

            CoroutineScope(Dispatchers.IO).launch {
                val db = AppDatabase.getDatabase(applicationContext)
                db.userDao().insert(user)

                runOnUiThread {
                    Toast.makeText(this@SignInActivity, "Sign In Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignInActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}