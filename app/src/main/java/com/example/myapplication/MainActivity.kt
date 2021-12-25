package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var brandsUrl = "http://192.168.1.130/onLineSotreApp/fetch_brands.php"

        var brandsList = ArrayList<String>()
        var requestQ = Volley.newRequestQueue(this)
        var jsonAR = JsonArrayRequest(Request.Method.GET,brandsUrl,null, { response ->

            for (jsonObject in 0.until(response.length())) {

                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))
            }
            var brandsListAdapter = ArrayAdapter(this,R.layout.brand_item_textview,brandsList)
            binding.brandsListview.adapter = brandsListAdapter


        },Response.ErrorListener { error ->


        })

        requestQ.add(jsonAR)

        binding.brandsListview.setOnItemClickListener { parent, view, position, id ->

            val tappedBrand = brandsList.get(position)
            val intent = Intent(this,FetchEProductsActivity::class.java )
            intent.putExtra("BRAND",tappedBrand)
            startActivity(intent)


        }




    }


}