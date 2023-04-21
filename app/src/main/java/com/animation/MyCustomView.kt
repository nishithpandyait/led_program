package com.animation


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin


class MyCustomView : View {

    constructor(context: Context?) : super(context!!) {
        //As it is not required
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    var icBlueCircle: Bitmap? = null
    var icGreenCircle: Bitmap? = null
    var icRedCircle: Bitmap? = null
    var icDefaultCircle: Bitmap? = null
    var arrayOfCircle: Array<Bitmap?>? = null


    fun init(context: Context) {
        icBlueCircle = ContextCompat.getDrawable(context, R.drawable.ic_blue_circle)?.toBitmap()

        icGreenCircle = ContextCompat.getDrawable(context, R.drawable.ic_green_circle)?.toBitmap()

        icRedCircle = ContextCompat.getDrawable(context, R.drawable.ic_red_circle)?.toBitmap()
        icDefaultCircle =
            ContextCompat.getDrawable(context, R.drawable.ic_default_circle)?.toBitmap()

        arrayOfCircle = arrayOf(
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
            icDefaultCircle,
        )

        GlobalScope.launch(Dispatchers.Main) {
            while (true) {

                /* if (currentPosition == circleCount) {
                     currentPosition = 0
                 }*/

               // arrayOfCircle.mapIndexed { index, bitmap ->  }

                currentPosition++
                delay(1000)
                invalidate()


            }
        }
    }


    var currentPosition = 0
    val colorDefault = Color.GRAY
    val circleCount = 15
    private val pointPosition: PointF = PointF(0.0f, 0.0f)


    override fun onDraw(canvas: Canvas?) {


        // super.onDraw(canvas)

        var count = 0
        val radius = (width / 2).toDouble()

        while (count < arrayOfCircle!!.size) {
            pointPosition.computeXYForSpeed(count, radius.toFloat())


                arrayOfCircle!![count]?.let {
                    canvas?.drawBitmap(
                        it,
                        pointPosition.x,
                        pointPosition.y,
                        null
                    )
                }



            count++
        }
        /* val paint = Paint()
         paint.color = Color.WHITE
         paint.style = Paint.Style.FILL_AND_STROKE
         canvas!!.drawPaint(paint)

         paint.color = Color.BLACK
         paint.setTextSize(100f)
         canvas.drawText(currentPosition.toString(), 10f, 100f, paint)*/


    }


    private fun PointF.computeXYForSpeed(pos: Int, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (6 / 3.0)
        val angle = startAngle + pos * (Math.PI / 9)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }


}