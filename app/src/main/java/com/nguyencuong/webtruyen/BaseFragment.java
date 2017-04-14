package com.nguyencuong.webtruyen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nguyencuong.webtruyen.util.LogUtils;
import com.nguyencuong.webtruyen.widget.dialog.DialogLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 *
 * Created by Mr Cuong on 2/18/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    protected DialogLoading dialogLoading;

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
        LogUtils.d("BaseFragment", "onCreateView");
        return v;
    }

    @Override
    public void onDestroyView() {
        dialogLoading.dismiss();
        dialogLoading = null;
        unbinder.unbind();
        super.onDestroyView();
    }

    protected abstract int getFragmentLayout();

    protected void showToast(String mes) {
        Toast.makeText(getActivity().getApplicationContext(), mes, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int idRes) {
        Toast.makeText(getActivity().getApplicationContext(), getString(idRes), Toast.LENGTH_SHORT).show();
    }

    protected void showToastError(@StringRes int messageId) {
        showToastError(getString(messageId));
    }

    protected void showToastError(String mes) {
        Toasty.error(getActivity().getApplicationContext(), mes + "", Toast.LENGTH_LONG).show();
    }

    protected void showToastSuccess(@StringRes int messageId) {
        showToastSuccess(getString(messageId));
    }

    protected void showToastSuccess(String mes) {
        Toasty.success(getActivity().getApplicationContext(), mes + "", Toast.LENGTH_SHORT).show();
    }
}
