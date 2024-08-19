package com.example.dts.pnj.auliyaputriassyfa

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        writeUserInfoToFile()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomnav)
        bottomNavigationView.setOnItemSelectedListener {item->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_home -> selectedFragment = fragmentHome()
                R.id.nav_news -> selectedFragment = fragmentBerita()
                R.id.nav_profile -> selectedFragment = Profile()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentLayout, selectedFragment)
                    .commit()
            }
            true
        }
        
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentLayout, fragmentHome())
                .commit()
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.alumni_nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_add_alumni -> {
                val intent = Intent(this, tambahAlumni::class.java)
                startActivity(intent)
                true
            }
            R.id.nav_data_alumni -> {
                val intent = Intent(this, dataAlumni::class.java)
                startActivity(intent)
                true
            }
            R.id.nav_logout -> {
                val intent = Intent(this, Login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun writeUserInfoToFile() {
        val fileName = "user_info.txt"
        val fileContent = """
            Dimas Febriyanto
            dimas@example.com
            50422430
            2IA21
        """.trimIndent()

        try {
            val file = File(filesDir, fileName)
            FileOutputStream(file).use { fos ->
                fos.write(fileContent.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}