package com.example.dts.pnj.auliyaputriassyfa

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.dts.pnj.auliyaputriassyfa.R

class detailBerita : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        val imageView = findViewById<ImageView>(R.id.gambarBerita)

        val intent = intent
        val title = intent.getStringExtra("EXTRA_TITLE")
        val content = intent.getStringExtra("EXTRA_CONTENT")
        val path_image = intent.getStringExtra("EXTRA_IMAGE_PATH")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val titleTextView = findViewById<TextView>(R.id.judulBerita)
        val contentTextView = findViewById<TextView>(R.id.isiBerita)

        titleTextView.text = title
        contentTextView.text = content

        // Load the image from drawable resource
        val imageResource = when (path_image) {
            "drawable/beritahacker" -> R.drawable.beritahacker
            "drawable/beritamediamasa" -> R.drawable.beritamediamasa
            "drawable/beritapolitik" -> R.drawable.beritapolitik
            else -> R.drawable.imageberita
        }
        imageView.setImageResource(imageResource)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}