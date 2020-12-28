package com.example.aplicaoredesesensores

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var b_emparelhar: Button
    lateinit var b_subir: Button
    lateinit var b_descer: Button
    lateinit var t_Disponiveis: TextView
    lateinit var t_Pareados: TextView
    lateinit var b_pausa: Button
    lateinit var listaDispositivos: ListView
    lateinit var listaPareados: ListView
    var request = 1
    var list = ArrayList<Dispositivos>()
    var listDispositivos = ArrayList<Dispositivos>()
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ativarBluetooth(bluetoothAdapter)




        listaDispositivos = lista_dispositivos_disponiveis
        listaPareados = lista_dispositivos_pareados
        b_emparelhar = button_emparelhar
        b_subir = button_subir
        b_descer = button_descer
        b_pausa = button_pausa
        t_Disponiveis = dispositivos_Disponiveis
        t_Pareados = Dispositivos_pareados

        b_subir.visibility = View.INVISIBLE
        b_descer.visibility = View.INVISIBLE
        b_pausa.visibility = View.INVISIBLE
        listaDispositivos.visibility = View.INVISIBLE
        t_Pareados.visibility = View.INVISIBLE
        t_Disponiveis.visibility = View.INVISIBLE

        b_emparelhar.setOnClickListener {
            Toast.makeText(this, "Aparelho Encontrado !", Toast.LENGTH_SHORT).show()


            listaDispositivos.visibility = View.VISIBLE
            t_Pareados.visibility = View.VISIBLE
            t_Disponiveis.visibility = View.VISIBLE
            b_subir.visibility = View.VISIBLE
            b_descer.visibility = View.VISIBLE
            b_emparelhar.visibility = View.INVISIBLE
            b_pausa.visibility = View.VISIBLE

            consultaDispositivos()
            descobrir()



            ListaPareados()

            ListaDispositivos()

        }

        b_subir.setOnClickListener {

        }

        b_descer.setOnClickListener {

        }

        b_pausa.setOnClickListener {

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
        } else {

            if (this.bluetoothAdapter?.isEnabled == false) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, request)
            }
        }
    }

    fun consultaDispositivos() {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
            list.add(Dispositivos(deviceName, deviceHardwareAddress))


        }
    }

    fun descobrir() {
        // Register for broadcasts when a device is discovered.
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, filter)
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            when (action) {
                BluetoothDevice.ACTION_FOUND -> {
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device!!.name
                    val deviceHardwareAddress = device!!.address // MAC address
                    listDispositivos.add(Dispositivos(deviceName, deviceHardwareAddress))
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()


        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(receiver)
    }

    fun ListaDispositivos(){
        listaDispositivos.adapter = DispositivosAdapter(this, R.layout.row, listDispositivos)
        listaDispositivos.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }
        }
    }

    fun ListaPareados() {
        listaPareados.adapter = DispositivosAdapter(this, R.layout.row, list)
        listaPareados.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }


        }
    }


}