package com.example.a215_ic_projekt_szymon

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.a215_ic_projekt_szymon.database.Kierunki

//creating the database logic, extending the SQLiteOpenHelper base class

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "AMW"
        private val TABLE_CONTACTS = "Studies"
        private val KEY_ID = "id"
        private val KEY_DEPARTMENT = "department"
        private val KEY_FIELD_OF_STUDY = "fieldOfStudy"
        private val KEY_TYPE = "type"
        private val KEY_DESCRIPTION = "description"
        private val KEY_IS_FAVOURITE = "isFavourite"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DEPARTMENT + " TEXT, "
                + KEY_FIELD_OF_STUDY + " TEXT, "
                + KEY_TYPE + " TEXT, "
                + KEY_DESCRIPTION + " TEXT, "
                + KEY_IS_FAVOURITE + " INTEGER" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    //method to insert data
    fun addFieldOfStudy(kierunek: Kierunki): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()
//        contentValues.put(KEY_ID, kierunek.id)
        contentValues.put(KEY_DEPARTMENT, kierunek.wydzial)
        contentValues.put(KEY_FIELD_OF_STUDY, kierunek.kierunek)
        contentValues.put(KEY_TYPE, kierunek.rodzaj)
        contentValues.put(KEY_DESCRIPTION, kierunek.opis)
        contentValues.put(KEY_IS_FAVOURITE, kierunek.czyUlubiony)


        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }


    //method to read data
    @SuppressLint("Range", "Recycle")
    fun viewFieldOfStudy(): List<Kierunki> {

        val empList: ArrayList<Kierunki> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var wydzial: String
        var kierunek: String
        var rodzaj: String
        var opis: String
        var czyUlubiony: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                wydzial = cursor.getString(cursor.getColumnIndex("department"))
                kierunek = cursor.getString(cursor.getColumnIndex("fieldOfStudy"))
//                rodzaj = cursor.getString(cursor.getColumnIndex("type"))
                rodzaj = cursor.getString(cursor.getColumnIndex(KEY_TYPE))
                opis = cursor.getString(cursor.getColumnIndex("description"))
                czyUlubiony = cursor.getInt(cursor.getColumnIndex("isFavourite"))
                val kierunek = Kierunki(
                    id = id,
                    wydzial = wydzial,
                    kierunek = kierunek,
                    rodzaj = rodzaj,
                    opis = opis,
                    czyUlubiony = czyUlubiony,
                )

                empList.add(kierunek)

            } while (cursor.moveToNext())
        }
        return empList
    }


    //method to update data

    fun updateFieldOfStudy(kierunek: Kierunki): Int {

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, kierunek.id)
        contentValues.put(KEY_DEPARTMENT, kierunek.wydzial)
        contentValues.put(KEY_FIELD_OF_STUDY, kierunek.kierunek)
        contentValues.put(KEY_TYPE, kierunek.rodzaj)
        contentValues.put(KEY_DESCRIPTION, kierunek.opis)
        contentValues.put(KEY_IS_FAVOURITE, kierunek.czyUlubiony)

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues, "id=" + kierunek.id, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to delete data
    fun deleteFieldOfStudy(kierunek: Kierunki): Int {

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, kierunek.id) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS, "id=" + kierunek.id, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}