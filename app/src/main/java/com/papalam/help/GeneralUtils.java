package com.papalam.help;

import com.muddzdev.styleabletoast.StyleableToast;

class GeneralUtils {
    public void showError(String errorMessage) {
        new StyleableToast
                .Builder(App.getInstance())
                .text(errorMessage)
                .cornerRadius(5)
                .textSize(13)
                .textColor(App.getInstance().getResources().getColor(R.color.white))
                .backgroundColor(App.getInstance().getResources().getColor(R.color.errorColor))
                .show();
    }

}
