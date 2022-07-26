package com.example.demoProject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"test\"")
@NamedQueries({
        @NamedQuery(name = "Test.findByUsernameOrderByUsernameAsc", query = "select t from Test t where t.username = :username order by t.username")
})
public class Test {
    @Id
    private String username;
    private String password;
    private String email;
}
