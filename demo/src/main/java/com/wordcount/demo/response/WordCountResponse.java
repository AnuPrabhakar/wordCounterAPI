package com.wordcount.demo.response;

public class WordCountResponse {
	public String keyWord;
	public long keyWordCount;
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public long getKeyWordCount() {
		return keyWordCount;
	}
	public void setKeyWordCount(long keyWordCount) {
		this.keyWordCount = keyWordCount;
	}
	public WordCountResponse(String keyWord, long l) {
		super();
		this.keyWord = keyWord;
		this.keyWordCount = l;
	}
	
	/*
	 * public String getWordCountResult() {
	 * 
	 * return "{" + this.keyWord + ":" + this.keyWordCount +"}"; }
	 */
	
}
