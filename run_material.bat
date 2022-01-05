@echo off
java -cp %cd%\just-2019ncov-crawler-1.0-SNAPSHOT-jar-with-dependencies.jar driver.MaterialCrawlDriver 3600000>>%cd%\log\material_crawl.txt
pause