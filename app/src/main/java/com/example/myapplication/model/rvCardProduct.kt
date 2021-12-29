package com.example.myapplication.model

class rvCardProduct {
    var id:Int
    var name:String
    var price:Int
    var email:String
    var amount:Int
    var picture:String

    constructor(id: Int, name:String, price:Int, email: String, amount:Int, picture:String){
        this.id=id
        this.name=name
        this.price=price
        this.email=email
        this.amount=amount
        this.picture=picture
    }
}