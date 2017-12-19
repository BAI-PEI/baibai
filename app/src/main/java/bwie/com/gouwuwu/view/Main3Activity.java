package bwie.com.gouwuwu.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bwie.com.gouwuwu.R;
import bwie.com.gouwuwu.bean.CartsBean;
import bwie.com.gouwuwu.bean.DuiXinag;
import bwie.com.gouwuwu.utils.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main3Activity extends AppCompatActivity {
    private ExAdapter adapter;
    private ExpandableListView elv;
    private CheckBox checkBox;
    private TextView tvTotal;
    private TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tvTotal = findViewById(R.id.tvTotal);
        elv = findViewById(R.id.elv);
        checkBox = findViewById(R.id.cb);
        tvCount=findViewById(R.id.tvCount);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setCheAll(checkBox.isChecked());
            }
        });

        //去除小箭头
        elv.setGroupIndicator(null);
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/getCarts?uid=71&source=android", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        CartsBean gson = new Gson().fromJson(string, CartsBean.class);
                        List<CartsBean.DataBean> data = gson.getData();
                        data.remove(0);
                        List<List<CartsBean.DataBean.ListBean>> list=new ArrayList<>();
                        for(int i=0;i<data.size();i++){
                            list.add(data.get(i).getList());
                        }
                        adapter = new ExAdapter(Main3Activity.this, data,list);
                        elv.setAdapter(adapter);
                        int elvCount = elv.getCount();
                        for (int i=0; i<elvCount; i++)
                        {
                            elv.expandGroup(i);
                        };
                    }
                });
            }
        });
    }
    public void setCheckAll(boolean checkAll) {
        checkBox.setChecked(checkAll);


    }

    public void PriceAndCount(DuiXinag duiXinag) {
        tvTotal.setText("合计:"+duiXinag.getPrice());
        tvCount.setText("去结算(" + duiXinag.getCount() + ")");
    }
}
