package com.example.dts.pnj.auliyaputriassyfa

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dts.pnj.auliyaputriassyfa.database.databaseHelper

class beritaFragment : Fragment() {
    private var columnCount = 1
    private lateinit var databaseHelper: databaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        databaseHelper = databaseHelper(requireContext())
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
            databaseHelper.TABLE_NEWS,
            arrayOf(
                databaseHelper.COLUMN_ID,
                databaseHelper.COLUMN_TITLE,
                databaseHelper.COLUMN_CONTENT,
                databaseHelper.COLUMN_PATH_IMAGE
            ),
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(databaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(databaseHelper.COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(databaseHelper.COLUMN_CONTENT))
                val pathImage = getString(getColumnIndexOrThrow(databaseHelper.COLUMN_PATH_IMAGE))
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
            beritaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

// Data class to represent news items
data class NewsItem(val id: Long, val title: String, val content: String, val pathImage: String)