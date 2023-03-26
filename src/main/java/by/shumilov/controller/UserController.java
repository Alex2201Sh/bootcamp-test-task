package by.shumilov.controller;

import by.shumilov.bean.User;
import by.shumilov.dao.UserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public List<User> gelAllUsers() {
        return userDao
                .findAll()
                .stream()
                .sorted(Comparator.comparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userDao.findById(id).orElse(new User());
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userDao.save(user);
    }

    @PatchMapping("/{id}")
    public User update(@PathVariable("id") long id,
                       @RequestBody User user) {
        User userFromDb = userDao.findById(id).orElse(new User());
        BeanUtils.copyProperties(user, userFromDb, "id");
        return userDao.save(userFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        userDao.deleteById(id);
    }
}
