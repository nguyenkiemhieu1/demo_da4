package com.example.demo_da4.View.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_da4.R;
import com.example.demo_da4.View.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao_layout);

        final Thread  thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);

                }catch (Exception e){

                }finally {

                    Intent iTrangChu=new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(iTrangChu);
                }

            }
        });
        thread.start();
    }
}
