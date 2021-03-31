package com.raman.salary.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.raman.salary.`interface`.ChangePageCallback
import com.raman.salary.activities.BaseActivity
import com.raman.salary.utils.SLog

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*progressBarHandler = ProgressBarHandler(requireActivity())*/
    }


/*    lateinit var progressBarHandler: ProgressBarHandler
    fun showLoader() {
        progressBarHandler.show()
    }

    fun hideLoader() {
        progressBarHandler.hide()
    }*/

    override fun onResume() {
        super.onResume()
        SLog.i("Current_Fragment: ", "(" + this.javaClass.simpleName + ".kt:0)")
    }

    protected fun getPageChangeCallback(): ChangePageCallback? {
        return when {
            parentFragment is ChangePageCallback -> parentFragment as ChangePageCallback
            context is ChangePageCallback -> context as ChangePageCallback
            else -> null
        }
    }

    fun showToast(message: String?) {
        activity?.let {
            if (it is BaseActivity)
                it.showToast(message)
        }
    }

}