package com.wordcount.demo.request;

public class WordCountSearchParam {
	public String keyWord;
	public String fetchCount;
	public String[] searchText;
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getFetchCount() {
		return fetchCount;
	}
	public String[] getSearchText() {
		return searchText;
	}
	public void setSearchText(String[] searchText) {
		this.searchText = searchText;
	}
	public void setFetchCount(String fetchCount) {
		this.fetchCount = fetchCount;
	}
}
