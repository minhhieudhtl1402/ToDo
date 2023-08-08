package com.minhhieu1402.todo.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.minhhieu1402.todo.model.Note

class NoteDBHelper(val context: Context) : SQLiteOpenHelper(context, "Note.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Note.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(Note.DROP_TABLE)
        onCreate(db)
    }

    fun insertToTable(note: Note) {
        val contentValues = ContentValues()
        contentValues.put(Note.COLUMN_TITLE_NAME, note.title)
        contentValues.put(Note.COLUMN_CONTENT_NAME, note.content)
        contentValues.put(Note.COLUMN_DATE_NAME, note.date)
        val sqLiteDatabase = this.writableDatabase
        sqLiteDatabase.insert(Note.TABLE_NAME, null, contentValues)
        sqLiteDatabase.close()
    }

    fun readAllNotes(): ArrayList<Note> {
        val notes = arrayListOf<Note>()
        val sqLiteDatabase = this.readableDatabase
        val projection = arrayOf(
            Note.COLUMN_ID_NAME,
            Note.COLUMN_TITLE_NAME,
            Note.COLUMN_CONTENT_NAME,
            Note.COLUMN_DATE_NAME
        )
        val cursor = sqLiteDatabase.query(Note.TABLE_NAME, projection, null, null, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(Note.COLUMN_ID_NAME))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_TITLE_NAME))
                val content =
                    cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_CONTENT_NAME))
                val date = cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_DATE_NAME))
                notes.add(Note(title, content, date))
            }
        }
        return notes
    }

    fun delete(notes: Note) {
        val database = this.writableDatabase
        database.delete(
            Note.TABLE_NAME,
            "${Note.COLUMN_TITLE_NAME}=? and ${Note.COLUMN_CONTENT_NAME}=? and ${Note.COLUMN_DATE_NAME}=?",
            arrayOf(notes.title, notes.content, notes.date)
        )
        database.close()
    }
}