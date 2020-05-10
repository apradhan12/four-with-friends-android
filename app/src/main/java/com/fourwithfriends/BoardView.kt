package com.fourwithfriends

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val rect: Rect = Rect(0, 0, 100, 100)
    private val paint: Paint = Paint()
    private val red: Paint = Paint(Color.RED)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLACK;
        val can: Canvas = canvas!!
        can.drawColor(Color.GREEN)
        can.drawOval(0F, 0F, 50F, 100F, red)
    }
}