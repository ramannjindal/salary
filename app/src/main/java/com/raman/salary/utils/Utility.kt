package com.raman.salary.utils

import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.raman.salary.SalaryApp
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

object Utility {
    /**
     * Adjust alpha of given color
     */
    fun adjustAlpha(@ColorInt color: Int, factor: Float): Int {
        val alpha = (Color.alpha(color) * factor).roundToInt()
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    fun getColor(@ColorRes colorResId: Int): Int {
        return ContextCompat.getColor(SalaryApp.getGlobalAppContext(), colorResId)
    }

    fun addOrReplaceFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String,
        isAddFragment: Boolean = false,
        isAddBackStack: Boolean = false
    ) {
        val transaction = fragmentManager.beginTransaction()
        if (isAddFragment)
            transaction.add(containerId, fragment, tag)
        else
            transaction.replace(containerId, fragment, tag)
        if (fragmentManager.fragments.isNotEmpty() && isAddBackStack)
            transaction.addToBackStack(tag)
        transaction.commit()
    }

    fun fromHtml(html: String?): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    fun readRawFile(rawId: Int): String {
        val inputStream: InputStream = SalaryApp.getGlobalAppContext().resources.openRawResource(rawId)
        val byteArrayOutputStream = ByteArrayOutputStream()
        var i: Int
        try {
            i = inputStream.read()
            while (i != -1) {
                byteArrayOutputStream.write(i)
                i = inputStream.read()
            }
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return byteArrayOutputStream.toString()
    }

    fun convertToUserFriendlyTime(milliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds))
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))
        var time = if (hours.toInt() == 0) "" else "$hours ${if (hours == 1L) "hour " else "hours "}"
        time += if (minutes.toInt() == 0) "" else "$minutes ${if (minutes == 1L) "minute " else "minutes"} "
        time += if (seconds.toInt() == 0) "" else "$seconds ${if (seconds == 1L) "second" else "seconds"}"
        return time
    }

    fun getCountryLocale(): String =
        if (Locale.getDefault().language.equals("en", ignoreCase = true)) Locale.getDefault().language else "${Locale.getDefault().language}-${Locale.getDefault().country}"

}