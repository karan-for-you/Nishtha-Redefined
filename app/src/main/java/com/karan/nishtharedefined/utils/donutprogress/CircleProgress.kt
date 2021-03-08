package com.karan.nishtharedefined.utils.donutprogress


import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.karan.nishtharedefined.R

open class CircleProgress @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {
    private var finishedPaint: Paint? = null
    private var unfinishedPaint: Paint? = null
    private var innerCirclePaint: Paint? = null
    var textPaint: Paint? = null
    protected var innerBottomTextPaint: Paint? = null
    private val finishedOuterRect = RectF()
    private val unfinishedOuterRect = RectF()
    var attributeResourceId = 0
    var isShowText = false
    private var textSize = 0f
    private var textColor = 0
    private var fontFamily: Typeface? = null
    private var innerBottomTextColor = 0
    private var progress = 0f
    private var max = 0
    private var finishedStrokeColor = 0
    private var gradientColorOne = 0
    private var gradientColorTwo = 0
    private var unfinishedStrokeColor = Color.WHITE
    private var startingDegree = 0
    private var finishedStrokeWidth = 0f
    private var unfinishedStrokeWidth = 0f
    private var innerBackgroundColor = 0
    private var prefixText: String? = ""
    private var suffixText: String? = "%"
    private var text: String? = null
    private var innerBottomTextSize = 0f
    private var innerBottomText: String? = null
    private var innerBottomTextHeight = 0f
    private val default_stroke_width: Float
    private val default_finished_color = Color.rgb(66, 145, 241)
    private val default_unfinished_color = Color.rgb(204, 204, 204)
    private val default_text_color = Color.rgb(66, 145, 241)
    private val default_inner_bottom_text_color = Color.rgb(66, 145, 241)
    private val default_inner_background_color = Color.parseColor("#71130a0d")
    private val default_max = 100
    private val default_startingDegree = -90
    private val default_text_size: Float
    private val default_inner_bottom_text_size: Float
    private val min_size: Int
    protected fun initPainters() {
        if (isShowText) {
            textPaint = TextPaint()
            (textPaint as TextPaint).color = textColor
            (textPaint as TextPaint).textSize = textSize
            (textPaint as TextPaint).isAntiAlias = true
            innerBottomTextPaint = TextPaint()
            (innerBottomTextPaint as TextPaint).color = innerBottomTextColor
            (innerBottomTextPaint as TextPaint).textSize = innerBottomTextSize
            (innerBottomTextPaint as TextPaint).isAntiAlias = true
            (innerBottomTextPaint as TextPaint).typeface = Typeface.createFromAsset(
                context.assets,
                "font/tommy_regular.ttf"
            )
        }
        finishedPaint = Paint()

        //finishedPaint.setShader(new SweepGradient(0, 0,  Color.BLUE, Color.YELLOW));
        if (gradientColorOne != 0 && gradientColorTwo != 0) {
            finishedPaint!!.shader = LinearGradient(
                0f, 0f, 0f, height.toFloat(),
                gradientColorOne, gradientColorTwo,
                Shader.TileMode.MIRROR
            )
        } else {
            finishedPaint!!.color = finishedStrokeColor
        }
        finishedPaint!!.style = Paint.Style.STROKE
        finishedPaint!!.isAntiAlias = true
        finishedPaint!!.strokeWidth = finishedStrokeWidth
        unfinishedPaint = Paint()
        unfinishedPaint!!.color = unfinishedStrokeColor
        unfinishedPaint!!.style = Paint.Style.STROKE
        unfinishedPaint!!.isAntiAlias = true
        unfinishedPaint!!.strokeWidth = unfinishedStrokeWidth
        innerCirclePaint = Paint()
        innerCirclePaint!!.color = innerBackgroundColor
        innerCirclePaint!!.isAntiAlias = true
    }

    protected fun initByAttributes(attributes: TypedArray) {
        gradientColorOne =
            attributes.getColor(R.styleable.AdCircleProgress_adpgb_gradient_color_one, 0)
        gradientColorTwo =
            attributes.getColor(R.styleable.AdCircleProgress_adpgb_gradient_color_two, 0)
        finishedStrokeColor = attributes.getColor(
            R.styleable.AdCircleProgress_adpgb_finished_color,
            default_finished_color
        )
        unfinishedStrokeColor = attributes.getColor(
            R.styleable.AdCircleProgress_adpgb_unfinished_color,
            default_unfinished_color
        )
        isShowText = attributes.getBoolean(R.styleable.AdCircleProgress_adpgb_show_text, true)
        attributeResourceId =
            attributes.getResourceId(R.styleable.AdCircleProgress_adpgb_inner_drawable, 0)
        setMax(attributes.getInt(R.styleable.AdCircleProgress_adpgb_max, default_max))
        setProgress(attributes.getFloat(R.styleable.AdCircleProgress_adpgb_progress, 0f))
        finishedStrokeWidth = attributes.getDimension(
            R.styleable.AdCircleProgress_adpgb_finished_stroke_width,
            default_stroke_width
        )
        unfinishedStrokeWidth = attributes.getDimension(
            R.styleable.AdCircleProgress_adpgb_unfinished_stroke_width,
            default_stroke_width
        )
        if (isShowText) {
            if (attributes.getString(R.styleable.AdCircleProgress_adpgb_prefix_text) != null) {
                prefixText = attributes.getString(R.styleable.AdCircleProgress_adpgb_prefix_text)
            }
            if (attributes.getString(R.styleable.AdCircleProgress_adpgb_suffix_text) != null) {
                suffixText = attributes.getString(R.styleable.AdCircleProgress_adpgb_suffix_text)
            }
            if (attributes.getString(R.styleable.AdCircleProgress_adpgb_text) != null) {
                text = attributes.getString(R.styleable.AdCircleProgress_adpgb_text)
            }
            textColor = attributes.getColor(
                R.styleable.AdCircleProgress_adpgb_text_color,
                default_text_color
            )
            textSize = attributes.getDimension(
                R.styleable.AdCircleProgress_adpgb_text_size,
                default_text_size
            )
            innerBottomTextSize = attributes.getDimension(
                R.styleable.AdCircleProgress_adpgb_inner_bottom_text_size,
                default_inner_bottom_text_size
            )
            innerBottomTextColor = attributes.getColor(
                R.styleable.AdCircleProgress_adpgb_inner_bottom_text_color,
                default_inner_bottom_text_color
            )
            innerBottomText =
                attributes.getString(R.styleable.AdCircleProgress_adpgb_inner_bottom_text)
        }
        innerBottomTextSize = attributes.getDimension(
            R.styleable.AdCircleProgress_adpgb_inner_bottom_text_size,
            default_inner_bottom_text_size
        )
        innerBottomTextColor = attributes.getColor(
            R.styleable.AdCircleProgress_adpgb_inner_bottom_text_color,
            default_inner_bottom_text_color
        )
        innerBottomText = attributes.getString(R.styleable.AdCircleProgress_adpgb_inner_bottom_text)
        startingDegree = attributes.getInt(
            R.styleable.AdCircleProgress_adpgb_circle_starting_degree,
            default_startingDegree
        )
        innerBackgroundColor = attributes.getColor(
            R.styleable.AdCircleProgress_adpgb_background_color,
            default_inner_background_color
        )
    }

    override fun invalidate() {
        initPainters()
        super.invalidate()
    }

    fun getFinishedStrokeWidth(): Float {
        return finishedStrokeWidth
    }

    fun setFinishedStrokeWidth(finishedStrokeWidth: Float) {
        this.finishedStrokeWidth = finishedStrokeWidth
        this.invalidate()
    }

    fun getUnfinishedStrokeWidth(): Float {
        return unfinishedStrokeWidth
    }

    fun setUnfinishedStrokeWidth(unfinishedStrokeWidth: Float) {
        this.unfinishedStrokeWidth = unfinishedStrokeWidth
        this.invalidate()
    }

    private val progressAngle: Float
        private get() = getProgress() / max.toFloat() * 360f

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        if (this.progress > getMax()) {
            this.progress %= getMax().toFloat()
        }
        invalidate()
    }

    fun getMax(): Int {
        return max
    }

    fun setMax(max: Int) {
        if (max > 0) {
            this.max = max
            invalidate()
        }
    }

    fun getTextSize(): Float {
        return textSize
    }

    fun setTextSize(textSize: Float) {
        this.textSize = textSize
        this.invalidate()
    }

    fun getTextColor(): Int {
        return textColor
    }

    fun setTextColor(textColor: Int) {
        this.textColor = textColor
        this.invalidate()
    }

    fun getFinishedStrokeColor(): Int {
        return finishedStrokeColor
    }

    fun getGradientColorOne(): Int {
        return gradientColorOne
    }

    fun setGradientColorOne(gradientColorOne: Int) {
        this.gradientColorOne = gradientColorOne
        this.invalidate()
    }

    fun getGradientColorTwo(): Int {
        return gradientColorTwo
    }

    fun setGradientColorTwo(gradientColorTwo: Int) {
        this.gradientColorTwo = gradientColorTwo
        this.invalidate()
    }

    fun setFinishedStrokeColor(finishedStrokeColor: Int) {
        this.finishedStrokeColor = finishedStrokeColor
        this.invalidate()
    }

    fun getUnfinishedStrokeColor(): Int {
        return unfinishedStrokeColor
    }

    fun setUnfinishedStrokeColor(unfinishedStrokeColor: Int) {
        this.unfinishedStrokeColor = unfinishedStrokeColor
        this.invalidate()
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String?) {
        this.text = text
        this.invalidate()
    }

    fun getSuffixText(): String? {
        return suffixText
    }

    fun setSuffixText(suffixText: String?) {
        this.suffixText = suffixText
        this.invalidate()
    }

    fun getPrefixText(): String? {
        return prefixText
    }

    fun setPrefixText(prefixText: String?) {
        this.prefixText = prefixText
        this.invalidate()
    }

    fun getInnerBackgroundColor(): Int {
        return innerBackgroundColor
    }

    fun setInnerBackgroundColor(innerBackgroundColor: Int) {
        this.innerBackgroundColor = innerBackgroundColor
        this.invalidate()
    }

    fun getInnerBottomText(): String? {
        return innerBottomText
    }

    fun setInnerBottomText(innerBottomText: String?) {
        this.innerBottomText = innerBottomText
        this.invalidate()
    }

    fun getInnerBottomTextSize(): Float {
        return innerBottomTextSize
    }

    fun setInnerBottomTextSize(innerBottomTextSize: Float) {
        this.innerBottomTextSize = innerBottomTextSize
        this.invalidate()
    }

    fun getInnerBottomTextColor(): Int {
        return innerBottomTextColor
    }

    fun setInnerBottomTextColor(innerBottomTextColor: Int) {
        this.innerBottomTextColor = innerBottomTextColor
        this.invalidate()
    }

    fun getStartingDegree(): Int {
        return startingDegree
    }

    fun setStartingDegree(startingDegree: Int) {
        this.startingDegree = startingDegree
        this.invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec))

        //TODO calculate inner circle height and then position bottom text at the bottom (3/4)
        innerBottomTextHeight = (height - height * 3 / 4).toFloat()
    }

    private fun measure(measureSpec: Int): Int {
        var result: Int
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        if (mode == MeasureSpec.EXACTLY) {
            result = size
        } else {
            result = min_size
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size)
            }
        }
        return result
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val delta = Math.max(finishedStrokeWidth, unfinishedStrokeWidth)
        finishedOuterRect[delta, delta, width - delta] = height - delta
        unfinishedOuterRect[delta, delta, width - delta] = height - delta
        val innerCircleRadius =
            (width - Math.min(finishedStrokeWidth, unfinishedStrokeWidth) + Math.abs(
                finishedStrokeWidth - unfinishedStrokeWidth
            )) / 2f
        canvas.drawCircle(
            width / 2.0f, height / 2.0f, innerCircleRadius,
            innerCirclePaint!!
        )
        canvas.drawArc(
            finishedOuterRect, getStartingDegree().toFloat(),
            progressAngle, false, finishedPaint!!
        )
        canvas.drawArc(
            unfinishedOuterRect, getStartingDegree() + progressAngle, 360 - progressAngle, false,
            unfinishedPaint!!
        )
        if (isShowText) {
            val text = if (text != null) text else prefixText + progress + suffixText
            if (!TextUtils.isEmpty(text)) {
                val textHeight = textPaint!!.descent() + textPaint!!.ascent() - 10
                canvas.drawText(
                    text!!,
                    (width - textPaint!!.measureText(text)) / 2.0f,
                    (width - textHeight) / 2.0f,
                    textPaint!!
                )
            }
            if (!TextUtils.isEmpty(getInnerBottomText())) {
                innerBottomTextPaint!!.textSize = innerBottomTextSize
                val bottomTextBaseline =
                    height - innerBottomTextHeight - (textPaint!!.descent() + textPaint!!.ascent()) / 2
                canvas.drawText(
                    getInnerBottomText()!!,
                    (width - innerBottomTextPaint!!.measureText(getInnerBottomText())) / 2.0f,
                    bottomTextBaseline,
                    innerBottomTextPaint!!
                )
            }
        }
        if (attributeResourceId != 0) {
            var bitmap: Bitmap? = null
            bitmap = BitmapFactory.decodeResource(resources, attributeResourceId)
            if (bitmap == null) bitmap = getBitmapFromVectorDrawable(
                context, attributeResourceId
            )
            if (bitmap != null) canvas.drawBitmap(
                bitmap,
                (width - bitmap.width) / 2.0f,
                (height - bitmap.height) / 2.0f,
                null
            )
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState())
        bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor())
        bundle.putFloat(INSTANCE_TEXT_SIZE, getTextSize())
        bundle.putFloat(INSTANCE_INNER_BOTTOM_TEXT_SIZE, getInnerBottomTextSize())
        bundle.putFloat(INSTANCE_INNER_BOTTOM_TEXT_COLOR, getInnerBottomTextColor().toFloat())
        bundle.putString(INSTANCE_INNER_BOTTOM_TEXT, getInnerBottomText())
        bundle.putInt(INSTANCE_INNER_BOTTOM_TEXT_COLOR, getInnerBottomTextColor())
        bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, getFinishedStrokeColor())
        bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, getUnfinishedStrokeColor())
        bundle.putInt(INSTANCE_GRADIENT_COLOR_ONE, getGradientColorOne())
        bundle.putInt(INSTANCE_GRADIENT_COLOR_TWO, getGradientColorTwo())
        bundle.putInt(INSTANCE_MAX, getMax())
        bundle.putInt(INSTANCE_STARTING_DEGREE, getStartingDegree())
        bundle.putFloat(INSTANCE_PROGRESS, getProgress())
        bundle.putString(INSTANCE_SUFFIX, getSuffixText())
        bundle.putString(INSTANCE_PREFIX, getPrefixText())
        bundle.putString(INSTANCE_TEXT, getText())
        bundle.putFloat(INSTANCE_FINISHED_STROKE_WIDTH, getFinishedStrokeWidth())
        bundle.putFloat(INSTANCE_UNFINISHED_STROKE_WIDTH, getUnfinishedStrokeWidth())
        bundle.putInt(INSTANCE_BACKGROUND_COLOR, getInnerBackgroundColor())
        bundle.putInt(INSTANCE_INNER_DRAWABLE, attributeResourceId)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is Bundle) {
            val bundle = state
            textColor = bundle.getInt(INSTANCE_TEXT_COLOR)
            textSize = bundle.getFloat(INSTANCE_TEXT_SIZE)
            innerBottomTextSize = bundle.getFloat(INSTANCE_INNER_BOTTOM_TEXT_SIZE)
            innerBottomText = bundle.getString(INSTANCE_INNER_BOTTOM_TEXT)
            innerBottomTextColor = bundle.getInt(INSTANCE_INNER_BOTTOM_TEXT_COLOR)
            finishedStrokeColor = bundle.getInt(INSTANCE_FINISHED_STROKE_COLOR)
            unfinishedStrokeColor = bundle.getInt(INSTANCE_UNFINISHED_STROKE_COLOR)
            gradientColorOne = bundle.getInt(INSTANCE_GRADIENT_COLOR_ONE)
            gradientColorTwo = bundle.getInt(INSTANCE_GRADIENT_COLOR_TWO)
            finishedStrokeWidth = bundle.getFloat(INSTANCE_FINISHED_STROKE_WIDTH)
            unfinishedStrokeWidth = bundle.getFloat(INSTANCE_UNFINISHED_STROKE_WIDTH)
            innerBackgroundColor = bundle.getInt(INSTANCE_BACKGROUND_COLOR)
            attributeResourceId = bundle.getInt(INSTANCE_INNER_DRAWABLE)
            initPainters()
            setMax(bundle.getInt(INSTANCE_MAX))
            setStartingDegree(bundle.getInt(INSTANCE_STARTING_DEGREE))
            setProgress(bundle.getFloat(INSTANCE_PROGRESS))
            prefixText = bundle.getString(INSTANCE_PREFIX)
            suffixText = bundle.getString(INSTANCE_SUFFIX)
            text = bundle.getString(INSTANCE_TEXT)
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE))
            return
        }
        super.onRestoreInstanceState(state)
    }

    fun setAdProgress(percent: Int) {
        if (percent >= 0) {
            setProgress(percent.toFloat())
        }
    }

    companion object {
        private const val INSTANCE_STATE = "saved_instance"
        private const val INSTANCE_TEXT_COLOR = "text_color"
        private const val INSTANCE_TEXT_SIZE = "text_size"
        private const val INSTANCE_TEXT = "text"
        private const val INSTANCE_INNER_BOTTOM_TEXT_SIZE = "inner_bottom_text_size"
        private const val INSTANCE_INNER_BOTTOM_TEXT = "inner_bottom_text"
        private const val INSTANCE_INNER_BOTTOM_TEXT_COLOR = "inner_bottom_text_color"
        private const val INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color"
        private const val INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color"
        private const val INSTANCE_GRADIENT_COLOR_ONE = "gradientColorOne"
        private const val INSTANCE_GRADIENT_COLOR_TWO = "gradientColorTwo"
        private const val INSTANCE_MAX = "max"
        private const val INSTANCE_PROGRESS = "progress"
        private const val INSTANCE_SUFFIX = "suffix"
        private const val INSTANCE_PREFIX = "prefix"
        private const val INSTANCE_FINISHED_STROKE_WIDTH = "finished_stroke_width"
        private const val INSTANCE_UNFINISHED_STROKE_WIDTH = "unfinished_stroke_width"
        private const val INSTANCE_BACKGROUND_COLOR = "inner_background_color"
        private const val INSTANCE_STARTING_DEGREE = "starting_degree"
        private const val INSTANCE_INNER_DRAWABLE = "inner_drawable"
        fun getBitmapFromVectorDrawable(context: Context?, drawableId: Int): Bitmap {
            var drawable = ContextCompat.getDrawable(context!!, drawableId)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                drawable = DrawableCompat.wrap(drawable!!).mutate()
            }
            val bitmap = Bitmap.createBitmap(
                drawable!!.intrinsicWidth,
                drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
    }

    init {
        default_text_size = Utils.sp2px(resources, 15.0f)
        min_size = Utils.dp2px(resources, 100.0f).toInt()
        default_stroke_width = Utils.dp2px(resources, 10.0f)
        default_inner_bottom_text_size = Utils.sp2px(resources, 18.0f)
        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AdCircleProgress,
            defStyleAttr,
            0
        )
        initByAttributes(attributes)
        attributes.recycle()
        initPainters()
    }
}