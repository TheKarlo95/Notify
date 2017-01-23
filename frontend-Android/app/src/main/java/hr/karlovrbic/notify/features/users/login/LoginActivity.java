package hr.karlovrbic.notify.features.users.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.LoginModule;
import hr.karlovrbic.notify.features.main.MainActivity;
import hr.karlovrbic.notify.features.shared.view.BaseView;
import hr.karlovrbic.notify.features.users.signup.SignUpActivity;
import hr.karlovrbic.notify.model.User;
import hr.karlovrbic.notify.utils.SharedPrefsUtils;

public class LoginActivity extends BaseView implements ILogin.View {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Inject
    ILogin.Presenter presenter;

    @NonNull
    public static Intent buildIntent(@NonNull Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        if (presenter != null) {
            presenter.cancel();
        }
    }

    @Override
    public void loginSuccessful(User user) {
        SharedPrefsUtils.saveUser(getApplicationContext(), user);
        startActivity(MainActivity.buildIntent(this));
    }

    @OnClick(R.id.btn_login)
    public void loginClicked() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        presenter.login(username, password);
    }

    @OnClick(R.id.btn_sign_up)
    public void signUpClicked() {
        startActivity(SignUpActivity.buildIntent(this));
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new LoginModule(this)).inject(this);
    }
}
