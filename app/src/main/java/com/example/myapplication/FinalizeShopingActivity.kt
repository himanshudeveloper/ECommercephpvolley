package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityFinalizeShopingBinding
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import java.math.BigDecimal

class FinalizeShopingActivity : AppCompatActivity() {

    var ttPrice: Long = 0
    private lateinit var binding: ActivityFinalizeShopingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalizeShopingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var calculateTotalPriceUrl = "http://192.168.1.130/onLineSotreApp/calculate_order_price.php?Invoice_num=${intent.getStringExtra("LATEST_INVOICE_NUMBER")}"
        var requestQ = Volley.newRequestQueue(this)
        var stringRequest = StringRequest(Request.Method.GET,calculateTotalPriceUrl, {
            response ->
            binding.btnPaymentProcessing.text = "Pay$ $response via paypal Now"
            ttPrice = response.toLong()


        }, {Response.ErrorListener { 
            error ->

        }
        })

        requestQ.add(stringRequest)

        var paypalConfig : PayPalConfiguration = PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(MyPayPal.clientId)

        var ppService = Intent(this,PayPalService::class.java)
        ppService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalConfig)
        startService(ppService)

        binding.btnPaymentProcessing.setOnClickListener {

            var ppProcessing = PayPalPayment(BigDecimal.valueOf(ttPrice),"USD","Online store Kotlin",PayPalPayment.PAYMENT_INTENT_SALE)
            var paypalPaymentIntent = Intent(this,PaymentActivity::class.java)
            paypalPaymentIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalConfig)
            paypalPaymentIntent.putExtra(PaymentActivity.EXTRA_PAYMENT,ppProcessing)
            startActivityForResult(paypalPaymentIntent,1002)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1002){
            if (requestCode == RESULT_OK){
                var intent = Intent(this,ThankYOuActivity::class.java)
                startActivity(intent)

            }  else{
                Toast.makeText(this,"Sorry! went wrong, try again",Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        stopService(Intent(this,PayPalService::class.java))
    }
}