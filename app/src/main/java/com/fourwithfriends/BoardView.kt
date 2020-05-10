package com.fourwithfriends

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Display
import android.view.View
import com.fourwithfriends.client.ClientModel
import com.fourwithfriends.dto.PlayerColor
import java.util.*

const val TOKEN_DIAMETER = 0.95
const val SCREEN_WIDTH = 0.9
const val TOP_PADDING = 0.05

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val rect: Rect = Rect(0, 0, 100, 100)
//    private val paint: Paint = Paint()
    private val blue: Paint = Paint()
    private val orange: Paint = Paint()
    private val white: Paint = Paint()

    init {
        blue.color = Color.CYAN
        orange.color = Color.rgb(255, 128, 0)
        white.color = Color.WHITE
    }

    var model: ClientModel = ClientModel()
    private val boardWidth = context.resources.getInteger(R.integer.board_width)
    private val boardHeight = context.resources.getInteger(R.integer.board_height)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val can: Canvas = canvas!!
        can.drawColor(Color.rgb(105, 88, 41))

        model.setBoardCell(0, 0, PlayerColor.Blue)
        val board = model.board
//        println("here is board ${Arrays.deepToString(board)}")
        for (col in 0 until boardWidth) {
            for (row in 0 until boardHeight) {
                val paint: Paint = when (board[col][row]!!) {
                    PlayerColor.Blue -> blue
                    PlayerColor.Orange -> orange
                    PlayerColor.None -> white
                }
                can.drawOval(convertCoords(row, col), paint)
            }
        }
//        can.drawOval(0F, 0F, 100F, 100F, blue)
    }

    private fun getDisplayDimensions(): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager
            .defaultDisplay
            .getMetrics(displayMetrics)
        return displayMetrics
    }

    private fun convertCoords(row: Int, col: Int): RectF {
        val boardWidthPx = width * SCREEN_WIDTH
        val boardXOffsetPx = (width - boardWidthPx) / 2
        val boardYOffsetPx = height * TOP_PADDING
        val cellWidthPx = boardWidthPx / boardWidth
        return RectF(
            (boardXOffsetPx + row * cellWidthPx).toFloat(),
            (boardYOffsetPx + col * cellWidthPx).toFloat(),
            (boardXOffsetPx + (row + 1) * cellWidthPx).toFloat(),
            (boardYOffsetPx + (col + 1) * cellWidthPx).toFloat()
        )
    }
}