package com.muradtyv.SpringEmailVerification.dao.token;

import com.muradtyv.SpringEmailVerification.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    private static final int EXPIRATION_TIME = 15;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime=this.getTokenExpirationTime();
    }

    public VerificationToken(String token) {
        this.token = token;
        this.expirationTime=this.getTokenExpirationTime();
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}