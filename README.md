# AutofillBug

Sample for bug https://issuetracker.google.com/issues/152145656

Device used: Pixel, Android 10

Steps to reproduce:
1. Install app, that implements an autofill service
2. Run this app and enable its autofill service in settings
   (at this point hasEnabledAutofillServices() method returns true as expected)
3. DO NOT USE AUTOFILL IN OTHER APPS
4. Update app that implements an autofill service (with increasing version code)
5. Open app and check what is the output of hasEnabledAutofillServices() method

Expected output: hasEnabledAutofillServices() still returns true

Current output: hasEnabledAutofillServices() returns false despite the fact autofill services is enabled (could be seen in device settings)
The result changes to true after first using of autofill in some other app

Frequency: >90%
