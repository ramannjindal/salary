
package com.raman.salary.utils

import android.util.Log
import com.raman.salary.SalaryApp

class SLog {
    /**
     * D.
     *
     * @param tag the tag
     * @param msg the msg
     */
    companion object {
        fun d(tag: String, msg: String) {
            if (SalaryApp.getInstance()!!.isDebuggingEnabled) {
                Log.d(tag, msg)
            }
        }


        /**
         * E.
         *
         * @param tag the tag
         * @param msg the msg
         */
        fun e(tag: String, msg: String) {
            if (SalaryApp.getInstance()!!.isDebuggingEnabled) {
                //            error(tag,msg);
                Log.e(tag, msg)
            }
        }

        /**
         * .
         *
         * @param tag the tag
         * @param msg the msg
         */
        fun i(tag: String, msg: String) {
            if (SalaryApp.getInstance()!!.isDebuggingEnabled) {
                Log.i(tag, msg)
            }
        }

        /**
         * V.
         *
         * @param tag the tag
         * @param msg the msg
         */
        fun v(tag: String, msg: String) {
            if (SalaryApp.getInstance()!!.isDebuggingEnabled) {
                Log.v(tag, msg)
            }
        }
    }
}