package hr.karlovrbic.notify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by Karlo Vrbic on 05.01.17..
 */
public class UserSignUp {

    private static final String ATTRIBUTE_TOKEN = "fcm_token";
    private static final String ATTRIBUTE_USERNAME = "username";
    private static final String ATTRIBUTE_PASSWORD = "password";
    private static final String ATTRIBUTE_PASSWORD_CONFIRMATION = "password_confirmation";
    private static final String ATTRIBUTE_EMAIL = "email";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SURNAME = "surname";
    private static final String ATTRIBUTE_BIRTHDAY = "birthday";


    @SerializedName(ATTRIBUTE_TOKEN)
    @Expose
    private String token;
    @SerializedName(ATTRIBUTE_USERNAME)
    @Expose
    private String username;
    @SerializedName(ATTRIBUTE_PASSWORD)
    @Expose
    private String password;
    @SerializedName(ATTRIBUTE_PASSWORD_CONFIRMATION)
    @Expose
    private String passwordConfirmation;
    @SerializedName(ATTRIBUTE_EMAIL)
    @Expose
    private String email;
    @SerializedName(ATTRIBUTE_NAME)
    @Expose
    private String name;
    @SerializedName(ATTRIBUTE_SURNAME)
    @Expose
    private String surname;
    @SerializedName(ATTRIBUTE_BIRTHDAY)
    @Expose
    private Date birthDay;

    public UserSignUp(String token,
                      String username,
                      String password,
                      String passwordConfirmation,
                      String email,
                      String name,
                      String surname,
                      Date birthDay) {
        this.token =token;
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
    }

    public UserSignUp(String username,
                      String password,
                      String passwordConfirmation,
                      String email,
                      String name,
                      String surname,
                      Date birthDay) {
        this(null, username, password, passwordConfirmation, email, name, surname, birthDay);
    }

    public UserSignUp() {
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserSignUp that = (UserSignUp) o;

        return new EqualsBuilder()
                .append(token, that.token)
                .append(username, that.username)
                .append(password, that.password)
                .append(passwordConfirmation, that.passwordConfirmation)
                .append(email, that.email)
                .append(name, that.name)
                .append(surname, that.surname)
                .append(birthDay, that.birthDay)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(token)
                .append(username)
                .append(password)
                .append(passwordConfirmation)
                .append(email)
                .append(name)
                .append(surname)
                .append(birthDay)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "UserSignUp{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
