package com.jiehun.veigar.pathdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiehun.veigar.pathdemo.pathmeasure.PathMeasureView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new PathMeasureView(this));
        setContentView(R.layout.layout_view);
    }

//    public void btn(View view) {
//        DragBubbleView drag = findViewById(R.id.drag);
//        //com.jiehun.veigar.pathdemo.widget.DragBubbleView drag = findViewById(R.id.drag);
//        drag.init();
//    }
}
