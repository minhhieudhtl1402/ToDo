package com.minhhieu1402.todo.model

import java.sql.Timestamp

data class Note(val title: String, val content: String, val date: String) {
    companion object {
        const val TABLE_NAME = "note"
        const val COLUMN_ID_NAME = "id"
        const val COLUMN_TITLE_NAME = "title"
        const val COLUMN_CONTENT_NAME = "content"
        const val COLUMN_DATE_NAME = "date"
        const val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME(" +
                    "$COLUMN_ID_NAME INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_TITLE_NAME TEXT NOT NULL," +
                    "$COLUMN_CONTENT_NAME TEXT NOT NULL," +
                    "$COLUMN_DATE_NAME TEXT NOT NULL)"
        const val DROP_TABLE = "" +
                "DROP TABLE IF EXISTS $TABLE_NAME"

    }
}
