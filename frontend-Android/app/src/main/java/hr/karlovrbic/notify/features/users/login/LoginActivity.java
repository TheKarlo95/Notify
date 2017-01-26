package hr.karlovrbic.notify.features.users.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

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
import hr.karlovrbic.notify.utils.InputUtil;
import hr.karlovrbic.notify.utils.SharedPrefsUtils;

public class LoginActivity extends BaseView implements ILogin.View {

    private static final String EXTRA_RUN_ANIMATION = "run_animation";

    @BindView(R.id.iv_text_logo)
    ImageView ivTextLogo;
    @BindView(R.id.cd_login_form)
    CardView cdLoginForm;
    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @Inject
    ILogin.Presenter presenter;

    @NonNull
    public static Intent buildIntent(@NonNull Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(EXTRA_RUN_ANIMATION, false);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setEditorListenerOnPassword();

        boolean animate = true;
        if (getIntent() != null) {
            animate = getIntent().getBooleanExtra(EXTRA_RUN_ANIMATION, true);
        }

        if (savedInstanceState == null && animate) {
            animate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        if (presenter != null) {
            presenter.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        presenter.cancel();

        super.onBackPressed();
    }

    @Override
    public void loginSuccessful(User user) {
        if (user.getToken() == null) {
            String token = FirebaseInstanceId.getInstance().getToken();
            user.setToken(token);
            presenter.updateToken(user.getId(), token);
        }

        SharedPrefsUtils.saveUser(getApplicationContext(), user);
        startActivity(MainActivity.buildIntent(this));
    }

    @OnClick(R.id.btn_login)
    public void loginClicked() {
        String username = InputUtil.getUsernameText(tilUsername);
        String password = InputUtil.getPasswordText(tilPassword);

        if (username != null && password != null) {
            presenter.login(username, password);
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void signUpClicked() {
        startActivity(SignUpActivity.buildIntent(this));
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new LoginModule(this)).inject(this);
    }

    private void animate() {
        ivTextLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.intro_text_logo));
        cdLoginForm.startAnimation(AnimationUtils.loadAnimation(this, R.anim.intro_login_form));
    }
    private void setEditorListenerOnPassword(){
        EditText etPassword = tilPassword.getEditText();
        if(etPassword != null) {
            etPassword.setOnEditorActionListener(new EditorActionListener());
        }
    }

    private class EditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                LoginActivity.this.loginClicked();

                return true;
            }
            return false;
        }
    }
}
