package com.wordcount.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordcount.demo.request.WordCountSearchParam;
import com.wordcount.demo.response.WordCountResponse;

@RestController
public class WordCountController {

	Logger logger = Logger.getLogger(WordCountController.class.getName());

	static String fileName = "sample.txt";

	ClassLoader classLoader = getClass().getClassLoader();

	File file = new File(classLoader.getResource(fileName).getFile());

	/**
	 * 
	 * @param param
	 * @return Json object with search Keyword & the word-count.
	 * @throws IOException
	 * Pattern Match - Accept only Characters
	 */
	@RequestMapping(value = "/counter-api/search", method = RequestMethod.POST ,  headers = "Accept=application/json")
	public List<WordCountResponse>  getWordCount(@RequestBody WordCountSearchParam param) throws IOException {
		WordCountResponse wordCountObj = null;
		List<WordCountResponse> wordCountResponseList =  new ArrayList<>();
		String content = new String(Files.readAllBytes(file.toPath())).replaceAll("[^a-z A-Z]", " ");


		for (int i = 0; i < param.searchText.length; i++) {
			wordCountObj  = new WordCountResponse(param.searchText[i], countNumberOfOccurrencesOfWord(param.searchText[i],content));
			wordCountResponseList.add(wordCountObj);
		}
		return wordCountResponseList;
	}

	/**
	 * 
	 * @param msg
	 * @return count of occurrence in the given String
	 * @throws IOException 
	 */
	public long countNumberOfOccurrencesOfWord(String msg,String content) throws IOException {

		return Arrays.stream(content.split("[ ]")).filter(s -> s.equalsIgnoreCase(msg)).count();
	}

	@RequestMapping(value = "/counter-api/top/{rowCount}", headers = "content-type=text/csv")
	public Map<String, Integer> findTopWords(@PathVariable("rowCount") int rowCount, HttpServletResponse response) throws IOException {


		String content = new String(Files.readAllBytes(file.toPath())).replaceAll("[^a-z A-Z]", "");

		String[] fileContent = content.split("[ ]");

		HashMap<String,Integer> wordCountMap = new HashMap<>() ;

		for (String keyWord : fileContent) {
			if (wordCountMap.containsKey(keyWord)) {
				wordCountMap.put(keyWord, (wordCountMap.get(keyWord) + 1)); 
			} 
			else { 
				wordCountMap.put(keyWord, 1); 
			}
		}
		return wordCountMap.entrySet()
				.stream()
				.sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).limit(rowCount).collect(Collectors.toMap(Map.Entry::getKey, 
						Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

	}

}