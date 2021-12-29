package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.Adapter.EProductAdapter
import com.example.myapplication.databinding.ActivityFetchEproductsBinding
import com.example.myapplication.model.EProduct

class FetchEProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFetchEproductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFetchEproductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedBrand: String? = intent.getStringExtra("BRAND")
        binding.txtBranName.text = "Product of $selectedBrand"

        var productsList = ArrayList<EProduct>()

        val productsUrl = "http://192.168.1.130/onLineSotreApp/fetch_eproducts.php?brand=$selectedBrand"

        val requestQ = Volley.newRequestQueue(this)

        val jsonAR = JsonArrayRequest(Request.Method.GET,productsUrl,null, {
            response ->
            for (productJOIndex in 0.until(response.length())){

                productsList.add(
                    EProduct(response.getJSONObject(productJOIndex).getInt("id"),
                    response.getJSONObject(productJOIndex).getString("name"),
                    response.getJSONObject(productJOIndex).getInt("price"),
                    response.getJSONObject(productJOIndex).getString("picture")
                )
                )
            }

            val pAdapter = EProductAdapter(this,productsList)
            binding.productsRV.layoutManager = LinearLayoutManager(this)
            binding.productsRV.adapter = pAdapter


        }, { error ->  })

        requestQ.add(jsonAR)




    }
}