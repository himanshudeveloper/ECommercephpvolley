
package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val url =   "http://192.168.1.130/onLineSotreApp/login_app_user.php?email="+binding.edtLoginemail.text.toString()+
                    "&password="+binding.edtLoginPassword.text.toString()

            val  requestQ = Volley.newRequestQueue(this)

            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                if (response.equals("The user does not exists")){
                    Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
                }else{
                    Person.email = binding.edtLoginemail.text.toString()
                    Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
                    val Home_loginIntent = Intent(this,MainActivity::class.java)
                    startActivity(Home_loginIntent)
                }



            }, { error ->
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("message")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()

            })

           requestQ.add(stringRequest)




        }

        binding.btnSignup.setOnClickListener {

            val intent = Intent(this, SignUPActivity::class.java)

            startActivity(intent)
            finish()
        }
    }
}