package br.com.alura.globalsolution_10.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.recreate
import androidx.core.os.bundleOf
import br.com.alura.globalsolution_10.Login
import br.com.alura.globalsolution_10.databinding.FragmentHomeBinding
import br.com.alura.globalsolution_10.LoginActivity
import br.com.alura.globalsolution_10.Theme
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var databind: FragmentHomeBinding

class HomeFragment : Fragment() {




    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        databind = FragmentHomeBinding.inflate(inflater, container, false)

         val register = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { data ->
                    val updatedName = data.getParcelableExtra<Login>("updatedName")
                    updatedName?.let { login ->
                        val userFirstName = login.userFirstName
                        if (userFirstName != null) {
                            Log.d("chegou", userFirstName)

                            val forumFunctionsText = "\nOur forum provides a platform for like-minded individuals to connect, share knowledge, and discuss various topics related to food, agriculture, and sustainable living.\n\n"
                            val shareFoodText = "In addition, we offer a unique feature called 'Share Food' where you can connect with local farmers and share or trade surplus produce, fostering a sense of community and reducing food waste.\n\n"
                            val aiGuideText = "As your AI guide, I'm here to assist you throughout your journey on Agrishare."

                            val formattedText = if (Locale.getDefault().language == "pt" && Locale.getDefault().country == "BR") {
                                val alternativeInstructions = "Seja bem-vindo ao Agrishare <b>$userFirstName!</b><br>\n\n" +
                                        "Nossa plataforma oferece um espaço para conectar-se com pessoas interessadas em agricultura, compartilhar conhecimentos e discutir temas relacionados a alimentos e sustentabilidade.\n\n" +
                                        "Além disso, temos uma funcionalidade exclusiva chamada 'Compartilhar Alimentos', onde você pode se conectar com produtores locais para compartilhar ou trocar excedentes de alimentos, promovendo a comunidade e reduzindo o desperdício.\n\n" +
                                        "Como seu guia de inteligência artificial, estou aqui para ajudá-lo em sua jornada no Agrishare."
                                alternativeInstructions
                            } else {
                                "Welcome to Agrishare <b>$userFirstName!</b><br>\n\n$forumFunctionsText$shareFoodText$aiGuideText"
                            }


                            // Set the result with the userFirstName to be received by the other fragment
                            parentFragmentManager.setFragmentResult("myKey", bundleOf("updatedName" to login))

                            // Optionally, you can also update TESTE NOME TextView in the current fragment
//                        databind.TESTENOME.text = formattedText
                            val toastText = "Hello, $userFirstName!"
                            val duration = Toast.LENGTH_LONG
                            val toast = Toast.makeText(requireContext(), toastText, duration)
                            toast.show()
                            databind.textWelcome.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_COMPACT)
                        }
                    }
                }
            }
        }
//        val welcomeText = "@string/welcome"
        var userFirstName = "User" // Replace with the user's first name
//        val userProfileText = "To make the most of Agrishare's services, we encourage you to create a profile. By doing so, We the AI can better understand your preferences and pair you with suitable farmers.\n\n"
//        val createProfileText = "\nTo create your profile, click on the 'Create Profile' button below.\n"
//
//        val formattedText = "$welcomeText Hello, <b>$userFirstName!</b><br><br>$userProfileText<br><br>$createProfileText"
//        databind.textWelcome.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_COMPACT)



        databind.btnCadastro.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra("userFirstName", userFirstName)
//            startActivity(intent)
            register.launch(intent)

        }


        val changeTheme = databind.fab

        changeTheme.setOnClickListener{
            Theme.switchTheme()
            requireActivity().recreate()
        }

        return databind.root
    }



    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}