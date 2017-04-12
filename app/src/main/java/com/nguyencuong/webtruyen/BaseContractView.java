package com.nguyencuong.webtruyen;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/13/2017.
 * Email: vancuong2941989@gmail.com
 */

public interface BaseContractView<T> {

    void setPresenter(T Presenter);

    void showLoading(boolean show);

    void showMsgError(boolean show, String msg);

    void showMsgToast(String msg);
}
