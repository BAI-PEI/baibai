package bwie.com.gouwuwu.model;

import java.io.IOException;

import bwie.com.gouwuwu.presenter.XiangQiangpresenter;
import bwie.com.gouwuwu.utils.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by BAIPEI on 2017/12/15.
 */

public class Xiangmodel {

    public void getData(String pid, final XiangQiangpresenter.XiangQCallBack xiangQCallBack) {
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/getProductDetail?pid=" + pid+"&source=android", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                xiangQCallBack.error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                xiangQCallBack.success(string);
            }
        });

    }
}
