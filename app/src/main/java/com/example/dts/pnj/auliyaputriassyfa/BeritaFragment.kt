package com.example.dts.pnj.auliyaputriassyfa

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dts.pnj.auliyaputriassyfa.database.DatabaseHelper

class BeritaFragment : Fragment() {
    private var columnCount = 1
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        databaseHelper = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_berita_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyNewsRecyclerViewAdapter(getNewsFromDatabase())
            }
        }
        return view
    }

    private fun getNewsFromDatabase(): List<NewsItem> {
        val newsList = mutableListOf<NewsItem>()
        val db = databaseHelper.readableDatabase

        // Query to get all news from the database
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_NEWS,
            arrayOf(
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_TITLE,
                DatabaseHelper.COLUMN_CONTENT,
                DatabaseHelper.COLUMN_PATH_IMAGE
            ),
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_CONTENT))
                val pathImage = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PATH_IMAGE))
                newsList.add(NewsItem(id, title, content, pathImage))
            }
            close()
        }

        return newsList
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            BeritaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

// Data class to represent news items
data class NewsItem(val id: Long, val title: String, val content: String, val pathImage: String)