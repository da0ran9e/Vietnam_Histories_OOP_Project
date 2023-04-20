# Data_Crawler_OOP_Project
For OOP lecture project
# Wikipedia page crawler
This program crawls the content of Wikipedia pages and stores it in a JSON file. It uses the Jsoup library to parse HTML and retrieve the content of Wikipedia pages. The program takes as input a JSON file that contains a list of Wikipedia page URLs and their titles. For each page, the program retrieves the page content and stores it in a separate JSON file. The JSON file contains the title of the page and its main sections and paragraphs.
## Prerequisite:
- Jsoup library < /br>
- JSON simple library
## How it work:
- Get content of div html tag with id="bodyContent" as the scope
- Get h2, h3 html tag as main sections
- Get p as paragraph
- Get a href as redirection link
## Path: .\Webcrawler\src\Jsoup\Wikipedia_Crawler.java
