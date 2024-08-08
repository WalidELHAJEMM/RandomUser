package com.wel.randomuser.extension

import android.view.View
import com.wel.randomuser.R
import com.google.android.material.snackbar.Snackbar

internal fun showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
        anchorView = view.rootView.findViewById(R.id.userListFragment)
        show()
    }
}



