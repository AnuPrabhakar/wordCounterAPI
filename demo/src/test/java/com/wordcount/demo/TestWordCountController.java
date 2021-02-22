package com.wordcount.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordcount.demo.controller.WordCountController;
import com.wordcount.demo.response.WordCountResponse;


@RunWith(SpringRunner.class)
@WebMvcTest(WordCountController.class)
public class TestWordCountController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getTopRecords() throws Exception 
	{
		String output = "{\"vel\":17," +
				"\"eget\":17," +
				"\"et\":14,"+
				"\"eu\":13" + "}";

		mockMvc.perform( MockMvcRequestBuilders
				.post("/counter-api/top/{id}", 4)
				.contentType(MediaType.parseMediaType("text/csv")))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(equalTo(output)));

	}


	@Test
	public void getKeyWordCount() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/counter-api/search")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"searchText\" :[\"dolor\",\r\n" + 
						"\"interdum\",\"def\",\"Donec\",\"Augue\"]" +"}")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(containsString(asJsonString(new WordCountResponse("dolor", 10)))))
		.andExpect(MockMvcResultMatchers.content().string(containsString(asJsonString(new WordCountResponse("interdum", 7)))))
		.andExpect(MockMvcResultMatchers.content().string(containsString(asJsonString(new WordCountResponse("def", 0)))))
		.andExpect(MockMvcResultMatchers.content().string(containsString(asJsonString(new WordCountResponse("Donec", 8)))))
		.andExpect(MockMvcResultMatchers.content().string(containsString(asJsonString(new WordCountResponse("Augue", 7)))));

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
