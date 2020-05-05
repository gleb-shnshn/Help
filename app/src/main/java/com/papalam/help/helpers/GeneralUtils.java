package com.papalam.help.helpers;

import com.muddzdev.styleabletoast.StyleableToast;
import com.papalam.help.App;
import com.papalam.help.R;

public class GeneralUtils {
    public void showError(String errorMessage) {
        new StyleableToast
                .Builder(App.getInstance())
                .text(errorMessage)
                .cornerRadius(5)
                .textSize(13)
                .font(R.font.gilroyl)
                .textColor(App.getInstance().getResources().getColor(R.color.white))
                .backgroundColor(App.getInstance().getResources().getColor(R.color.errorColor))
                .show();
    }

}
