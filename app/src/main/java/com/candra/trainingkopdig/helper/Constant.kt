package com.candra.trainingkopdig.helper

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES

object Constant {

    const val BRI = "1332-01-001142-53-2"
    const val BNI = "0061446556"
    const val BCA = "3491309821"

    const val PEOPLE_1 = "ANTONIUS DANANG SUPANTORO"
    const val PEOPLE_2 = "JUITA SINUHAJI"

    val Context.isDarkMode get() = this.resources?.configuration?.uiMode?.and(UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES

}