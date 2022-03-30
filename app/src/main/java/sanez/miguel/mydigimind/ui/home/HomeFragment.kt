package sanez.miguel.mydigimind.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recordatorio.view.*
import sanez.miguel.mydigimind.Carrito
import sanez.miguel.mydigimind.R
import sanez.miguel.mydigimind.Recordatorio

class HomeFragment : Fragment() {
    private lateinit var homeViewModel:HomeViewModel
    private var adapter: RecordatorioAdapter? = null
    companion object{
        var recordatorio = ArrayList<Recordatorio>()
        var first = true
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home,container,false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer{

        })
        if(first){
            fillTask()
            first = false
        }
        adapter = RecordatorioAdapter(root.context, recordatorio)
        val table : GridView = root.findViewById(R.id.gridView)
        table.adapter = adapter

        return root
    }

    fun fillTask(){
        recordatorio.add(Recordatorio(arrayListOf("Tuesday"),"17:30","Tarea 1"))
        recordatorio.add(Recordatorio(arrayListOf("Monday","Tuesday"),"17:30","Tarea 2"))
        recordatorio.add(Recordatorio(arrayListOf("Wednesday"),"17:30","Tarea 3"))
        recordatorio.add(Recordatorio(arrayListOf("Wednesday"),"17:30","Tarea 4"))
        recordatorio.add(Recordatorio(arrayListOf("Friday"),"17:30","Tarea 5"))
        recordatorio.add(Recordatorio(arrayListOf("Wednesday"),"17:30","Tarea 6"))
    }
    class RecordatorioAdapter: BaseAdapter {
        var recordatorio = ArrayList<Recordatorio>()
        var context: Context? = null

        constructor(context: Context?, recordatorio: ArrayList<Recordatorio>){
            this.context = context
            this.recordatorio = recordatorio
        }
        override fun getCount(): Int {
            return recordatorio.size
        }

        override fun getItem(p0: Int): Any {
            return recordatorio[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var rec = recordatorio[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.recordatorio, null)
            vista.txtDiasRecordatorio.text = rec.dias.toString()
            vista.txtNombreRecordatorio.text = rec.nombre.toString()
            vista.txtTiempoRecordatorio.text = rec.tiempo.toString()
            return vista
        }

    }
}