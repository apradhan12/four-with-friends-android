package com.fourwithfriends

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fourwithfriends.client.*
import com.fourwithfriends.dto.PlayerColor
import kotlinx.android.synthetic.main.connect4_activity.*

class Connect4Activity : AppCompatActivity() {

    private var model: IClientModel = ClientModel()
    private lateinit var view: IClientView
    private var controller = ClientControllerDelegate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect4_activity)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        println("Desired fragment ID: " + R.id.GameFragment)
        for (fragment in supportFragmentManager.fragments) {
            println("Fragment id: " + fragment.id)
            println("Frag name: $fragment")
        }
        view = supportFragmentManager.findFragmentById(R.id.GameFragment)!! as GameFragment
        controller.setModel(model)
        controller.setView(view)
        view.setModel(model)
        view.setController(controller)
    }

    /**
     * This function creates the settings menu (the ... icon)
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}