package com.example.a215_ic_projekt_szymon.menu_fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.a215_ic_projekt_szymon.DatabaseHandler
import com.example.a215_ic_projekt_szymon.R
import com.example.a215_ic_projekt_szymon.UserListAdapter
import com.example.a215_ic_projekt_szymon.admin.AdminLoginPanel
import com.example.a215_ic_projekt_szymon.database.Kierunki

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Recruitment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Recruitment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

//        viewRecord()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recruitment, container, false)

        val adminBtn = view.findViewById<Button>(R.id.adminBtn)

        adminBtn.setOnClickListener {
            val intent = Intent(activity, AdminLoginPanel::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewRecord(view)
    }


    private fun viewRecord(view: View) {

        //creating the instance of DatabaseHandler class
        val databaseHandler = DatabaseHandler(requireContext())

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
        val myListAdapter = UserListAdapter(
            requireContext(),
            fosArrayId,
            fosArrayDepartment,
            fosArrayFieldOfStudy,
            fosArrayType,
            fosArrayDescription,
            fosArrayIsFavourite
        )
        val listView = view.findViewById<ListView>(R.id.fieldsOfStudy)
        listView.adapter = myListAdapter
    }


}