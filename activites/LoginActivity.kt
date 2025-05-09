package com.example.promanagement.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.promanagement.R
import android.content.Intent
import com.example.promanagement.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.promanagement.activities.SignInActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var loginName: EditText
    private lateinit var passwordName: EditText
    private lateinit var loginButton: Button
    private lateinit var createAccountText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginName = findViewById(R.id.login_name)
        passwordName = findViewById(R.id.password_name)
        loginButton = findViewById(R.id.login_button)
        createAccountText = findViewById(R.id.createaccount)

        loginButton.setOnClickListener {
            val username = loginName.text.toString()
            val password = passwordName.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val db = AppDatabase.getDatabase(applicationContext)
                    val user = db.userDao().getUserByUsernameAndPassword(username, password)

                    runOnUiThread {
                        if (user != null) {
                            val intent = Intent(this@LoginActivity, Homepage::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Username atau Password salah", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Username atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        createAccountText.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
        }
    }
}