package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivitySignUpactivityBinding

class SignUPActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            if (binding.edtSignUpPassword.text.toString().equals(binding.edtSignUpConfiremPassword.text.toString())){


                // Registratiomn process

                val signUPUrl = "http://192.168.1.130/onLineSotreApp/join_new_user.php?email="+
                        binding.edtSignUpemail.text.toString()+"&username="+binding.edtSignUpUsername.text.toString()+
                        "&password="+binding.edtSignUpPassword.text.toString()

                val requestQ = Volley.newRequestQueue(this)

                val stringRequest = StringRequest(Request.Method.GET,signUPUrl, { response ->
                    if (response.equals("user with same email already exits")) {
                        Toast.makeText(this,response,Toast.LENGTH_SHORT).show()

                    }else{
                        Person.email = binding.edtSignUpemail.text.toString()
                        Toast.makeText(this,response,Toast.LENGTH_LONG).show()
                        val homeIntent = Intent(this,MainActivity::class.java)
                        startActivity(homeIntent)
                    }

                }, { error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()

                })
                requestQ.add(stringRequest)

            }else{
                val dialogBuilder = AlertDialog.Builder(this);
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("Password mismatch")
                dialogBuilder.create().show()

            }
        }
        binding.btnLogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
            finish()
        }
    }
}