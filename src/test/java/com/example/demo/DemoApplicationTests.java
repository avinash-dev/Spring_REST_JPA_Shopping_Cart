package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getProductsTest() {
		try{
			this.mockMvc.perform(get("/products/")).andDo(print()).andExpect(status().isOk());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void saveProductsValidationErrorTest() {
		try{
			this.mockMvc.perform(post("/products/").contentType(MediaType.APPLICATION_JSON)
					.content("{ \"name\": \"pen\", \"price\": -1 }")).andDo(print()).andExpect(status().is5xxServerError());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void saveProductsValidTest() {
		try{
			this.mockMvc.perform(post("/products/").contentType(MediaType.APPLICATION_JSON)
					.content("{ \"name\": \"pen\", \"price\": 2.3 }")).andDo(print()).andExpect(status().isOk());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void deleteProductTest(){
		try{
			this.mockMvc.perform(delete("/products/1")).andDo(print()).andExpect(status().isOk());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
