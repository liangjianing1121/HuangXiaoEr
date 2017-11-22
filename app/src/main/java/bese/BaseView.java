package bese;

/**
 * Created by DELL on 2017/11/17.
 */

public interface BaseView {
    //实现公共的方法 所有的接口都需要实现的方法
    void showLoading();
    void hideLoding();
    void showFailure(String msg);
}
