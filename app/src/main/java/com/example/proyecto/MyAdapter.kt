package com.example.proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val datos: Array<Array<String?>>, private val myListener: MyListener):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemSensor: View): RecyclerView.ViewHolder(itemSensor) {
        lateinit var tvSensorUno: TextView
        lateinit var tvValorUno: TextView
        lateinit var tvOpUno: TextView
        lateinit var tvSensorDos: TextView
        lateinit var tvValorDos: TextView
        lateinit var tvOpDos: TextView
        lateinit var tvFecha: TextView
        lateinit var bEdit: ImageView
        lateinit var bDel: ImageView
        init {
            tvSensorUno = itemSensor.findViewById(R.id.tvSensorUno)
            tvValorUno = itemSensor.findViewById(R.id.tvValorUno)
            tvOpUno = itemSensor.findViewById(R.id.tvOpUno)
            tvSensorDos = itemSensor.findViewById(R.id.tvSensorDos)
            tvValorDos = itemSensor.findViewById(R.id.tvValorDos)
            tvOpDos = itemSensor.findViewById(R.id.tvOpDos)
            tvFecha = itemSensor.findViewById(R.id.tvFecha)

            bDel = itemSensor.findViewById(R.id.bDel)

            bDel.setOnClickListener { myListener.onClickDel(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemSensor: View = LayoutInflater.from(parent.context).
        inflate(R.layout.item_sensor, parent, false)
        return ViewHolder(itemSensor)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvSensorUno.text = datos[position][2]
        holder.tvValorUno.text = datos[position][3]
        holder.tvOpUno.text = datos[position][4]
        holder.tvSensorDos.text = datos[position][5]
        holder.tvValorDos.text = datos[position][6]
        holder.tvOpDos.text = datos[position][7]
        holder.tvFecha.text = datos[position][8]
    }

    override fun getItemCount(): Int {
        return datos.size
    }

}