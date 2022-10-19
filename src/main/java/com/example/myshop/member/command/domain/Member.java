package com.example.myshop.member.command.domain;

import com.example.myshop.common.jpa.EmailSetConverter;
import com.example.myshop.common.model.Email;
import com.example.myshop.common.model.EmailSet;
import com.example.myshop.member.command.exception.IdPasswordNotMatchingException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Getter
    @EmbeddedId
    private MemberId id;

    @Getter
    private String name;

    @Embedded
    private Password password;

    private boolean blocked;

    @Column(name = "emails")
    @Convert(converter = EmailSetConverter.class)
    private EmailSet emails;

    public Member(MemberId id, String name) {
        this.id = id;
        this.name = name;
    }

    public void initializePassword() {
        String newPassword = generateRandomPassword();
        this.password = new Password(newPassword);
    }

    private String generateRandomPassword() {
        Random random = new Random();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

    public void changeEmails(Set<Email> emails) {
        this.emails = new EmailSet(emails);
    }

    public void block() {
        this.blocked = true;
    }

    public void unblock() {
        this.blocked = false;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.match(oldPassword)) {
            throw new IdPasswordNotMatchingException();
        }
        this.password = new Password(newPassword);
    }
}
