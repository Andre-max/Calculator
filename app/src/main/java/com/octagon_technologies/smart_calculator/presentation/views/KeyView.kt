package com.octagon_technologies.smart_calculator.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.octagon_technologies.smart_calculator.R
import com.octagon_technologies.smart_calculator.presentation.util.getColor
import timber.log.Timber

class KeyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): MaterialCardView(context, attrs, defStyleAttr) {

    private lateinit var keyTextView: KeyTextView
    lateinit var key: String

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Timber.d("onMeasure called")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Timber.d("onLayout called")

        updateLayoutParams<MarginLayoutParams> {
            val value = context.getSdpDimen(R.dimen._62sdp).toInt()
            height = value
            width = value

            topMargin = context.getSdpDimen(R.dimen._8sdp).toInt()
        }
        radius = context.getSdpDimen(R.dimen._8sdp)
        cardElevation = context.getSdpDimen(R.dimen._1sdp)

        keyTextView = KeyTextView(context)
        keyTextView.updateLayoutParams {
            height = MarginLayoutParams.MATCH_PARENT
            width = MarginLayoutParams.MATCH_PARENT
        }
        addView(keyTextView)
    }

    fun setText(text: String) { keyTextView.text = text }
    fun setTextColor(colorId: Int) = keyTextView.setTextColor(colorId)
}

private class KeyTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): MaterialTextView(context, attrs, defStyleAttr) {

    init {
        gravity = Gravity.CENTER
        typeface = ResourcesCompat.getFont(context, R.font.cabin_semibold)

        setBackgroundColor(getColor(resources, android.R.color.transparent))
    }
}
