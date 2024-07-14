package member.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class BoardApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}
	@Bean
	   HiddenHttpMethodFilter hiddenHttpMethodFilter(){
	      return new HiddenHttpMethodFilter();
	   }
	@Override 
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { 
	      return application.sources(BoardApplication.class);
	   }
}
