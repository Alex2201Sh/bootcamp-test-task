package by.shumilov.service;

import by.shumilov.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageService {

    public List<User> findPaginated(List<User> userList, Integer page, Integer size) {
        return userList.stream().skip((long) (page - 1) * size).limit(size).collect(Collectors.toList());
    }
}
