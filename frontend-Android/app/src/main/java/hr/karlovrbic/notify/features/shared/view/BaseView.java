package hr.karlovrbic.notify.features.shared.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import hr.karlovrbic.notify.AndroidApplication;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.features.shared.IBase;

/**
 * Created by thekarlo95 on 21.01.17..
 */

public abstract class BaseView extends AppCompatActivity implements IBase.View {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(((AndroidApplication) getApplication()).getApplicationComponent());
        initProgressDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        progressDialog.cancel();
        progressDialog = null;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(@StringRes int message) {
        showMessage(getString(message));
    }

    @Override
    public void showLoading(String message) {
        if (progressDialog != null) {
            progressDialog.hide();
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    @Override
    public void showLoading(@StringRes int id) {
        showLoading(getString(id));
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

    protected abstract void injectDependencies(AppComponent appComponent);

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
    }
}

