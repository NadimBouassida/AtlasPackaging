package com.nadim.atlaspackaging.daily_production.presentation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.nadim.atlaspackaging.models.ProductionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.reflect.full.memberProperties


@HiltViewModel
class DailyProductionViewModel @Inject constructor (
    @Named("db") private val db: FirebaseDatabase
    ) : ViewModel() {

    private var _machine = ""

    var dataState by mutableStateOf(ProductionData())
    private val calendar: Calendar = Calendar.getInstance()
    private val datePicker = DatePicker(calendar = calendar)
    private val _date = mutableStateOf("")
    val date: State<String> = _date
    private val _conductorsList = mutableStateListOf<String?>()
    val conductorsList: List<String?> = _conductorsList
    private val _clientsList = mutableStateListOf<String>()
    val clientList: List<String> = _clientsList
    private val _articlesList = mutableStateListOf<String>()
    val articlesList: List<String> = _articlesList

    fun setMachineName(machine: String){
        _machine = machine
    }

    fun onEvent(event: Events, string: String){
        when(event){
            is Events.DateChange -> dataState = dataState.copy(date = _date.value )
            is Events.ConductorChange -> dataState = dataState.copy(conductor = string )
            is Events.PostChange -> dataState = dataState.copy(post = string)
            is Events.ArticleChange -> dataState = dataState.copy(article = string)
            is Events.ClientChange -> dataState = dataState.copy(client = string)
            is Events.LotChange -> dataState = dataState.copy(lot = string)
            is Events.ProductionChange -> dataState = dataState.copy(production = string)
            is Events.WasteChange -> dataState = dataState.copy(waste = string)
            is Events.CommentaryChange -> dataState = dataState.copy(commentary = string)
            is Events.Submit -> onSubmitClicked()
        }
    }

    fun getDatePicker(context: Context): DatePickerDialog {
        return datePicker.getDatePickerDialog(context = context,_date)
    }

    fun createConductorsList(machine: String?){
        _conductorsList.addAll(identifyConductors(machine = machine))
    }

    private fun identifyConductors(machine: String?) : List<String>{
        return when(machine){
            "Flexo" -> listOf("Hamdi", "Youssef")
            "Sealer" -> listOf("Thabet", "Nadim", "Nessrine")
            "Découpe1" -> listOf("Saif", "Anoir", "Mohammed", "Bilel")
            "Découpe2" -> listOf("Saif", "Anoir", "Mohammed", "Bilel")
            "Découpe3" -> listOf("Saif", "Anoir", "Mohammed", "Bilel")
            else -> listOf("")
        }
    }

    fun getClientsList() {
        _clientsList.clear()
        viewModelScope.launch (Dispatchers.IO) {
            db.getReference("clients").keepSynced(true)
            db.getReference("clients")
                .addValueEventListener(object : ValueEventListener  {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach{ dbClient ->
                        _clientsList.add(dbClient.key.toString())
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.i("getArticle:onCancelled",error.message)
                }
            })

        }
    }

    fun getArticlesList(client: String){
        if (client.isBlank()){
            Log.i("ClientsSection","clients section need to be filled first!")
            return
        }
        _articlesList.clear()
        viewModelScope.launch(Dispatchers.IO) {
            db.getReference("clients").keepSynced(true)
            db.getReference("clients")
                .addValueEventListener(object : ValueEventListener  {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach{ dbClient ->
                        if (client in dbClient.key.toString()){
                            dbClient.children.forEach { article ->
                                _articlesList.add(article.value.toString())
                            }
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.i("getArticle:onCancelled",error.message)
                }
            })
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun onSubmitClicked(){
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val date = Date().time
        val time = sdf.format(date).toString()
        onEvent(Events.DateChange,_date.value)
        dataState = dataState.copy(
            time = time,
            date = dataState.date.replace("/", "-")
        )
        val dataList = mutableStateListOf<String>()
        ProductionData::class.memberProperties.forEach { member ->
            val value = member.get(dataState) as String
            dataList.add(value)
        }

        val hasEmptyField = dataList.toList().any {
            it.isBlank()
        }
        Log.i("ProductionDataList",dataList.toList().toString())
        if (!hasEmptyField){
            viewModelScope.launch(Dispatchers.IO) {
                db.getReference(_machine)
                    .push()
                    .setValue(dataState).addOnSuccessListener {
                    clearAllItems()
                    _date.value = ""
                }
            }
        }
    }

    private fun clearAllItems() {
        dataState = ProductionData()
    }

}