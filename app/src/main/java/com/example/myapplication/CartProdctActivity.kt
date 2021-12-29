package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.Adapter.RvCardAdapter
import com.example.myapplication.databinding.ActivityCartProdctBinding
import com.example.myapplication.model.rvCardProduct

class CartProdctActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCartProdctBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartProdctBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cartProductUrl = "http://192.168.1.130/onLineSotreApp/fetch_temporary_order.php?email=${Person.email}"
        var cartProductsList = ArrayList<rvCardProduct>()
        var requestQ = Volley.newRequestQueue(this)
        var jsonAR = JsonArrayRequest(Request.Method.GET,cartProductUrl,null, {
            response ->

            for (joIndex in 0.until(response.length()) ){

                // id,name,price,email,ammount

//                cartProductsList.add("${response.getJSONObject(joIndex).getInt("id")} " ,${response.getJSONObject(joIndex).getString("name")} " +
//                        "\n  ${response.getJSONObject(joIndex).getInt("price")}" +
//                        " ${response.getJSONObject(joIndex).getString("email")} \n" +
//                        " ${response.getJSONObject(joIndex).getInt("amount")} " )

                cartProductsList.add(
                    rvCardProduct(response.getJSONObject(joIndex).getInt("id"),
                        response.getJSONObject(joIndex).getString("name"),
                     response.getJSONObject(joIndex).getInt("price"),
                   response.getJSONObject(joIndex).getString("email"),
                    response.getJSONObject(joIndex).getInt("amount"),
                    response.getJSONObject(joIndex).getString("picture"))
                )
            }

            var CardAdapter = RvCardAdapter(this,cartProductsList)
            binding.cardRecycyleView.layoutManager = LinearLayoutManager(this)
            binding.cardRecycyleView.adapter = CardAdapter
        }, {
            error ->
        })

        requestQ.add(jsonAR)







    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.card_menu,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item?.itemId == R.id.continueShoppingItem){

            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }else if(item?.itemId == R.id.declineOrderItem){

            var deleteUrl = "http://192.168.1.130/onLineSotreApp/decline_order.php?email=${Person.email}"
            var requestQ =Volley.newRequestQueue(this)
            var stringRequest = StringRequest(Request.Method.GET,deleteUrl, {
                response ->

                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                
            }, {
                error ->
            })

            requestQ.add(stringRequest)

        }else if(item?.itemId== R.id.verifyOrderItem){

            var verifyOrderUrl = "http://192.168.1.130/onLineSotreApp/verify_order.php?email=${Person.email}"
            var requestQ =Volley.newRequestQueue(this)
            var stringRequest = StringRequest(Request.Method.GET,verifyOrderUrl, {
                    response ->

                var intent = Intent(this,FinalizeShopingActivity::class.java)
                Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
                intent.putExtra("LATEST_INVOICE_NUMBER",response)
                startActivity(intent)

            }, {
                    error ->
            })

            requestQ.add(stringRequest)

        }
        return super.onOptionsItemSelected(item)
    }
}


