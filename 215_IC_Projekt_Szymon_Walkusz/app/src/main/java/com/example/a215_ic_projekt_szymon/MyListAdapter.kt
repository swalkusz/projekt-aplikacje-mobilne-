package com.example.a215_ic_projekt_szymon

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(
    private val context: Activity,
    private val id: Array<String>,
    private val wydzial: Array<String>,
    private val kierunek: Array<String>,
    private val rodzaj: Array<String>,
    private val opis: Array<String>,
    private val czyUlubiony: Array<String>
) : ArrayAdapter<String>(context, R.layout.custom_list, wydzial) {

    @SuppressLint("MissingInflatedId", "SetTextI18n", "ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater

        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val idText: TextView = rowView.findViewById(R.id.textViewId)
        val departmentText: TextView = rowView.findViewById(R.id.textViewDepartment)
        val fieldOfStudyText: TextView = rowView.findViewById(R.id.textViewFieldOfStudy)
        val typeText: TextView = rowView.findViewById(R.id.textViewType)
        val descriptionText: TextView = rowView.findViewById(R.id.textViewDescription)
        val isFavoutiteText: TextView = rowView.findViewById(R.id.textViewIsFavourite)

        idText.text = "Id: ${id[position]}"
        departmentText.text = "Wydzial: ${wydzial[position]}"
        fieldOfStudyText.text = "kierunek: ${kierunek[position]}"
        typeText.text = "rodzaj: ${rodzaj[position]}"
        descriptionText.text = "opis: ${opis[position]}"
        isFavoutiteText.text = "czyUlubiony: ${czyUlubiony[position]}"
        return rowView
    }
}