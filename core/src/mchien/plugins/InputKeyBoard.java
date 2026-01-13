package mchien.plugins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import lib2.TField;

public class InputKeyBoard implements Input.TextInputListener {
    TField tField;
    public boolean isShow;
    public InputKeyBoard(TField tField){
        this.tField = tField;
    }
    public void showKeyboard(String title, String defaultText) {
        // Hiển thị hộp thoại nhập liệu với tiêu đề và văn bản mặc định
        isShow = true;
        Gdx.input.getTextInput(this, title, defaultText, "");
    }

    @Override
    public void input(String text) {
        isShow = false;
        tField.setText(text);
        tField.setFocus(false);
        if(tField.ischat)
            tField.okTextBox();
    }

    @Override
    public void canceled() {
        isShow = false;
    }

}
