package com.example.teste.database
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "app_database.db"
        const val DATABASE_VERSION = 1

        // Tabelas
        const val TABLE_USERS = "users"
        const val TABLE_PRODUCTS = "products"

        // Colunas da tabela de usuários
        const val COL_USER_ID = "id"
        const val COL_USER_NAME = "name"
        const val COL_USER_EMAIL = "email"

        // Colunas da tabela de produtos
        const val COL_PRODUCT_ID = "id"
        const val COL_PRODUCT_NAME = "name"
        const val COL_PRODUCT_PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Criação das tabelas
        val createUsersTable = """
            CREATE TABLE $TABLE_USERS (
                $COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_USER_NAME TEXT NOT NULL,
                $COL_USER_EMAIL TEXT NOT NULL
            )
        """.trimIndent()

        val createProductsTable = """
            CREATE TABLE $TABLE_PRODUCTS (
                $COL_PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_PRODUCT_NAME TEXT NOT NULL,
                $COL_PRODUCT_PRICE REAL NOT NULL
            )
        """.trimIndent()

        db.execSQL(createUsersTable)
        db.execSQL(createProductsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualização das tabelas (drop e recriação)
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db)
    }

    // Funções CRUD para usuários
    fun addUser(name: String, email: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_USER_NAME, name)
            put(COL_USER_EMAIL, email)
        }
        return db.insert(TABLE_USERS, null, values)
    }

    fun getUser(id: Int): String? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COL_USER_NAME),
            "$COL_USER_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        return if (cursor != null && cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndex(COL_USER_NAME))
        } else {
            null
        }
    }

    // Funções CRUD para produtos
    fun addProduct(name: String, price: Double): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_PRODUCT_NAME, name)
            put(COL_PRODUCT_PRICE, price)
        }
        return db.insert(TABLE_PRODUCTS, null, values)
    }

    fun getProduct(id: Int): String? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_PRODUCTS,
            arrayOf(COL_PRODUCT_NAME),
            "$COL_PRODUCT_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        return if (cursor != null && cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndex(COL_PRODUCT_NAME))
        } else {
            null
        }
    }
}
