package com.example.aplicaoredesesensores

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var b_emparelhar: Button
    lateinit var b_subir: Button
    lateinit var b_descer:Button
    lateinit var t_texto : TextView
    lateinit var b_pausa: Button
    lateinit var  listaDispositivos : RecyclerView
    var request = 1
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       ativarBluetooth(bluetoothAdapter)


        listaDispositivos = lista_dispositivos_disponiveis
        b_emparelhar = button_emparelhar
        b_subir = button_subir
        b_descer = button_descer
        b_pausa = button_pausa
        t_texto = textview

        b_subir.visibility = View.INVISIBLE
        b_descer.visibility = View.INVISIBLE
        b_pausa.visibility = View.INVISIBLE
        listaDispositivos.visibility = View.INVISIBLE

        b_emparelhar.setOnClickListener {
            Toast.makeText(this, "Aparelho Encontrado !", Toast.LENGTH_SHORT).show()
            listaDispositivos.visibility = View.VISIBLE




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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "bluetooth ativado", Toast.LENGTH_LONG).show()
            } else {
                ativarBluetooth(bluetoothAdapter)
            }
        }
    }

    fun ativarBluetooth(bluetoothAdapter: BluetoothAdapter?) {

        if (this.bluetoothAdapter == null) {
            Toast.makeText(this, "Nao suporta o bluetooth", Toast.LENGTH_LONG).show()
        }
        else {

            if (this.bluetoothAdapter?.isEnabled == false) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, request)
            }
        }
    }


}