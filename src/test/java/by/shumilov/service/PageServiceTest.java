package by.shumilov.service;

import by.shumilov.bean.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PageServiceTest {

    private final PageService service = new PageService();

    private final List<User> userList = new ArrayList<>();

    @BeforeEach
    private void init() {
        IntStream.range(1, 50).forEach(value -> {
            User user = new User();
            user.setId((long) value);
            userList.add(user);
        });

    }

    @Test
    void findPaginatedSizeCheck() {
        List<User> paginated = service.findPaginated(userList, 1, 10);
        assertThat(paginated).hasSize(10);
    }

    @Test
    void findPaginatedUserCheck() {
        List<User> paginated = service.findPaginated(userList, 2, 10);
        List<Long> idList = paginated.stream().map(User::getId).filter(aLong -> aLong > 10 && aLong < 21).collect(Collectors.toList());
        assertThat(idList).isNotEmpty();
    }
}