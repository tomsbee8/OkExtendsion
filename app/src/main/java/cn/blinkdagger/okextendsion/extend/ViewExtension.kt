package cn.blinkdagger.okextendsion.extend

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder


fun View?.dp2px(dp: Int): Int = dp2px(dp.toFloat())
fun View?.dp2px(dp: Float): Int = this?.context?.dp2px(dp)?:0

fun View?.sp2px(sp: Int): Int = sp2px(sp.toFloat())
fun View?.sp2px(sp: Float): Int = this?.context?.sp2px(sp)?: 0


fun View?.bg(id: Int) {
    this?.context?.let {
        this.background = ContextCompat.getDrawable(it, id)
    }
}

fun View?.bgColor(@ColorRes id: Int): View? {
    this?.context?.let {
        this.setBackgroundColor(ContextCompat.getColor(it, id))
    }
    return this
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.setVisible(show: Boolean): View? {
    if (show) this?.visible() else this?.invisible()
    return this
}

fun View?.noPadding() {
    this?.setPadding(0, 0, 0, 0)
}

fun View?.bottomPadding(bottom: Int) {
    this?.setPadding(0, 0, 0, bottom)
}

fun View?.topPadding(top: Int) {
    this?.setPadding(0, top, 0, 0)
}

fun View?.padding(left: Int, top: Int, right: Int, bottom: Int): View? {
    this?.setPadding(left, top, right, bottom)
    return this
}

fun View?.padding(padding: Int): View? {
    this?.setPadding(padding, padding, padding, padding)
    return this
}

fun View?.safePost(runnable: Runnable?) {
    safePostDelayed(runnable, 0)
}

fun View?.safePostDelayed(runnable: Runnable?, delayMills: Long) {
    this?.postDelayed({
        this.context?.aliveActivity()?.let { runnable?.run() }
    }, delayMills)
}

fun View?.addOnGlobalLayoutListener(block: () -> Unit){
    val view = this
    val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            view?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
            block()
        }
    }
    this?.viewTreeObserver?.addOnGlobalLayoutListener(listener)
}


fun TextView?.color(id: Int) {
    this?.context?.let {
        this.setTextColor(ContextCompat.getColor(it, id))
    }
}

fun TextView?.size(sizeSp: Float): TextView? {
    this?.textSize = sizeSp
    return this
}

// 设置TextView下划线
fun TextView?.underLine(): TextView? {
    return paintFlags(Paint.UNDERLINE_TEXT_FLAG)
}

// 设置TextView中划线
fun TextView?.strikeThrough(): TextView? {
    return paintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
}

fun TextView?.paintFlags(flag: Int): TextView? {
    this?.paintFlags = flag
    return this
}

fun TextView?.topDrawable(id: Int) {
    this?.setCompoundDrawablesWithIntrinsicBounds(0, id, 0, 0)
}

fun TextView?.rightDrawable(id: Int) {
    this?.setCompoundDrawablesWithIntrinsicBounds(0, 0, id, 0)
}

fun TextView?.rightDrawable(drawable: Drawable) {
    this?.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
}

fun TextView?.leftDrawable(id: Int) {
    this?.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0)
}

fun TextView?.noDrawable() {
    this?.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
}

fun TextView?.bgResource(id: Int) {
    this?.setBackgroundResource(id)
}

fun TextView?.text(id: Int) {
    this?.context?.let {
        text = it.getString(id)
    }
}

fun TextView?.text(text: String?) {
    this?.text = text.orEmpty()
}

fun TextView?.bold(): TextView? {
    this?.typeface = Typeface.DEFAULT_BOLD
    return this
}

fun TextView?.normal(): TextView? {
    this?.typeface = Typeface.DEFAULT
    return this
}

fun ImageView.get(url: String?): RequestBuilder<Drawable> = Glide.with(context).load(url)
fun ImageView.get(drawable: Drawable?): RequestBuilder<Drawable> = Glide.with(context).load(drawable)
fun ImageView.get(@DrawableRes resourceId: Int): RequestBuilder<Drawable> = Glide.with(context).load(resourceId)

fun ImageView?.loadGif(url: String?) {
    this?.context?.aliveActivity()?.let {
        Glide.with(it).load(url).into(this)
    }
}

fun ImageView?.loadGif(@DrawableRes resourceId: Int) {
    this?.context?.aliveActivity()?.let {
        Glide.with(it).asGif().load(resourceId).into(this)
    }
}





