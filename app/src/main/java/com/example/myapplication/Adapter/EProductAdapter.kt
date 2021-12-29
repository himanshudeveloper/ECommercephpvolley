package com.example.myapplication.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.AmountFragment
import com.example.myapplication.model.EProduct
import com.example.myapplication.Person
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class EProductAdapter (var context: Context,var arrayList: ArrayList<EProduct> ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val productView = LayoutInflater.from(context).inflate(R.layout.e_product_row,parent,false);

        return ProductViewHolder(productView)



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ProductViewHolder).initializeRowUiComponents(arrayList.get(position).id,arrayList.get(position).name,arrayList.get(position).price,arrayList.get(position).picture)

    }

    override fun getItemCount(): Int {
        return arrayList.size

    }

    inner class ProductViewHolder(pView : View): RecyclerView.ViewHolder(pView) {
        var txtId=pView.findViewById<TextView>(R.id.txtid)
        var txtName=pView.findViewById<TextView>(R.id.txtName)
        var txtprice=pView.findViewById<TextView>(R.id.txtprice)
        var imgProduct =pView.findViewById<ImageView>(R.id.imgProdct)
        var imgAdd  =pView.findViewById<ImageView>(R.id.imgAdd)

        fun initializeRowUiComponents (id:Int,name:String,price : Int , picName:String){

            txtId.text = id.toString()
            txtName.text = name
            txtprice.text =price.toString()

            var picUrl = "http://192.168.1.130//onLineSotreApp/osimages/"

            picUrl = picUrl.replace(" ","%20")

            Picasso.get().load(picUrl+picName).into(imgProduct)

            imgAdd.setOnClickListener {

                Person.addToCardProductId = id
                var amountFragment = AmountFragment()
                var fragmentManager = (itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager,"TAG")

            }

        }
    }

}