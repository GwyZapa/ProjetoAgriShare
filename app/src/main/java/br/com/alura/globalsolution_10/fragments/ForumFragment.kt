package br.com.alura.globalsolution_10.fragments

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import br.com.alura.globalsolution_10.Login
import br.com.alura.globalsolution_10.R
import br.com.alura.globalsolution_10.databinding.FragmentForumBinding
import br.com.alura.globalsolution_10.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForumFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var databind: FragmentForumBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {
        // Inflate the layout for this fragment
        databind = FragmentForumBinding.inflate(inflater, container, false)
        fun swapText() {
            val editText = databind.editText
            val tituloTextView = databind.TESTETITULO
            val conteudoTextView = databind.TESTECONTEUDO

            val editTextValue = editText.text.toString()
            val periodIndex = editTextValue.indexOf('.')
            val tituloValue = if (periodIndex != -1) {
                editTextValue.substring(0, periodIndex)
            } else {
                editTextValue
            }
            val conteudoValue = if (periodIndex != -1 && periodIndex + 1 < editTextValue.length) {
                editTextValue.substring(periodIndex + 1)
            } else {
                ""
            }

            tituloTextView.text = tituloValue.trim()
            conteudoTextView.text = conteudoValue.trim()
            editText.setText("") // Clear the EditText if needed
        }

        fun toggleAgrupamento1() {
            val agrupamento1 = databind.agrupamento1
            val isVisible = agrupamento1.visibility == View.VISIBLE

            if (isVisible) {
                agrupamento1.visibility = View.GONE
            } else {
                agrupamento1.visibility = View.VISIBLE
            }
        }
        toggleAgrupamento1()


// Add this code in the onCreateView or onViewCreated method of the receiving fragment
        var userFirstName: String? = null

        parentFragmentManager.setFragmentResultListener("myKey", this) { key, bundle ->
            val updatedName = bundle.getParcelable<Login>("updatedName")
            updatedName?.let { login ->
                userFirstName = login.userFirstName
            }
        }
        val btnPost = databind.btnPost
        btnPost.setOnClickListener {
            swapText()
            userFirstName?.let { firstName ->
                // Update the TESTENOME TextView with the userFirstName

                toggleAgrupamento1()

                databind.TESTENOME.text = firstName
            }
        }
        return databind.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}