package com.example.virtus.a2048;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    private GridView grvGamePlay;
    private OSoAdapter adapter;
    private View.OnTouchListener listener;
    private float X, Y;
    TextView DiemCuaBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DiemCuaBan = (TextView) findViewById(R.id.diemcuaban);
        grvGamePlay = (GridView) findViewById(R.id.grvGamePlay);
        khoiTao();
        setData();
    }

    private void khoiTao() {
        DataGame.getDataGame().intt(MainActivity.this);
        adapter = new OSoAdapter(MainActivity.this, 0, DataGame.getDataGame().getArrSO());

        listener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        X = event.getX();
                        Y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(event.getX() - X) > Math.abs(event.getY() - Y)) {
                            if (event.getX() < X) {
                                DataGame.getDataGame().vuotTrai();
                            } else {
                                DataGame.getDataGame().vuotphai();
                            }

                        } else {
                            if (event.getY() < Y) {
                                DataGame.getDataGame().vuotXuong();
                            } else {
                                DataGame.getDataGame().vuotLen();
                            }
                        }
                        break;
                }
                adapter.notifyDataSetChanged();
                DiemCuaBan.setText(DataGame.getDataGame().CapNhatDiem());
                if (DataGame.getDataGame().KiemTraThang()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Chúc mừng!");
                    builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setMessage("Bạn đã chiến thắng!");
                    builder.create().show();
                }
                if (DataGame.getDataGame().KiemTraThua()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Chia buồn!");
                    builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DataGame.getDataGame().resetGame();
                            dialog.cancel();
                        }
                    });
                    builder.setMessage("Bạn đã thua rồi!");
                    builder.create().show();
                }
                return true;
            }
        };
    }

    private void setData() {
        grvGamePlay.setAdapter(adapter);
        grvGamePlay.setOnTouchListener(listener);
    }


}
