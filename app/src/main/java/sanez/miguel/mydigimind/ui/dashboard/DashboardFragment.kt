package sanez.miguel.mydigimind.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_dashboard.*
import sanez.miguel.mydigimind.R
import sanez.miguel.mydigimind.Recordatorio
import sanez.miguel.mydigimind.Time

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard,container,false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tiempo.setOnClickListener {
            showTimePickerDialog()
        }
        aceptar.setOnClickListener {
            var name = takemypill.text
            var days = ""
            var time = time.text.toString()
            if( monday.isChecked) {
                days+= "Mon"
            }
            if(tuesday.isChecked) {
                days += if (days.isNotEmpty()){
                    ",Tue"
                }else
                    "Tue"
            }
            if(wednesday.isChecked) {
                days+= if(days.isNotEmpty()){
                    ",Wed"
                }else "Wed"
            }
            if(thursday.isChecked) {
                days+= if(days.isNotEmpty()){
                    ",Thu"
                }else
                    "Thu"
            }
            if(friday.isChecked) {
                days+= if(days.isNotEmpty()){
                    ",Fri"
                }else "Fri"
            }
            if(saturday.isChecked) {
                days+= if(days.isNotEmpty()){
                    ",Sat"
                }else "Sat"
            }
            if(sunday.isChecked){
                days+= if(days.isNotEmpty()){
                    ",Sun"
                }else "Sun"
            }
            if(monday.isChecked && tuesday.isChecked && wednesday.isChecked && thursday.isChecked && friday.isChecked &&
                saturday.isChecked && sunday.isChecked) {
                days="Everyday"
            }

            var recordatorio = Recordatorio(days,time,name)
            val bundle = Bundle()
            bundle.putSerializable("recordatorio",recordatorio)
            clean()
            setFragmentResult("key",bundle)

        }
    }
    private fun showTimePickerDialog(){
        val timePicker = Time{onTimeSelected(it)}
        timePicker.show(parentFragmentManager,"time")
    }
    private fun onTimeSelected(time:String){
        tiempo.text = time
    }
    private fun clean(){
        takemypill.setText("")
        monday.isChecked =false
        tuesday.isChecked =false
        wednesday.isChecked =false
        thursday.isChecked =false
        friday.isChecked =false
        saturday.isChecked =false
        sunday.isChecked =false
        tiempo.text = ""


    }
}