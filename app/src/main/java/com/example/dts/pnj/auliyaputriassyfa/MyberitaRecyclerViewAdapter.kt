package com.example.dts.pnj.auliyaputriassyfa

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dts.pnj.auliyaputriassyfa.databinding.FragmentBeritaBinding

class MyNewsRecyclerViewAdapter(
    private val values: List<NewsItem>
) : RecyclerView.Adapter<MyNewsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentBeritaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.content.text = item.content

        // Load the image from drawable resource
        val imageResource = when (item.pathImage) {
            "drawable/imagethreadsapp"-> R.drawable.imagethreadsapp
            "drawable/aigemini"-> R.drawable.aigemini
            "drawable/imageanggrek"-> R.drawable.imageanggrek
            "drawable/beritahacker"-> R.drawable.beritahacker
            "drawable/imagedeadpool3"-> R.drawable.imagedeadpool3
            "drawable/beritamediamasa"-> R.drawable.beritamediamasa
            "drawable/imageelonmusk"-> R.drawable.imageelonmusk
            "drawable/imagesafari"-> R.drawable.imagesafari
            "drawable/imagelaptopvirus"-> R.drawable.imagelaptopvirus
            "drawable/atlantisjpg"-> R.drawable.atlantisjpg
            else -> R.drawable.imageberita // Placeholder or default image
        }
        holder.image.setImageResource(imageResource)

        // Set up click listener
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, detailBerita::class.java).apply {
                putExtra("EXTRA_TITLE", item.title)
                putExtra("EXTRA_CONTENT", item.content)
                putExtra("EXTRA_IMAGE_PATH", item.pathImage)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentBeritaBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val content = binding.content
        val image= binding.itemImage

        override fun toString(): String {
            return super.toString() + " '" + content.text + "'"
        }
    }
}