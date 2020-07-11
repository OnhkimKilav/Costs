package com.costs;

import com.costs.utils.EntityHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostsApplicationTests {

    @Autowired
    protected EntityHelper entityHelper;

    @Test
    void contextLoads() {
    }

}
