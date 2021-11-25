package br.com.polaris.saferoutemessenger.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MessageRequest {

    @NotNull
    @NotBlank
    private String senderName;

    @NotNull
    private List<String> toNumbers;

    @NotNull
    @NotBlank
    private String latitudeSender;

    @NotNull
    @NotBlank
    private String longitudeSender;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public List<String> getToNumbers() {
        return toNumbers;
    }

    public void setToNumbers(List<String> toNumbers) {
        this.toNumbers = toNumbers;
    }

    public String getLatitudeSender() {
        return latitudeSender;
    }

    public void setLatitudeSender(String latitudeSender) {
        this.latitudeSender = latitudeSender;
    }

    public String getLongitudeSender() {
        return longitudeSender;
    }

    public void setLongitudeSender(String longitudeSender) {
        this.longitudeSender = longitudeSender;
    }
}
