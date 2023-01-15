package com.nadim.atlaspackaging.archive.presentation

import android.os.Environment
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.nadim.atlaspackaging.models.ProductionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class ArchiveScreenViewModel @Inject constructor(
    @Named("db") private val db: FirebaseDatabase,
) : ViewModel() {
    var data  by mutableStateOf(ProductionData())

    var selectedData by mutableStateOf(ProductionData())

    var dataToBeDeleted by mutableStateOf(ProductionData())

    private val _search = mutableStateOf("")
    val search : State <String> = _search

    private val _searchType = mutableStateOf("client")
    val searchType : State <String> = _searchType

    private var _dataList = mutableStateListOf<ProductionData>()
    val dataList: List<ProductionData> = _dataList

    private var _secondaryDataList = mutableStateListOf<ProductionData>()

    fun setSelectedDataValue(data: ProductionData){
        selectedData = data
    }

    val openExcelFile = mutableStateOf(false)

    fun downloadData(machine: String) {
        _dataList.clear()
        _secondaryDataList.clear()
        data = ProductionData(machine = machine)
        viewModelScope.launch (Dispatchers.IO){
            db.getReference(machine).keepSynced(true)
            db.getReference(machine)
                .addValueEventListener( object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { child ->
                        child.children.forEach { thisData ->
                            when (thisData.key) {
                                "date" -> data = data.copy(date = thisData.value.toString())
                                "conductor" -> data = data.copy(conductor = thisData.value.toString())
                                "post" -> data = data.copy(post = thisData.value.toString())
                                "client" -> data = data.copy(client = thisData.value.toString())
                                "article" -> data = data.copy(article = thisData.value.toString())
                                "lot" -> data = data.copy(lot = thisData.value.toString())
                                "secondaryLot" -> data = data.copy(secondaryLot = thisData.value.toString())
                                "production" -> data = data.copy(production = thisData.value.toString())
                                "waste" -> data = data.copy(waste = thisData.value.toString())
                                "time" -> data = data.copy(time = thisData.value.toString())
                                "commentary" -> data = data.copy(commentary = thisData.value.toString())
                            }
                        }
                        _dataList.add(data)
                        _secondaryDataList.add(data)
                        createExcel()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.i("DatabaseError", error.message)
                }
            })
        }
    }

    fun setSearchType(text: String) {
        _searchType.value = text
    }

    fun setSearchValue(search: String){
        _search.value = search
    }

    fun onSearchValueChange() {
        _dataList.clear()
        _secondaryDataList.forEach {
            val searchData = when (_searchType.value){
                "client" -> it.client
                "article" ->it.article
                "date" -> it.date
                "lot" -> it.lot
                "conductor" -> it.conductor
                else -> ""
            }
            if (searchData.lowercase()
                    .replace(" ","")
                    .replace("-","")
                    .replace("é","e")
                    .contains(_search.value.lowercase()
                        .replace(" ","")
                        .replace("-","")
                        .replace("é","e")
                    )){
                if (it !in _dataList){
                    _dataList.add(it)
                }
            }
            createExcel()
        }

    }

    fun setItemToBeDeleted(data: ProductionData){
        dataToBeDeleted = data
    }

    fun deleteData(machine: String){
        db.getReference(machine).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    if (
                        it.child("article").value == dataToBeDeleted.article
                        && it.child("client").value == dataToBeDeleted.client
                        && it.child("date").value == dataToBeDeleted.date
                        && it.child("lot").value == dataToBeDeleted.lot
                        && it.child("secondaryLot").value == dataToBeDeleted.secondaryLot
                    ){
                        db.getReference(machine).child(it.key.toString()).removeValue()
                        _dataList.clear()
                        _secondaryDataList.clear()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("DatabaseError", error.message)
            }
        })
    }

    private fun createExcel(){
        val wb = HSSFWorkbook()
        val sheet = wb.createSheet("Archive")
        val headerRow = sheet.createRow(2)

        // Create the header at row number 2
        val cellMachineHeader = headerRow.createCell(1)
        cellMachineHeader.setCellValue("Machine")
        val cellDateHeader = headerRow.createCell(2)
        cellDateHeader.setCellValue("Date")
        val cellConductorHeader = headerRow.createCell(3)
        cellConductorHeader.setCellValue("Conductor")
        val cellPostHeader = headerRow.createCell(4)
        cellPostHeader.setCellValue("Post")
        val cellClientHeader = headerRow.createCell(5)
        cellClientHeader.setCellValue("Client")
        val cellArticleHeader = headerRow.createCell(6)
        cellArticleHeader.setCellValue("Article")
        val cellLotHeader = headerRow.createCell(7)
        cellLotHeader.setCellValue("Lot")
        val cellProductionHeader = headerRow.createCell(8)
        cellProductionHeader.setCellValue("Production")
        val cellWasteHeader = headerRow.createCell(9)
        cellWasteHeader.setCellValue("Waste")
        val cellTimeHeader = headerRow.createCell(10)
        cellTimeHeader.setCellValue("Time")
        val cellCommentaryHeader = headerRow.createCell(11)
        cellCommentaryHeader.setCellValue("Commentary")

        // iterate through data list and pass its values to the proper column
        for (r in 0..dataList.lastIndex){
            val row = sheet.createRow(r+3)
            val cellMachine = row.createCell(1)
            cellMachine.setCellValue(dataList[r].machine)
            val cellDate = row.createCell(2)
            cellDate.setCellValue(dataList[r].date)
            val cellConductor = row.createCell(3)
            cellConductor.setCellValue(dataList[r].conductor)
            val cellPost = row.createCell(4)
            cellPost.setCellValue(dataList[r].post)
            val cellClient = row.createCell(5)
            cellClient.setCellValue(dataList[r].client)
            val cellArticle = row.createCell(6)
            cellArticle.setCellValue(dataList[r].article)
            val cellLot = row.createCell(7)
            cellLot.setCellValue("AP:${dataList[r].lot}-${dataList[r].secondaryLot}")
            val cellProduction = row.createCell(8)
            cellProduction.setCellValue(dataList[r].production)
            val cellWaste = row.createCell(9)
            cellWaste.setCellValue(dataList[r].waste)
            val cellTime = row.createCell(10)
            cellTime.setCellValue(dataList[r].time)
            val cellCommentary = row.createCell(11)
            cellCommentary.setCellValue(dataList[r].commentary)
        }

        val filePath =  File("${Environment.getExternalStorageDirectory()}/Download/Archive.xls")
        if (!filePath.exists()){
            filePath.createNewFile()
        }
        val fileOutputStream = FileOutputStream(filePath)
        wb.write(fileOutputStream)

        fileOutputStream.flush()
        fileOutputStream.close()
    }

    fun onDownloadIconClicked(){
        openExcelFile.value = true
    }

}