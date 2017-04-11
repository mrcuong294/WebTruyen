package com.nguyencuong.webtruyen;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nguyencuong.webtruyen.widget.dialog.DialogLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * Created by Mr Cuong on 2/18/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    protected DialogLoading dialogLoading;

    protected boolean isScreenLand = false;

    protected boolean isLoadingBackPressExit = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getFragmentLayout(), container, false);
        unbinder = ButterKnife.bind(this, v);
        dialogLoading = new DialogLoading(getActivity());
        dialogLoading.setOnPopupLoadingListener(new DialogLoading.OnPopupLoadingListener() {
            @Override
            public void onBackPressed() {
                if (isLoadingBackPressExit) {
                    getActivity().finish();
                }
            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        dialogLoading.dismiss();
        dialogLoading = null;
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isScreenLand = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    protected abstract int getFragmentLayout();

    protected void showToast(String mes) {
        Toast.makeText(getActivity().getApplicationContext(), mes, Toast.LENGTH_SHORT).show();
    }
}
