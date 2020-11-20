package com.example.aplicaoredesesensores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var b_emparelhar: Button
    lateinit var b_subir: Button
    lateinit var b_descer:Button
    lateinit var t_texto : TextView
    lateinit var b_pausa: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b_emparelhar = button_emparelhar
        b_subir = button_subir
        b_descer = button_descer
        b_pausa = button_pausa
        t_texto = textview

        b_subir.visibility = View.INVISIBLE
        b_descer.visibility = View.INVISIBLE
        b_pausa.visibility = View.INVISIBLE

        b_emparelhar.setOnClickListener {
            Toast.makeText(this, "Aparelho Encontrado !", Toast.LENGTH_SHORT).show()
            b_subir.visibility = View.VISIBLE
            b_descer.visibility = View.VISIBLE
            b_emparelhar.visibility = View.INVISIBLE
            b_pausa.visibility = View.VISIBLE

        }

        b_subir.setOnClickListener {
            textview.setText("Está a subir!!!")
        }

        b_descer.setOnClickListener {
            textview.setText("Está a descer!!!")
        }

        b_pausa.setOnClickListener {
            textview.setText("Parada!!!")
        }

    }


}