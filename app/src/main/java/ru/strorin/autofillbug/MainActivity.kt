package ru.strorin.autofillbug

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.requestResultView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        promptButton.setOnClickListener {
            promptSelection(this, 123)
        }
    }

    override fun onResume() {
        super.onResume()
        textView.text = this.isMyAutofillServiceSelected().toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun promptSelection(activity: Activity, requestCode: Int) {
        val selectionIntent = Intent(Settings.ACTION_REQUEST_SET_AUTOFILL_SERVICE).apply {
            data = Uri.parse("package:${applicationContext.packageName}")
        }

        val activityExists = selectionIntent.resolveActivityInfo(applicationContext.packageManager, 0) != null

        try {
            activity.startActivityForResult(selectionIntent, requestCode)
        } catch (exception: ActivityNotFoundException) {
        }
    }
}
