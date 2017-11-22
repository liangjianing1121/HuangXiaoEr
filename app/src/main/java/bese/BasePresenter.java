package bese;

/**
 * Created by DELL on 2017/11/17.
 */

public class BasePresenter<V> {
    public V mView;


    /**
     * presenter与view层绑定
     * @param mView
     */
    public BasePresenter(V mView) {
        this.mView = mView;
    }

    /**
     * 解绑
     */
    public void  deatach() {
        this.mView = null;
    }


}
