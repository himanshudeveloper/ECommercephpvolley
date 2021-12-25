package com.example.myapplication

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EProductAdapter (var context: Context,var arrayList: ArrayList<EProduct> ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }

    inner class ProductViewHolder(pView : View): RecyclerView.ViewHolder(pView) {
        var txtId=pView.findViewById<TextView>(R.id.txtid)
        var txtName=pView.findViewById<TextView>(R.id.txtName)
        var txtprice=pView.findViewById<TextView>(R.id.txtprice)

        fun initializeRowUiComponents (id:Int,name:String,price : Int , picName:String){

            txtId.text = id.toString()
            txtName.text = name
            txtprice.text =price.toString()

            var picUrl = "http://192.168.1.130//onLineSotreApp/osimages/"

            picUrl = picUrl.replace(" ","%20")










        }
    }

}