package bwie.com.gouwuwu.model;

import bwie.com.gouwuwu.utils.OkHttpUtils;
import okhttp3.Callback;

/**
 * Created by BAIPEI on 2017/12/13.
 */

public class RecyModel implements IRecyModel {
    @Override
    public void recy(Callback callback) {
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/getProducts?pscid=39&page=1", callback);
    }
}
