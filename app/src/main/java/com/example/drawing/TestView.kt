package com.example.drawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2

class TestView(context: Context, attributes: AttributeSet) : View(context, attributes) {

    var  listener : Listener? = null
    private val paint = Paint()
    private val paintC = Paint()

    private val startAngle = 0f
    private val colors = listOf(
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.YELLOW,
        Color.BLACK,
        Color.DKGRAY
    )
    private val sweepAngle = 360f / colors.size

    private var buttonClicked = 0

    init {
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = 5f

        paintC.style = Paint.Style.FILL
        paintC.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        cusok(canvas)
    }

    private fun cusok(canvas: Canvas) {

        val centerX = width / 2
        val centerY = width / 2
        val radius = width.coerceAtMost(height) / 2f - 10f

        for (i in colors.indices) {
            paintC.color = colors[i]
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle + i * sweepAngle,
                sweepAngle,
                true,
                paintC
            )
        }
        for (i in colors.indices) {
            paintC.color = if (i == buttonClicked)
                Color.GRAY
            else colors[i]
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle + i * sweepAngle,
                sweepAngle,
                true,
                paint
            )
        }
        paintC.color = colors[buttonClicked]
        canvas.drawCircle(
            centerX.toFloat() ,
            centerY.toFloat() ,
            radius / 2,
            paintC
        )
        canvas.drawCircle(
            centerX.toFloat() ,
            centerY.toFloat() ,
            radius / 2,
            paint
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val centerX = width / 2f
        val centerY = width / 2f
        val x = event.x
        val y = event.y

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                val angle = (Math.toDegrees(
                    atan2(
                        y - centerY,
                        x - centerX
                    ).toDouble()
                ) + 360) % 360
                buttonClicked = (angle / ( 360 / colors.size)).toInt()
                listener?.onClick(buttonClicked)
                Log.d("MyLog", "Angle: $angle")
                invalidate()
            }

           /* MotionEvent.ACTION_UP -> {
                buttonClicked = -1
                invalidate()
            } */
        }

        return true
    }

    interface Listener{
        fun onClick(index: Int)
    }
}