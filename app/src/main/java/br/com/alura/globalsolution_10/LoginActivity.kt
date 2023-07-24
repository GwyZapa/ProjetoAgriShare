package br.com.alura.globalsolution_10

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.globalsolution_10.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var databind: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(databind.root)


        val userFirstName = intent.getStringExtra("userFirstName")
        databind.usernameEditText.setText(userFirstName)


        databind.saveUserName.setOnClickListener {
            val login = Login(databind.usernameEditText.text.toString())
            val resultIntent = Intent().apply {
                putExtra("updatedName", login)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }


    }

}