package br.com.polaris.saferoutemessenger.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SafeRouteTwilioProperties {

    @Value("${twilio.account_sid}")
    private String account_sid;

    @Value("${twilio.auth_token}")
    private String auth_token;

    @Value("${twilio.from_number_sms}")
    private String from_number_sms;

    @Value("${twilio.from_number}")
    private String from_number;

    public String getAccount_sid() {
        return account_sid;
    }

    public void setAccount_sid(String account_sid) {
        this.account_sid = account_sid;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getFrom_number_sms() {
        return from_number_sms;
    }

    public void setFrom_number_sms(String from_number_sms) {
        this.from_number_sms = from_number_sms;
    }

    public String getFrom_number() {
        return from_number;
    }

    public void setFrom_number(String from_number) {
        this.from_number = from_number;
    }
}
