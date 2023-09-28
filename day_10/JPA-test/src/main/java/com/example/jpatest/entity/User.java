package com.example.jpatest.entity;

import com.example.jpatest.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@SqlResultSetMappings(value = {
//        @SqlResultSetMapping(
//                name = "UserInfo",
//                classes = @ConstructorResult(
//                        targetClass = UserDto.class,
//                        columns = {
//                                @ColumnResult(name = "id", type=Integer.class),
//                                @ColumnResult(name = "name", type=String.class),
//                                @ColumnResult(name = "email", type=String.class)
//                        }
//                )
//        )
//})
//@NamedNativeQuery(
//        name = "getAllUserDtoNQ",
//        resultSetMapping = "UserInfo",
//        query = """
//        select u.id, u.name, u.email AS used
//        from user u
//""")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
}
