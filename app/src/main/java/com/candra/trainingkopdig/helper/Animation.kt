package com.candra.trainingkopdig.helper

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.candra.trainingkopdig.R
import com.candra.trainingkopdig.databinding.DialogFragmentCartBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

object Animation {


    const val EXTRA_DATA = "data"

    fun animationCardView(cardView: MaterialCardView)
    {
      ObjectAnimator.ofFloat(cardView,View.ALPHA,1f).apply {
          setDuration(500)
      }.start()
    }

    fun progressBarShow(progressBar: ProgressBar, isShow: Boolean,recyclerView: RecyclerView){
        progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isShow) View.GONE else View.VISIBLE
    }


    fun appInstalled(context: Context,uri: String): Boolean{
        val appInstalled: Boolean = try {
            val packageManager = context.packageManager
            packageManager.getPackageInfo(uri,PackageManager.GET_META_DATA)
            true
        }catch (e: PackageManager.NameNotFoundException){
           val errorData =  e.printStackTrace()
            Log.d("Error", "appInstalled: ${errorData.toString()}")
            false
        }
        return appInstalled
    }
}