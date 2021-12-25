package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class FetchEProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)

        val selectedBrand: String? = intent.getStringExtra("BRAND")

        var productsList = ArrayList<EProduct>()

        val productsUrl = "http://192.168.1.130/onLineSotreApp/fetch_eproducts.php?brand=$selectedBrand"

        val requestQ = Volley.newRequestQueue(this)

        val jsonAR = JsonArrayRequest(Request.Method.GET,productsUrl,null, {
            response ->
            for (productJOIndex in 0.until(response.length())){

                productsList.add(EProduct(response.getJSONObject(productJOIndex).getInt("id"),
                    response.getJSONObject(productJOIndex).getString("name"),
                    response.getJSONObject(productJOIndex).getInt("price"),
                    response.getJSONObject(productJOIndex).getString("picture")
                ))
            }


        }, { error ->  })




    }
}