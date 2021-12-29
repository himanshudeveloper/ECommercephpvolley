package com.example.myapplication

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R

class AmountFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)

        var edtEnterAmount = fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnAddtoCard = fragmentView.findViewById<ImageButton>(R.id.btnAddToCard)

        btnAddtoCard.setOnClickListener {
            val ptoUrl ="http://192.168.1.130/onLineSotreApp/insert_temporary_order.php?email=${Person.email}&product_id=${Person.addToCardProductId}&amount=${edtEnterAmount.text.toString()}"
            var requestQue  = Volley.newRequestQueue(activity)
            var stringRequest = StringRequest(Request.Method.GET,ptoUrl, {
                    response ->

                var intent = Intent(activity,CartProdctActivity::class.java)
                startActivity(intent)

                
            }, {
                error ->
            })

            requestQue.add(stringRequest)
        }
        return fragmentView
    }


}