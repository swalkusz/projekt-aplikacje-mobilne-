package com.example.a215_ic_projekt_szymon

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class UserListAdapter(
    private val context: Context,
    private val id: Array<String>,
    private val wydzial: Array<String>,
    private val kierunek: Array<String>,
    private val rodzaj: Array<String>,
    private val opis: Array<String>,
    private val czyUlubiony: Array<String>
) : ArrayAdapter<String>(context, R.layout.custom_list, wydzial) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.custom_list_for_user, parent, false)

        val departmentText = rowView.findViewById<TextView>(R.id.textViewDepartment)
        val fieldOfStudyText = rowView.findViewById<TextView>(R.id.textViewFieldOfStudy)
        val typeText = rowView.findViewById<TextView>(R.id.textViewType)
        val descriptionText = rowView.findViewById<TextView>(R.id.textViewDescription)
        val isFavouriteText = rowView.findViewById<TextView>(R.id.textViewIsFavourite)

        departmentText.text = "Wydział: ${wydzial[position]}"
        fieldOfStudyText.text = "Kierunek: ${kierunek[position]}"
        typeText.text = "Rodzaj: ${rodzaj[position]}"
        descriptionText.text = "Opis: ${opis[position]}"
        isFavouriteText.text = "Czy Ulubiony: ${czyUlubiony[position]}"

        return rowView
    }
}
