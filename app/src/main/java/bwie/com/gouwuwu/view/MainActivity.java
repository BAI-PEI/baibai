package bwie.com.gouwuwu.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import bwie.com.gouwuwu.R;
import bwie.com.gouwuwu.adapder.MyRecyAdapter;
import bwie.com.gouwuwu.bean.InfoDatas;
import bwie.com.gouwuwu.presenter.RecyPresenter;

public class MainActivity extends AppCompatActivity implements IRecyView{
    private RecyclerView xrv;
    private RecyPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new RecyPresenter(this);
        presenter.showRecy();
    }
        private void initView() {
            xrv =findViewById(R.id.rv);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            xrv.setLayoutManager(manager);
        }
    @Override
    public void showRecy(final InfoDatas infoDatas) {
        MyRecyAdapter myRecyAdapter = new MyRecyAdapter(this, infoDatas);
        xrv.setAdapter(myRecyAdapter);

        myRecyAdapter.setClick(new MyRecyAdapter.Click() {
            @Override
            public void itemclick(int position) {
                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("pid",position+"");
                startActivity(intent);
            }
        });
    }
}
