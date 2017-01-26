package hr.karlovrbic.notify.features.users.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.SignUpModule;
import hr.karlovrbic.notify.features.shared.view.BaseView;
import hr.karlovrbic.notify.features.users.login.LoginActivity;
import hr.karlovrbic.notify.utils.InputUtil;

public class SignUpActivity extends BaseView implements ISignUp.View {

    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.til_password_confirmation)
    TextInputLayout tilPasswordConfirmation;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_surname)
    TextInputLayout tilSurname;
    @BindView(R.id.dp_birthday)
    DatePicker dpBirthday;

    @Inject
    ISignUp.Presenter presenter;

    @NonNull
    public static Intent buildIntent(@NonNull Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        dpBirthday.setMaxDate(getMaxDate().getTime());
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        if (presenter != null) {
            presenter.cancel();
        }
    }

    @OnClick(R.id.btn_cancel)
    @Override
    public void signUpSuccessful() {
        startActivity(LoginActivity.buildIntent(this));
    }

    @SuppressWarnings("ConstantConditions")
    @OnClick(R.id.btn_sign_up)
    public void signUpClicked() {
        String username = InputUtil.getUsernameText(tilUsername);
        String password = InputUtil.getPasswordText(tilPassword);
        String passwordConfirmation = InputUtil.getPasswordConfiramtionText(tilPasswordConfirmation);
        String email = InputUtil.getEmailText(tilEmail);
        String name = InputUtil.getNameText(tilName);
        String surname = InputUtil.getSurnameText(tilSurname);
        Date birthday = InputUtil.getBirthdayDate(dpBirthday);

        if (username != null &&
                password != null &&
                passwordConfirmation != null &&
                email != null &&
                name != null &&
                surname != null) {
            presenter.signUp(username,
                    password,
                    passwordConfirmation,
                    email,
                    name,
                    surname,
                    birthday);
        }
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new SignUpModule(this)).inject(this);
    }

    @NonNull
    private static Date getMaxDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18);
        return calendar.getTime();
    }
}
