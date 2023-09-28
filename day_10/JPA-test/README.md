### Câu 1: 
Thuộc tính name trong annotation @Entity khác với thuộc tính name trong 
@Table như thế nào? Hãy giải thích rõ cần thì minh hoạ

#### Trả lời:
Thuộc tính name của @Entity được sử dụng để xác định tên của entity 
trong cơ sở dữ liệu , ta sẽ sử dụng name của entity để truy vấn dữ liệu.
Còn thuộc tính name của @Table là tên bảng của entity trong cơ sở dữ liệu

### Câu 2:
Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi,
cần phải bổ sung lệnh nào vào file application.properties?
#### Trả lời:
spring.jpa.show-sql=true

### Câu 3:
Annotation @Column dùng để bổ sung tính chất cho cột ứng với một thuộc tính.
* Tham số nào trong @Column sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính
* Tham số nào chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu
* Tham số nào buộc trường không được null?

#### Trả lời:
* Tham số đổi lại tên cột là: name
* Tham số chỉ định yêu cầu duy nhất: unique = true
* Tham số nào buộc trường không được null: nullable = false

### Câu 4:
Có 2 sự kiện mà JPA có thể bắt được, viết logic bổ sung

* Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)
* Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)
Vậy 2 annotation này là gì
#### Trả lời:
2 annotation này là @PrePersist và  @PreUpdate

### Câu 5:
JpaRepository là một interface có sẵn trong thư viện JPA,
nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu. Cụ thể JpaRepository kế thừa từ interface nào?
#### Trả lời:
kế thừa từ interface là PagingAndSortingRepository

### Câu 6:
Hãy viết khai báo một interface repository thao tác với một Entity tên
là Post, kiểu dữ liệu trường Identity là Long, tuân thủ interface 
JpaRepository.
#### Trả lời:
```java
public interface PostRepository extends JpaRepository<Post,Long> {
}
```
### Câu 7:
Khi đã chọn một cột là Identity dùng @Id để đánh dấu, thì có cần phải
dùng xác định unique dùng annotation @Column(unique=true) không?
#### Trả lời:
Không cần. Bời vì khi đánh dấu là @Id thì nó là unique

### Câu 8:
Giả sử có 1 class Employee với các fields sau {id, emailAddress, firstName, lastName}. Hãy viết các method trong interface EmployeeRespository để :

* Tìm tất cả các Employee theo emailAddress và lastName
* Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
* Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
* Tìm tất cả các Employee theo fistName không phân biệt hoa thường
#### Trả lời:
```java
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
//    Tìm tất cả các Employee theo emailAddress và lastName
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);

//    Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
    List<Employee> findByFirstNameNotOrLastNameNot(String firstName, String lastName);

//    Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

//    Tìm tất cả các Employee theo fistName không phân biệt hoa thường
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

}
```

### Câu 9: 
Hãy nêu cách sử dụng của @NamedQuery và @Query. Cho ví dụ
#### Trả lời:
@NamedQuerry là đặt tên cho truy vấn ở trong entity và gọi trong repository
```java
@Entity
@Table
@NamedQuery(name = "Employee.findByEmailAddress1",
        query = "select e from Employee e WHERE e.emailAddress like :emailAddress")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String emailAddress;
    private String firstName;
    private String lastName;
}
```
```java
    //named query
    List<Employee> findByEmailAddress1(@Param("emailAddress") String emailAddress);
```
* @Query: 
```java
    //query
    @Query("select e from Employee e where e.emailAddress like ?1")
    List<Employee> find_by_email(String emailAddress);
```
### Câu 10:
Hãy nêu 1 ví dụ sử dụng sorting và paging khi query đối tượng Employee ở trên
#### Trả lời:
```java
    public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    // Sử dụng sorting
    List<Employee> findByLastName(String lastName, Sort sort);

    // Sử dụng paging
    Page<Employee> findByFirstName(String firstName, Pageable pageable);
}
```
```java
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findEmployeesByLastName(String lastName) {
        Sort sort = Sort.by("firstName").ascending();
        return employeeRepository.findByLastName(lastName, sort);
    }

    public Page<Employee> findEmployeesByFirstName(String firstName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findByFirstName(firstName, pageable);
    }
} 
```
### Câu 11:
Có 3 Entity Product.java và Category.java và Tag.java

* Hãy bổ sung định nghĩa quan hệ One to Many giữa bảng Category (One) – Product (Many). Chú ý khi một Category xoá, thì không được phép xoá Product, mà chỉ set thuộc tính Category của Product là null.
* Hãy bổ sung định nghĩa quan hệ Many to Many giữa bảng Tag(Many) – Product(Many).
#### Trả lời:

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Product> productList = new ArrayList<>();
}
```
```java
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToMany(mappedBy = "productList")
    private List<Tag> tagList;
}

```
```java
@Entity
@Table
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "tag_product",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;
}
```
### Câu 12:
* method query: Tìm các User qua method query rồi map qua userDto
```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<UserDto> getAllUserDto(){
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
```

* JPQL query :
```java
    @Query("""
       select new com.example.jpatest.dto.UserDto(u.id,u.name,u.email)
       from User u
""")
    List<UserDto> getAllUserDto();
```
* Native query
* Projection:
tạo ra 1 interface 
```java
public interface UserInfo {
    Long getId();
    String getName();
    String getEmail();
}
```
rồi gọi trong repository
```java
    UserInfo findByNameContainingIgnoreCase(String name);
```
### Câu 13:

```java
@Entity
@Table

public class Post {
    @Id
    private String id;
    private String title;

    public Post() {
        this.id = UUID.randomUUID().toString();
    }

}

```