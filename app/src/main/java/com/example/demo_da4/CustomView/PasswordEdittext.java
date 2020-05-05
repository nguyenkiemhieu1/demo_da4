package com.example.demo_da4.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.example.demo_da4.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordEdittext   extends AppCompatEditText {
    Drawable eye, eyeStrike;
//    mặc định là password âẩn
    Boolean visible=false;
    Boolean useStrike=false;
    Drawable drawable;
    int ALPHA = (int ) (255 * .80f);
//    ?=. kiểm tra xem    :   \d là số
//    . là điều kiện  {} bắt buộc 6 dến 20 kí tự
    String MATCHER_PATTERN="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    Pattern pattern;
    Matcher matcher;

    Boolean useValidate=false;

    public PasswordEdittext(Context context) {
        super(context);
        khoitao(null);
    }

    public PasswordEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao(attrs);
    }

    public PasswordEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }
    private  void khoitao(AttributeSet attrs){
        this.pattern=Pattern.compile(MATCHER_PATTERN);

        if(attrs!=null) {
            TypedArray array=getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEdittext, 0,0);

            this.useStrike=array.getBoolean(R.styleable.PasswordEdittext_useStrike, false);
            this.useValidate=array.getBoolean(R.styleable.PasswordEdittext_useValidate, false);
        }
            eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
            eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        if(this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                         String chuoi = getText().toString();

                        TextInputLayout textInputLayout = (TextInputLayout) view.getParent().getParent();
                        matcher = pattern.matcher(chuoi);
                        if( !matcher.matches()){

                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu phải bao gồm 6 kí tự và 1 chữ hoa");

                        }else{

                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }

                    }
                }
            });
        }
            caidat();
    }
    private  void caidat(){
//                                                                        hiển  thị pw                                      k hiển thị
        setInputType(InputType.TYPE_CLASS_TEXT |(visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD:InputType.TYPE_TEXT_VARIATION_PASSWORD));

        Drawable[] drawables=getCompoundDrawables();
//        tạo biến drawable , kiểm tra ndung có  nhập vaod eyeStrike hay k
        drawable=useStrike && !visible? eyeStrike:eye;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
        drawable.setAlpha(ALPHA);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//    if(event.getAction()==MotionEvent.ACTION_up)
//        kiểm tra người dùng ấn vào màn hình
//        kiểm tra ngd co chạm vào con mắt hay k =cách lấy chiều dài bên phải edt- chiều rộng com mắt
        if(event.getAction()==MotionEvent.ACTION_UP&& event.getX() >= (getRight()- drawable.getBounds().width())){
            visible= !visible;
            caidat();
//            kiểm tra lại sự kiện click trong màn hinhgf
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
