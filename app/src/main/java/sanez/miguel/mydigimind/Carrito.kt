package sanez.miguel.mydigimind

import java.io.Serializable

class Carrito: Serializable {
    var recordatorio = ArrayList<Recordatorio>()
    fun agregar(p: Recordatorio):Boolean{
        return recordatorio.add(p)
    }
}