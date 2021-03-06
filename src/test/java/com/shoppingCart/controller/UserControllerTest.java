package com.shoppingCart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.shoppingcar.controller.UserController;
import com.shoppingcar.model.User;
import com.shoppingcar.service.IUserService;

@RunWith(MockitoJUnitRunner.class)
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {
	
	@Mock
	private IUserService userService;
	
	@InjectMocks
	private UserController userController;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@AfterEach
	public void tearDown() {
		System.out.println("end test");
	}
	
	@Test
	@Order(1)
	public void verifyBuilderUserTest() {
		User mockUser = this.builderUser();
		assertEquals(mockUser.getId(), 7L);
	}
	
	@Test
	@Order(2)
	public void showTest() {
		User mockUser = this.builderUser();
		Mockito.when(userService.findById(7L)).thenReturn(mockUser);
		userController.show(7L);
		verify(userService, times(1)).findById(7L);
	}
	
	@Test
	@Order(3)
	public void loginTest() {
		User mockUser = this.builderUser();
		BindingResult result = null;
		Mockito.when(userService.findByNameAndPassword("Test", "pass")).thenReturn(mockUser);
		userController.login(mockUser, result);
		verify(userService, times(1)).findByNameAndPassword("Test", "pass");
		assertEquals(mockUser.getName(), "Test");
	}
	
	public User builderUser() {
		User mockUser = new User();
		mockUser.setId(7L);
		mockUser.setName("Test");
		mockUser.setPassword("pass");
		mockUser.setDataCreate(new Date());
		mockUser.setState(true);
		
		return mockUser;
	}

}
