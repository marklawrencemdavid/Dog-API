package com.rensuuuuuuuu.dogapi.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.rensuuuuuuuu.dogapi.R;

public class LoadingOverlayDialog extends Dialog {

    public LoadingOverlayDialog(final Context context) {
        super(context);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setContentView(R.layout.dialog_loading_overlay);
    }
}
