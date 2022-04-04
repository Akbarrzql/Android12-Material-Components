package com.example.android12

import android.os.Bundle
import android.system.Os.accept
import android.view.*
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var Button: Button
    private lateinit var listMateri: RecyclerView
    private var list: ArrayList<Materi> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Button = findViewById(R.id.extendedfab)

        // extanded fab (Button)



        list_Materi.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    extendedfab.extend()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && extendedfab.isExtended()) {
                    extendedfab.shrink()
                }
            }
        })
        //navigation drawer
        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            drawerLayout.close()
            true
        }

        //bottom navigation
        bottomnavigation.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    // Respond to navigation item 1 reselection
                    true
                }
                R.id.favorite -> {
                    // Respond to navigation item 2 reselection\
                    true
                }
                R.id.profile -> {
                    // Respond to navigation item 3 reselection
                    true
                } else -> {
                    false
                }
            }
        }


        extendedfab.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(resources.getString(R.string.title))
                .setMessage(resources.getString(R.string.supporting_text))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to neutral button press
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    // Respond to positive button press
                }
                .show()
            list_Materi.smoothScrollToPosition(0)
        }

        //recylerview layout manager
        listMateri = findViewById(R.id.list_Materi)
        listMateri.setHasFixedSize(true)

        list.addAll(MateriData.listdata)
        showRecyclerCardView()

        //chip group
       chip_kotlin.setOnClickListener {
           Toast.makeText(this, "Kotlin", Toast.LENGTH_SHORT).show()
           list.get(1).name = "Kotlin"
       }
        chip_java.setOnClickListener {
            Toast.makeText(this, "Java", Toast.LENGTH_SHORT).show()
            list.get(2).name = "Java"
        }
        chip_swift.setOnClickListener {
            Toast.makeText(this, "swift", Toast.LENGTH_SHORT).show()
            list.get(3).name = "swift"
        }
        chip_php.setOnClickListener {
            Toast.makeText(this, "php", Toast.LENGTH_SHORT).show()
            list.get(4).name = "php"
        }
        chip_c.setOnClickListener {
            Toast.makeText(this, "C", Toast.LENGTH_SHORT).show()
            list.get(5).name = "C"
        }
        chip_golang.setOnClickListener {
            Toast.makeText(this, "golang", Toast.LENGTH_SHORT).show()
            list.get(6).name = "golang"
        }
        chip_other.setOnClickListener {
            Toast.makeText(this, "Lainnya", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showRecyclerCardView() {
        listMateri.layoutManager = LinearLayoutManager(this)
        val cardViewMateriAdapter = CardViewMateriAdapter.CardViewMateriAdapter(list)
        listMateri.adapter = cardViewMateriAdapter
    }
}