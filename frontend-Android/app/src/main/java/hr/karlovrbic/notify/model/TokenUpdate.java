package hr.karlovrbic.notify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TokenUpdate {

    private static final String ATTRIBUTE_TOKEN = "fcm_token";

    @SerializedName(ATTRIBUTE_TOKEN)
    @Expose
    private String token;

    public TokenUpdate(String token) {
        this.token = token;
    }

    public TokenUpdate() {
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TokenUpdate that = (TokenUpdate) o;

        return new EqualsBuilder()
                .append(token, that.token)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(token)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "TokenUpdate{" +
                "token='" + token + '\'' +
                '}';
    }
}