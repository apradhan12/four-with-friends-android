package com.fourwithfriends

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.fourwithfriends.client.ClientModel

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val rect: Rect = Rect(0, 0, 100, 100)
    private val paint: Paint = Paint()
    private val red: Paint = Paint(Color.RED)
    var model: ClientModel = ClientModel()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for ()
        paint.color = Color.BLACK;
        val can: Canvas = canvas!!
        can.drawColor(Color.GREEN)
        can.drawOval(0F, 0F, 50F, 100F, red)
    }
}