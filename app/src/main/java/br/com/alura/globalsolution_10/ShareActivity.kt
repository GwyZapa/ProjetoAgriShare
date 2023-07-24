package br.com.alura.globalsolution_10

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.globalsolution_10.databinding.ActivityLoginBinding
import br.com.alura.globalsolution_10.databinding.ActivityShareBinding
import br.com.alura.globalsolution_10.databinding.FragmentShareBinding

class ShareActivity : AppCompatActivity() {
    private lateinit var databind: ActivityShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityShareBinding.inflate(layoutInflater)
        setContentView(databind.root)

        val productName = intent.getStringExtra("productName")
        val qtdProduct = intent.getStringExtra("qtdProduct")



        databind.productEditText.setText(productName)
        databind.qtdEditText.setText(qtdProduct)


        databind.saveProduct.setOnClickListener {
            val share = Share(databind.productEditText.text.toString(), databind.qtdEditText.text.toString())
            val resultIntent = Intent().apply {
                putExtra("updatedProduct", share)


            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}