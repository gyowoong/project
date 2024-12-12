package com.example.project.test;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.project.admin.Entity.Admin;
import com.example.project.admin.Entity.constant.AdminRole;
import com.example.project.admin.repository.AdminRepository;
import com.example.project.entity.test.UserEntity;
import com.example.project.repository.test.UserRepository;

@SpringBootTest
public class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertAdmin(){
        Admin admin = Admin.builder().userId("user1").password(passwordEncoder.encode("1111")).adminRole(AdminRole.ADMIN).build();
        adminRepository.save(admin);
    }

    @Test
    public void userInsert(){
        LongStream.rangeClosed(1, 50).forEach(i ->{
            int re = ((int)(Math.random()*2));
            int age = ((int)(Math.random()*50) + 10);
            int year = ((int)(Math.random()*50) + 1980);
            int month = ((int)(Math.random()*12) + 1);
            int day = ((int)(Math.random()*31) + 1);
            UserEntity userEntity = UserEntity.builder()
            .userid("user"+i)
            .password(passwordEncoder.encode("1111"))
            .name("name"+i)
            .email("usermeil"+ i + "@gmail.com")
            .age(age)
            .gender(re)
            .brith(year + "/" + month + "/" + day)
            .telNo("010-0000-0000")
            .build();

            userRepository.save(userEntity);
        });
    }

    @Test
    public void userList(){
        userRepository.findAll().forEach(u ->{
            System.out.println(u.getUserid());
            System.out.println(u.getName());
            System.out.println(u.getEmail());
            System.out.println(u.getGender());
        });;
    }

    
}
