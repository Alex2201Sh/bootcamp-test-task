package by.shumilov.controller;

import by.shumilov.bean.User;
import by.shumilov.dao.UserDao;
import by.shumilov.service.PageService;
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
    private final PageService service;

    @Autowired
    public UserController(UserDao userDao, PageService service) {
        this.userDao = userDao;
        this.service = service;
    }

    @GetMapping()
    public List<User> gelAllUsers(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "size", required = false) Integer size) {
        List<User> userList = userDao
                .findAll()
                .stream()
                .sorted(Comparator.comparing(User::getEmail))
                .collect(Collectors.toList());
        if (page != null && size != null) {
            return service.findPaginated(userList, page, size);
        } else return userList;

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
