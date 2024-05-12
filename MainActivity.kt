package com.example.myapplicationnewtaskmanagement


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationnewtaskmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  lateinit var db:NoteDataBaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db= NoteDataBaseHelper(this)
        notesAdapter= NotesAdapter(db.getAllNotes(),this)

        binding.noteRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.noteRecyclerView.adapter=notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent( this,AddNoteActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }

}