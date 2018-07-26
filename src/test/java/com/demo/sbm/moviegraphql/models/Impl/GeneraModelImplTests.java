package com.demo.sbm.moviegraphql.models.Impl;


import com.demo.sbm.moviegraphql.models.GeneraModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class GeneraModelImplTests {

    @Autowired
    GeneraModel generaModel;

    @Test
    public void testGenera() {

        assertThat(generaModel.getGenera(35)).isEqualTo("Comedy");


    }
}
