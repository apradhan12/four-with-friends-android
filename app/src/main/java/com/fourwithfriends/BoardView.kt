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

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val blue: Paint = Paint()
    private val boardBgPaint = Paint()
    private val orange: Paint = Paint()
    private val white: Paint = Paint()
    private val bgColor: Int = Color.rgb(105, 88, 41)
    private val colorsMap = mapOf(PlayerColor.Blue to blue,
        PlayerColor.Orange to orange,
        PlayerColor.None to white)

    init {
        blue.color = Color.CYAN
        boardBgPaint.color = Color.rgb(255, 128, 0)
        orange.color = Color.rgb(255, 206, 0)
        white.color = Color.WHITE

        for ((_, paint) in colorsMap) {
            paint.isAntiAlias = true;
            paint.isFilterBitmap = true;
        }
    }

    var model: ClientModel = ClientModel()
    private val boardWidth = context.resources.getInteger(R.integer.board_width)
    private val boardHeight = context.resources.getInteger(R.integer.board_height)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val can: Canvas = canvas!!
        can.drawColor(bgColor)
        can.drawRect(0F, 0F, width.toFloat(), (width * boardHeight / boardWidth).toFloat(), boardBgPaint)

        val board = model.board
        for (col in 0 until boardWidth) {
            for (row in 0 until boardHeight) {
                val paint: Paint = colorsMap[board[col][row]!!] ?: error("Color not in map")
                can.drawOval(convertCoords(col, row), paint)
            }
        }
    }

    private fun convertRow(row: Int): Int {
        return boardHeight - row - 1
    }

    private fun convertCoords(col: Int, row: Int): RectF {
        val boardWidthPx = width * SCREEN_WIDTH
        val screenHeightAdjusted = width * boardHeight / boardWidth
        val boardHeightPx = boardWidthPx * boardHeight / boardWidth
        val boardXOffsetPx = (width - boardWidthPx) / 2
        val boardYOffsetPx = (screenHeightAdjusted - boardHeightPx) / 2
        val cellWidthPx = boardWidthPx / boardWidth
        val tokenWidthPx = cellWidthPx * TOKEN_DIAMETER
        val tokenOffsetPx = (cellWidthPx - tokenWidthPx) / 2
        val convertedRow = convertRow(row)

        return RectF(
            (boardXOffsetPx + col * cellWidthPx + tokenOffsetPx).toFloat(),
            (boardYOffsetPx + convertedRow * cellWidthPx + tokenOffsetPx).toFloat(),
            (boardXOffsetPx + (col + 1) * cellWidthPx - tokenOffsetPx).toFloat(),
            (boardYOffsetPx + (convertedRow + 1) * cellWidthPx - tokenOffsetPx).toFloat()
        )
    }
}