package com.example.a215_ic_projekt_szymon.admin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a215_ic_projekt_szymon.DatabaseHandler
import com.example.a215_ic_projekt_szymon.MyListAdapter
import com.example.a215_ic_projekt_szymon.R
import com.example.a215_ic_projekt_szymon.database.Kierunki

class AdminPanel : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_panel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backFromAdminBtn: Button = findViewById(R.id.backFromAdminBtn)
        val addNewFieldOfStudyBtn: Button = findViewById(R.id.addNewFieldOfStudyBtn)
        val editRecordBtn: Button = findViewById(R.id.editRecordBtn)
        val deleteRecordBtn: Button = findViewById(R.id.deleteRecordBtn)

        viewRecord()
        backFromAdminBtn.setOnClickListener {
            finish()
        }
        addNewFieldOfStudyBtn.setOnClickListener {
            saveRecord()
        }
        editRecordBtn.setOnClickListener {
            updateRecord()
        }
        deleteRecordBtn.setOnClickListener {
            deleteRecord()
        }

    }

    //method for saving records in database
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    fun saveRecord() {

        val i_department = findViewById<EditText>(R.id.departmentField)
        val i_fieldOfStudy = findViewById<EditText>(R.id.fieldOfStudyField)
        val i_typeOfstudy = findViewById<Switch>(R.id.typeSwitch)
        val i_description = findViewById<EditText>(R.id.descriptionField)

        val department = i_department.text.toString()
        val fieldOfStudy = i_fieldOfStudy.text.toString()
        var typeOfstudy = "Wojskowe"
        if (!i_typeOfstudy.isChecked) {
            typeOfstudy = "Cywilne"
        }
        val description = i_description.text.toString()

        val databaseHandler = DatabaseHandler(this)

        if (department.trim() != "" && fieldOfStudy.trim() != "" && description.trim() != "") {
            val status =
                databaseHandler
                    .addFieldOfStudy(
                        Kierunki(id = 0, department, fieldOfStudy, typeOfstudy, description, 0)
                    )

            if (status > -1) {
                Toast.makeText(applicationContext, "record save", Toast.LENGTH_LONG).show()
                i_department.text.clear()
                i_fieldOfStudy.text.clear()
                i_typeOfstudy.isChecked = false
                i_description.text.clear()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Błąd podczas zapisywania rekordu",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Wypełnij wszystkie pola",
                Toast.LENGTH_LONG
            ).show()
        }
        viewRecord()
    }


    //    //method for read records from database in ListView
    private fun viewRecord() {

        //creating the instance of DatabaseHandler class
        val databaseHandler = DatabaseHandler(this)

        //calling the viewEmployee method of DatabaseHandler class to read the records
        val fos: List<Kierunki> = databaseHandler.viewFieldOfStudy()
        val fosArrayId = Array(fos.size) { "0" }
        val fosArrayDepartment = Array(fos.size) { "null" }
        val fosArrayFieldOfStudy = Array(fos.size) { "null" }
        val fosArrayType = Array(fos.size) { "null" }
        val fosArrayDescription = Array(fos.size) { "null" }
        val fosArrayIsFavourite = Array(fos.size) { "null" }
        var index = 0

        for (e in fos) {
            fosArrayId[index] = e.id.toString()
            fosArrayDepartment[index] = e.wydzial
            fosArrayFieldOfStudy[index] = e.kierunek
            fosArrayType[index] = e.rodzaj
            fosArrayDescription[index] = e.opis
            fosArrayIsFavourite[index] = e.czyUlubiony.toString()
            index++
        }

        //creating custom ArrayAdapter
        val myListAdapter = MyListAdapter(
            this,
            fosArrayId,
            fosArrayDepartment,
            fosArrayFieldOfStudy,
            fosArrayType,
            fosArrayDescription,
            fosArrayIsFavourite
        )
        val listView = findViewById<ListView>(R.id.fieldsOfStudy)
        listView.adapter = myListAdapter
    }

    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    fun updateRecord() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val editId: EditText = dialogView.findViewById(R.id.updateId)
        val editDepartment: EditText = dialogView.findViewById(R.id.updateDepartment)
        val editFieldOfStudy: EditText = dialogView.findViewById(R.id.updateFieldOfStudy)
        val editDescription: EditText = dialogView.findViewById(R.id.updateDescription)
        val editTypeOfStudies: Switch = dialogView.findViewById(R.id.updateTypeOfStudies)

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update") { _, _ ->

            val updateId = editId.text.toString()
            val updateDepartment = editDepartment.text.toString()
            val updateFieldOfStudy = editFieldOfStudy.text.toString()
            val updateDescription = editDescription.text.toString()
            var updateTypeOfStudies = "Cywilne"
            if (editTypeOfStudies.isChecked) {
                updateTypeOfStudies = "Wojskowe"
            }
            //creating the instance of DatabaseHandler class
            val databaseHandler = DatabaseHandler(this)
            if (updateId.trim() != "" && updateDepartment.trim() != "" && updateFieldOfStudy.trim() != "" && updateDescription.trim() != "") {
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler
                    .updateFieldOfStudy(
                        Kierunki(
                            Integer.parseInt(updateId),
                            updateDepartment,
                            updateFieldOfStudy,
                            updateTypeOfStudies,
                            updateDescription,
                            0
                        )
                    )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record update", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
            viewRecord()

        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            //pass
        }
        val b = dialogBuilder.create()
        b.show()
        viewRecord()
    }

    //method for deleting records based on id
    private fun deleteRecord() {
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId: EditText = dialogView.findViewById(R.id.deleteId)
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete") { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler = DatabaseHandler(this)
            if (deleteId.trim() != "") {
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteFieldOfStudy(
                    Kierunki(
                        Integer.parseInt(deleteId),
                        "",
                        "",
                        "",
                        "",
                        0
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record deleted", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
            viewRecord()
        }
        dialogBuilder.setNegativeButton("Cancel") { _, _ ->
            //pass
        }
        val b = dialogBuilder.create()
        b.show()
    }

}