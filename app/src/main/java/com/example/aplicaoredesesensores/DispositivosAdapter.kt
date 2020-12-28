package com.example.aplicaoredesesensores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DispositivosAdapter (var mCtx: Context, var resource:Int, var items:List<Dispositivos>)
    : ArrayAdapter<Dispositivos>( mCtx , resource , items ){




    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        var mac : TextView = view.findViewById(R.id.Mac)
        var name : TextView = view.findViewById(R.id.Nome)



        var person : Dispositivos = items[position]


        name.text = person.nome
        mac.text = person.mac



        return view
    }


}