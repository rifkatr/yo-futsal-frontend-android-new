package futsal.yo.com.yofutsal.Helper;

import android.widget.Button;

import futsal.yo.com.yofutsal.R;

/**
 * Created by rizalsidikp on 17/04/18.
 */

public class ButtonHandler {

    public static Button setDisableButton(Button btn){
        btn.setEnabled(false);
        btn.setText("Loading...");
        return btn;
    }

    public static Button setEnableButton(Button btn, int text){
        btn.setEnabled(true);
        btn.setText(btn.getContext().getResources().getString(text));
        return btn;
    }
}
