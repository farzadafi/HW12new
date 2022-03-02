package entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "UserTable")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,length = 10)
    private String nationalId;

    private String password;

    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    private String address;
    private Double balance;

}
