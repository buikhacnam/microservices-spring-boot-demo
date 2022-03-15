package com.buinam.userservice.service;

import com.buinam.userservice.repository.UserRepository;
import com.buinam.userservice.VO.Department;
import com.buinam.userservice.VO.ResponseTemplateVO;
import com.buinam.userservice.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    // with this restTemplate we will call department service
    private RestTemplate restTemplate;


    public User saveUser(User user) {
        log.info("inside saveuser of saveuser service");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("inside get userwithdepartment of service");
        User user = userRepository.findByUserId(userId);
        // Department department = restTemplate.getForObject("http://localhost:9001/departments/" + user.getDepartmentId(), Department.class);
        // if using service registry - eureka server:
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
        ResponseTemplateVO vo = new ResponseTemplateVO();
        vo.setDepartment(department);
        vo.setUser(user);

        return vo;
    }
}
