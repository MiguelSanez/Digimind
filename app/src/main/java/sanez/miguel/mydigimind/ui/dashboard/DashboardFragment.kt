package sanez.miguel.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_dashboard.*
import sanez.miguel.mydigimind.R
import sanez.miguel.mydigimind.Recordatorio
import sanez.miguel.mydigimind.Time
import sanez.miguel.mydigimind.databinding.FragmentDashboardBinding
import sanez.miguel.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val btnSetTime: Button = root.findViewById(R.id.tiempo) as Button
        val btnAdd = root.findViewById(R.id.aceptar) as Button
        val toDo = root.findViewById(R.id.takemypill) as EditText
        val check_monday = root.findViewById(R.id.monday) as CheckBox
        val check_tuesday = root.findViewById(R.id.tuesday) as CheckBox
        val check_wednesday = root.findViewById(R.id.wednesday) as CheckBox
        val check_thursday = root.findViewById(R.id.thursday) as CheckBox
        val check_friday = root.findViewById(R.id.friday) as CheckBox
        val check_saturday = root.findViewById(R.id.saturday) as CheckBox
        val check_sunday = root.findViewById(R.id.sunday) as CheckBox

        btnSetTime.setOnClickListener(){
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btnSetTime.text= SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),true).show()
        }

        btnAdd.setOnClickListener{
            var descriptionToDo = toDo.text.toString()
            var time = btnSetTime.text.toString()
            var days = ArrayList<String>()

            if(check_monday.isChecked){
                days.add("Monday")
            }
            if(check_tuesday.isChecked){
                days.add("Tuesday")
            }
            if(check_wednesday.isChecked){
                days.add("Wednesday")
            }
            if(check_thursday.isChecked){
                days.add("Thursday")
            }
            if(check_friday.isChecked){
                days.add("Friday")
            }
            if(check_saturday.isChecked){
                days.add("Saturday")
            }
            if(check_sunday.isChecked){
                days.add("Sunday")
            }

            var recordatorio = Recordatorio(days,time,descriptionToDo)

            HomeFragment.recordatorio.add(recordatorio)
            Toast.makeText(root.context,"New task added", Toast.LENGTH_SHORT).show()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}