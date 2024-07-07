package com.example.a215_ic_projekt_szymon

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.a215_ic_projekt_szymon.menu_fragments.AboutUs
import com.example.a215_ic_projekt_szymon.menu_fragments.Contact
import com.example.a215_ic_projekt_szymon.menu_fragments.News
import com.example.a215_ic_projekt_szymon.menu_fragments.Recruitment

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun actionsOnClickMenu(view: View) {
        val button = view as Button
        when (button.id) {
            R.id.aboutUsBtn ->
                replaceFragment(AboutUs::class.java)

            R.id.newsBtn ->
                replaceFragment(News::class.java)

            R.id.recruitmentBtn ->
                replaceFragment(Recruitment::class.java)

            R.id.contactBtn ->
                replaceFragment(Contact::class.java)
        }
    }


    private fun <T : Fragment> replaceFragment(fragmentClass: Class<T>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragmentClass, null)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }

}