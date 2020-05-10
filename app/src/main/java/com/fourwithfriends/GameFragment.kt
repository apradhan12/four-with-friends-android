package com.fourwithfriends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fourwithfriends.client.IClientController
import com.fourwithfriends.client.IClientModel
import com.fourwithfriends.client.IClientView
import com.fourwithfriends.dto.GameStatus
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class GameFragment : Fragment(), IClientView {

    private lateinit var boardView: BoardView
    private lateinit var statusText: TextView
    private lateinit var hostInput: TextInputLayout
    private lateinit var portInput: TextInputLayout
    private lateinit var model: IClientModel
    private lateinit var controller: IClientController
    private lateinit var dropColumnInput: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        boardView = view.findViewById(R.id.boardView)
        statusText = view.findViewById(R.id.statusText)
        hostInput = view.findViewById(R.id.hostIpInput)
        portInput = view.findViewById(R.id.portNumberInput)
        dropColumnInput = view.findViewById(R.id.dropColumnInput)

        println("This is the view that was created $view")
        println("This is the fragment: ${view.findViewById<BoardView>(R.id.boardView)}")

        view.findViewById<Button>(R.id.connectButton).setOnClickListener {
            controller.connectToServer(hostInput.editText!!.text.toString(), portInput.editText!!.text.toString().toInt())
        }
        view.findViewById<Button>(R.id.dropButton).setOnClickListener {
            println("Button clicked")
            controller.dropToken(dropColumnInput.progress)
        }
//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun updateGameStatus() {
        requireActivity().runOnUiThread {
            statusText.text = when (model.gameStatus!!) {
                GameStatus.PlayerTurn -> "It's your turn"
                GameStatus.OpponentTurn -> "It's the opponent's turn"
                GameStatus.Win -> "You won!"
                GameStatus.Loss -> "You lost."
                GameStatus.Draw -> "It's a tie."
            }
        }
    }

    override fun render() {
        boardView.invalidate()
    }

    override fun updateBoardState() {
        boardView.invalidate()
    }

    override fun updateConnectionState() {

    }

    override fun updatePlayerColor() {

    }

    override fun setModel(model: IClientModel) {
        this.model = model
        boardView.model = model
    }

    override fun setController(controller: IClientController) {
        this.controller = controller
    }
}