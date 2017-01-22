package hr.karlovrbic.notify.features.users.signup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.karlovrbic.notify.R;
import hr.karlovrbic.notify.dagger.components.AppComponent;
import hr.karlovrbic.notify.dagger.modules.SignUpModule;
import hr.karlovrbic.notify.features.shared.view.BaseView;
import hr.karlovrbic.notify.features.users.login.LoginActivity;

public class SignUpActivity extends BaseView implements ISignUp.View {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_confirmation)
    EditText etPasswordConfirmation;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_surname)
    EditText etSurname;
    @BindView(R.id.et_birthday)
    EditText etBirthday;

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

        etBirthday.setRawInputType(InputType.TYPE_NULL);
        etBirthday.setTextIsSelectable(true);
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        if (presenter != null) {
            presenter.cancel();
        }
    }

    @Override
    public void signUpSuccessful() {
        startActivity(LoginActivity.buildIntent(this));
    }

    @OnClick(R.id.btn_sign_up)
    public void signUpClicked() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passwordConfirmation = etPasswordConfirmation.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String surname = etSurname.getText().toString().trim();
        String birthday = etBirthday.getText().toString().trim();


        presenter.signUp(username,
                password,
                passwordConfirmation,
                email,
                name,
                surname,
                birthday);
    }

    @OnClick(R.id.et_birthday)
    public void onBirthdayClicked(){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new SignUpModule(this)).inject(this);
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Activity activity = getActivity();
            if(activity != null && activity instanceof SignUpActivity) {
                ((SignUpActivity) activity).etBirthday.setText(day + "." + (month + 1) + "." + year + ".");
            }
        }
    }
}
