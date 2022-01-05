@echo off
java -cp %cd%\just-2019ncov-crawler-1.0-SNAPSHOT-jar-with-dependencies.jar driver.NewsCrawlDriver 600000>>%cd%\log\news_crawl_0205.txt
pause