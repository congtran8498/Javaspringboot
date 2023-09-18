package com.example.relationshipdemo;

import com.example.relationshipdemo.entity.*;
import com.example.relationshipdemo.repository.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RelationshipDemoApplicationTests {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    void save_user_card() {
        Faker faker = new Faker();
        for(int i = 0; i < 10; i++){
            Card card = new Card();
            card.setCode(faker.code().isbn10());
            cardRepository.save(card);

            User user = new User();
            user.setName(faker.name().fullName());
            user.setCard(card);
            userRepository.save(user);
        }
    }

    @Test
    void delete_user(){
        userRepository.deleteById(11);
    }
    @Test
    void save_user_card_2() {
        Faker faker = new Faker();
        for (int i = 0; i < 5 ; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setCard(new Card(null, faker.code().isbn10()));
            userRepository.save(user);
        }
    }
    @Test
    void get_user_by_id(){
        User user = userRepository.findById(7).orElse(null);
        System.out.println(user.getName());
        System.out.println(user.getCard().getCode());
    }

    @Test
    void save_author_book(){
        Faker faker = new Faker();
        for(int i = 0; i < 10; i++){
            Author author = new Author();
            author.setName(faker.name().fullName());
            authorRepository.save(author);

            for(int j = 0; j < 3; j++){
                Book book = new Book();
                book.setTitle(faker.book().title());
                book.setAuthor(author);
                bookRepository.save(book);
            }
        }
    }

    @Test
    void get_book_by_id(){
        Book book = bookRepository.findById(1).get();
        System.out.println(book.getAuthor().getName());
    }

    @Test
    void save_post_category(){
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setName(faker.book().genre());
            categoryRepository.save(category);
        }

//        for (int i = 0; i < 5; i++) {
//            Post post = new Post();
//            post.setTitle(faker.book().title());
//            post.
//        }
    }
}
