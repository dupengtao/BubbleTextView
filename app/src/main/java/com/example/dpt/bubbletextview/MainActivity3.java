package com.example.dpt.bubbletextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.dpt.bubbletextview.helper.LeBubbleTextViewHelper;
import com.example.dpt.bubbletextview.widget.LeBubbleTitleTextView;


public class MainActivity3 extends Activity {


    private boolean inited;
    private Button bt1,bt2,bt3,bt4;
    private LeBubbleTextViewHelper helper1;
    private LeBubbleTextViewHelper helper2;
    private LeBubbleTextViewHelper helper3;
    private LeBubbleTextViewHelper helper4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);

        bt1 = (Button) findViewById(R.id.bt);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper1.show();
            }
        });

        bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper2.show();
            }
        });

        bt3 = (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper3.show();
            }
        });

        bt4 = (Button) findViewById(R.id.bt4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper4.show();
                Toast.makeText(MainActivity3.this,"button 4",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && !inited){
            inited = true;

            helper1 = new LeBubbleTextViewHelper();
            helper1.init(bt1, R.layout.view_demo_bubble_title1);
            helper1.show();
            //helper1.getBubbleView().setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        helper1.dismissBubblePopupWindow();
            //    }
            //});
            LeBubbleTitleTextView bubbleTitleTextView1 = (LeBubbleTitleTextView) helper1.getBubbleView();
            bubbleTitleTextView1.setCancelImageOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper1.dismissBubblePopupWindow();
                }
            });

            helper2 = new LeBubbleTextViewHelper();
            helper2.init(bt2, R.layout.view_demo_bubble_title2);
            helper2.show();
            //helper2.getBubbleView().setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        helper2.dismissBubblePopupWindow();
            //    }
            //});
            LeBubbleTitleTextView bubbleTitleTextView2 = (LeBubbleTitleTextView) helper2.getBubbleView();
            bubbleTitleTextView2.setCancelImageOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper2.dismissBubblePopupWindow();
                }
            });

            helper3 = new LeBubbleTextViewHelper();
            helper3.init(bt3, R.layout.view_demo_bubble3);
            helper3.show();
            helper3.getBubbleView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper3.dismissBubblePopupWindow();
                }
            });

            helper4 = new LeBubbleTextViewHelper();
            helper4.init(bt4, R.layout.view_demo_bubble4);
            helper4.show();
            helper4.getBubbleView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper4.dismissBubblePopupWindow();
                }
            });

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
