package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;


@MapperScan("sample.common.dao.mapper")

@SpringBootApplication
public class SamplePjTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplePjTodoApplication.class, args);
	}

}
