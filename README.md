# wordCounterAPI

Rest API to fetch the word count & top N records from a sample text file provided.

Code Example
1.API to fetch word count -

Input :  {“searchText”:[“Duis”, “Sed”, “Donec”]}
Output : [{"keyWord":"Duis","keyWordCount":10},{"keyWord":"Sed","keyWordCount":12},{"keyWord":"Donec","keyWordCount":17}]

2. API to fetch top N records (N passed as Path variable)

Input: N = 5
Output :  [{"eget":14},{"dev":12},{"Sed":10},{"Donec":9},{"Duis":8}]


