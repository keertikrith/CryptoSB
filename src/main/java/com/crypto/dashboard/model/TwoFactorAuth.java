//package com.crypto.dashboard.model;
//
//import com.crypto.dashboard.domain.VerificationType;
//import jakarta.persistence.Embeddable;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Embeddable
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class TwoFactorAuth {
//    private boolean isEnabled= false;
//    private VerificationType sendTo=null;
//}
//
//
package com.crypto.dashboard.model;

import com.crypto.dashboard.domain.VerificationType;
import jakarta.persistence.Embeddable;

@Embeddable
public class TwoFactorAuth {
    private boolean isEnabled = false;
    private VerificationType sendTo = null;

    // Getters
    public boolean isEnabled() {
        return isEnabled;
    }

    public VerificationType getSendTo() {
        return sendTo;
    }

    // Setters
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setSendTo(VerificationType sendTo) {
        this.sendTo = sendTo;
    }

    // Constructors
    public TwoFactorAuth() {
    }

    public TwoFactorAuth(boolean isEnabled, VerificationType sendTo) {
        this.isEnabled = isEnabled;
        this.sendTo = sendTo;
    }
}