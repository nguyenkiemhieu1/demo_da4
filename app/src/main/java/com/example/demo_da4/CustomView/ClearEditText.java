package com.example.demo_da4.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.example.demo_da4.R;

public class ClearEditText extends AppCompatEditText {
    Drawable crossx, nonecrossx;
    Boolean visible = false;
    Drawable drawable;
    public ClearEditText(Context context) {
        super(context);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao();
    }
//    private void khoitao(){
//        crossx= ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
//        nonecrossx=ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
//
//    }
//    private  void cauhinh(){
//        setInputType(InputType.TYPE_CLASS_TEXT);
//        Drawable [] drawables=getCompoundDrawables();
////                           nếu visible = true hiển thị chũ X
//        drawable = visible? crossx : nonecrossx;
//        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable,drawables[3]);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if(MotionEvent.ACTION_DOWN == event.getAction() && event.getX() >= (getRight()-drawable.getBounds().width())){

//            setText("");
//        }
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
//        super.onTextChanged(text, start, lengthBefore, lengthAfter);
//        if(lengthAfter == 0 && start ==0){
//            visible = false;
//            cauhinh();
//        }else {
//            visible = true;
//            cauhinh();
//        }
//    }
private void khoitao(){
    crossx = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
    nonecrossx = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();

    cauhinh();
}

    private void cauhinh(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        //                         nếu visible = true hiển thị chũ X
        drawable = visible? crossx:nonecrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_DOWN == event.getAction() && event.getX() >= (getRight() - drawable.getBounds().width())){
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter == 0 && start == 0){
            visible = false;
            cauhinh();
        }else{
            visible = true;
            cauhinh();
        }

    }

}
