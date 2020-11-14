package uniManagement.sap.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import uniManagement.sap.dao.CourseDAO;
import uniManagement.sap.dao.CourseDAOImpl;
import uniManagement.sap.dao.StudentDAO;
import uniManagement.sap.dao.StudentDAOImpl;
import uniManagement.sap.dao.TeacherDAO;
import uniManagement.sap.dao.TeacherDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "uniManagement.sap")
public class SpringMvcConfig {
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/university?allowPublicKeyRetrieval=true&useSSL=false");
		dataSource.setUsername("Paladinsta");
		dataSource.setPassword("asdasd");

		return dataSource;
	}
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public StudentDAO getStudentDAO() {
		return new StudentDAOImpl(getDataSource());
	}
	
	@Bean
	public TeacherDAO getTeacherDAO() {
		return new TeacherDAOImpl(getDataSource());
	}
	
	@Bean
	public CourseDAO getCourseDAO() {
		return new CourseDAOImpl(getDataSource());
	}
}
