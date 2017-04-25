package com.nguyencuong.webtruyen.ui.activity.bookreader;

import com.nguyencuong.webtruyen.BaseContractPresenter;
import com.nguyencuong.webtruyen.BaseContractView;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/25/2017.
 * Email: vancuong2941989@gmail.com
 */

public interface BookReaderContract {

    interface View extends BaseContractView<Presenter> {
        void setViewPagerData();
    }

    interface Presenter extends BaseContractPresenter {

    }
}
