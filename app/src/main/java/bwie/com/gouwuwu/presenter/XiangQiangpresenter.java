package bwie.com.gouwuwu.presenter;

import bwie.com.gouwuwu.model.Xiangmodel;
import bwie.com.gouwuwu.view.XiangCallBack;

/**
 * Created by BAIPEI on 2017/12/15.
 */

public class XiangQiangpresenter {
    XiangCallBack callBack;
    Xiangmodel xiangmodel;
    public XiangQiangpresenter(XiangCallBack callBack) {
        this.callBack=callBack;
        this.xiangmodel=new Xiangmodel();
    }

    public void getData(String pid) {
        xiangmodel.getData(pid, new XiangQCallBack() {
            @Override
            public void success(String s) {
                callBack.success(s);
            }

            @Override
            public void error(Exception e) {
                callBack.error(e);
            }
        });
    }
    XiangQCallBack xiangQCallBack;
    public void setXiangQCallBack(XiangQCallBack xiangQCallBack){
        this.xiangQCallBack=xiangQCallBack;
    }

    public interface  XiangQCallBack{
        void  success(String s);
        void  error(Exception e);
    }

}
