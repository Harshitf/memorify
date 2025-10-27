package com.moclase.Memorify.user;

import com.moclase.Memorify.common.BaseAuditingEntity;
import com.moclase.Memorify.chat.Chat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@NamedQuery(name=UserConstants.FIND_USER_BY_EMAIL,
            query = "SELECT u FROM User u WHERE u.email= :email")
@NamedQuery(name=UserConstants.FIND_ALL_USERS_EXCEPT_SELF,
            query = "SELECT u FROM User u WHERE u.id!= :publicId")
@NamedQuery(name=UserConstants.FIND_BY_PUBLIC_ID,
            query = "SELECT u FROM User u WHERE u.id= :publicId")
public class User extends BaseAuditingEntity {

    private static final int LAST_ACTIVE_INTERVAL = 1;
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastSeen;
    @OneToMany(mappedBy = "sender")
    private List<Chat> chatsAsSender;
    @OneToMany(mappedBy = "recipient")
    private List<Chat> chatsAsRecipient;

    @Transient
    public boolean isUserOnline(){
        return lastSeen!=null&&lastSeen.isAfter(LocalDateTime.now().plusMinutes(LAST_ACTIVE_INTERVAL));
    }
}
