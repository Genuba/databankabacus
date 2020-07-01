package com.example.databankabacus.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

internal class SqliteUsuario(context: Context?) :
    SQLiteOpenHelper(context, TABLE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL1 + " INTEGER," +
                    COL2 + " TEXT," +
                    COL3 + " TEXT," +
                    COL4 + " TEXT" +
                    ")"
        db.execSQL(createTable)

        val contentValues = ContentValues()
        contentValues.put(COL4, "token")
        db.insert(TABLE_NAME, null, contentValues)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    /**
     * Updates the name field
     */
    fun updateToken(token: String?) {
        val db = this.writableDatabase
        val query =
            "UPDATE " + TABLE_NAME + " SET " +
                    COL4 + " = '" + token + "'" +
                    " WHERE ID = 1"
        Log.d(TAG, "updateName: query: $query")
        db.execSQL(query)
    }

    /**
     * Updates the name field
     */
    fun updateUsuario(id: Int?,
    nombre: String?,
    password: String?) {
        val db = this.writableDatabase
        val query =
            "UPDATE " + TABLE_NAME + " SET " +
                    COL1 + " = " + id + "," +
                    COL2 + " = '" + nombre + "'," +
                    COL3 + " = '" + password + "'" +
                    " WHERE ID = 1"
        Log.d(TAG, "updateName: query: $query")
        db.execSQL(query)
    }


    val Usuario: Cursor
        get() {
            val db = this.writableDatabase
            val query =
                "SELECT " + COL1 + "," +
                        COL2 + "," +
                        COL3 + "," +
                        COL4 +
                        " FROM " + TABLE_NAME +
                        " WHERE ID = 1"
            return db.rawQuery(query, null)
        }

    companion object {
        private const val TAG = "DatabaseHelper"
        private const val TABLE_NAME = "usuario"
        private const val COL1 = "IDUSUARIO"
        private const val COL2 = "NOMBRE"
        private const val COL3 = "PASSWORD"
        private const val COL4 = "TOKEN"
    }
}