package ru.strorin.autofillbug

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.autofill.AutofillManager

@TargetApi(Build.VERSION_CODES.O)
fun Context.isMyAutofillServiceSelected(): Boolean {
    return getSystemService(AutofillManager::class.java)
        ?.hasEnabledAutofillServices()
        ?: false
}