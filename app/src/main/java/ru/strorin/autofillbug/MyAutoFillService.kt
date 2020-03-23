package ru.strorin.autofillbug

import android.annotation.TargetApi
import android.os.Build
import android.os.CancellationSignal
import android.service.autofill.*
import com.example.android.autofillframework.multidatasetservice.datasource.SharedPrefsAutofillRepository

@TargetApi(Build.VERSION_CODES.O)
class MyAutoFillService: AutofillService() {
    override fun onFillRequest(request: FillRequest,
                               cancellationSignal: CancellationSignal,
                               callback: FillCallback) {

        val structure = request.fillContexts[request.fillContexts.size - 1].structure

        val data = request.clientState
        // Parse AutoFill data in Activity
        val parser = StructureParser(structure)
        parser.parseForFill()
        val autofillFields = parser.autofillFields

        val responseBuilder = FillResponse.Builder()

        val clientFormDataMap = SharedPrefsAutofillRepository.getFilledAutofillFieldCollection(this,
            autofillFields.focusedAutofillHints, autofillFields.allAutofillHints)
        val response = AutofillHelper.newResponse(this, false, autofillFields, clientFormDataMap)
            callback.onSuccess(response)
    }

    override fun onSaveRequest(p0: SaveRequest, p1: SaveCallback) {

    }
}