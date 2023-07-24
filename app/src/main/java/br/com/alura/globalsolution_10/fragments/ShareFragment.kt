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
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import br.com.alura.globalsolution_10.*
import br.com.alura.globalsolution_10.databinding.FragmentForumBinding
import br.com.alura.globalsolution_10.databinding.FragmentShareBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShareFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShareFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var databind: FragmentShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databind = FragmentShareBinding.inflate(inflater, container, false)
        val toastText = "Based on Your preferences I have aligned you with the following farmers..."
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(requireContext(), toastText, duration)
        toast.show()
        fun toggleAgrupamento1() {
            val agrupamento4 = databind.agrupamento4
            val isVisible = agrupamento4.visibility == View.VISIBLE

            if (isVisible) {
                agrupamento4.visibility = View.GONE
            } else {
                agrupamento4.visibility = View.VISIBLE
            }
        }



        toggleAgrupamento1()

        var userFirstName: String? = null


        parentFragmentManager.setFragmentResultListener("myKey", this) { key, bundle ->
            val updatedName = bundle.getParcelable<Login>("updatedName")
            updatedName?.let { login ->
                userFirstName = login.userFirstName
            }
        }

        val register = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { data ->
                    val updatedProduct = data.getParcelableExtra<Share>("updatedProduct")
                    updatedProduct?.let { share ->
                        val productName = share.productName
                        val qtdProduct = share.qtdProduct
                        if (productName != null) {
                            Log.d("chegou", productName)



                            databind.product4.text = productName
                            databind.qtd4.text = qtdProduct
                            toggleAgrupamento1()
                            databind.TESTENOME4.text = userFirstName
                        }
                    }
                }
            }
        }


        parentFragmentManager.setFragmentResultListener("myKey", this) { key, bundle ->
            val updatedName = bundle.getParcelable<Login>("updatedName")
            updatedName?.let { login ->
                userFirstName = login.userFirstName
            }
        }
        val btnPost = databind.fab
        btnPost.setOnClickListener {
            val intent = Intent(context, ShareActivity::class.java)
            var productName = "productTest"
            var qtdProduct = "10ton"


            intent.putExtra("productName", productName )
            intent.putExtra("qtdProduct", qtdProduct )

//            startActivity(intent)

            register.launch(intent)


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
         * @return A new instance of fragment ShareFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShareFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}