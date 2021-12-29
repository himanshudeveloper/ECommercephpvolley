package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.rvCardProduct
import com.squareup.picasso.Picasso

class RvCardAdapter(var context: Context,var cardArrayList: ArrayList<rvCardProduct>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val CardProductView = LayoutInflater.from(context).inflate(R.layout.row_card,parent,false)

        return CardProductViewHolder(CardProductView)



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as CardProductViewHolder).initializeRowUiComponents(cardArrayList.get(position).id,cardArrayList.get(position).name,cardArrayList.get(position).price,cardArrayList.get(position).picture)

    }

    override fun getItemCount(): Int {
      return   cardArrayList.size


    }


    inner class CardProductViewHolder(CView:View):RecyclerView.ViewHolder(CView){
        var CardImage = CView.findViewById<ImageView>(R.id.cardRowImage)
        var txtName = CView.findViewById<TextView>(R.id.txtCardName)
        var txtPrice = CView.findViewById<TextView >(R.id.txtCardPrice)

        fun initializeRowUiComponents(id:Int,name:String,price:Int,cardpicName:String){

            txtName.text = name
            txtPrice.text = price.toString()

            var CardPicUrl = "http://192.168.1.130//onLineSotreApp/osimages/"

            CardPicUrl = CardPicUrl.replace(" ","%20")

            Picasso.get().load(CardPicUrl+cardpicName).into(CardImage)

        }
    }


}