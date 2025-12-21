# Full-Website-Paths-Scrapper
Trys to scrape the full link list of an website, without using a headless browser

## What is the problem?
A friend of mine has a problem at work where the server has to load a large portion of a website into memory first. Therefore, he needs a large portion of the website's links.

## The solution: scrape the links
This program attempts to find all other links that belong to the same part of the page, using the given link.

## How does my solution work? (So far)
The program takes the first link and extracts all the links it finds from the given HTML. These are checked for safety and accessibility and then stored in a hash. Using this list, it then goes through each individual link, takes a new list of links, and compares it to the original list. If it finds any new links, it adds them. The program always uses the original list as its basis.

## What is the underlying problem?
Reading the description, it quickly becomes apparent that the algorithm has a high time complexity. We initially have to compare n links with potentially n further links and check them for safety and accessibility. If you wanted to do this for all the links on a website, it could take a very long time, depending on the website.

## Improvements i am working on
Currently, the algorithm does not recognize navigation components. Furthermore, this only works if the links are exposed in pure HTML. If the page is a single-page application, or only loads its content when a user interacts, it will not detect them.

Furthermore, there is no user interface. My bad.


