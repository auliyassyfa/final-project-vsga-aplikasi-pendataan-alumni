package com.example.dts.pnj.auliyaputriassyfa

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.File

class Profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_profile, container, false)

        val userInfo = loadUserInfo()
        view.findViewById<TextView>(R.id.username).text = userInfo["nama"]
        view.findViewById<TextView>(R.id.userEmail).text = userInfo["email"]
        view.findViewById<TextView>(R.id.userNpm).text = userInfo["npm"]
        view.findViewById<TextView>(R.id.kelasUser).text = userInfo["kelas"]

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            handleLogout()
        }

        return view
    }

    private fun loadUserInfo(): Map<String, String> {
        val fileName = "user_info.txt"
        val file = File(requireContext().filesDir, fileName)
        val userInfo = mutableMapOf<String, String>()

        if (file.exists()) {
            file.bufferedReader().useLines { lines ->
                val content = lines.toList()
                if (content.size >= 4) {
                    userInfo["nama"] = content[0]
                    userInfo["email"] = content[1]
                    userInfo["npm"] = content[2]
                    userInfo["kelas"] = content[3]
                }
            }
        }
        return userInfo
    }
    private fun handleLogout() {
        val fileName = "user_info.txt"
        val file = File(requireContext().filesDir, fileName)
        if (file.exists()) {
            file.delete()
        }

        val intent = Intent(requireContext(), Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}