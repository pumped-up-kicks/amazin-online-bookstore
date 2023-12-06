# amazin-online-bookstore
## Production Status
[![Build and deploy JAR app to Azure Web App - project-11-amazin-bookstore](https://github.com/pumped-up-kicks/amazin-online-bookstore/actions/workflows/main_project-11-amazin-bookstore.yml/badge.svg)](https://github.com/pumped-up-kicks/amazin-online-bookstore/actions/workflows/main_project-11-amazin-bookstore.yml)
![Code Coverage](./.github/badges/jacoco.svg)

## Project Description 
An SaaS Fake amazon online bookstore application that is implemented in SpringMVC and Agile development https://project-11-amazin-bookstore.azurewebsites.net/
</br>
Bookstore Owner can upload and edit Book information (ISBN, picture, description, author, publisher,...) and inventory. User can search for, and browse through, the books in the bookstore, sort/filter them based on the above information. User can then decide to purchase one or many books by putting them in the Shopping Cart and proceeding to Checkout. The purchase itself will obviously be simulated, but purchases cannot exceed the inventory. User can also view Book Recommendations based on past purchases. This is done by looking for users whose purchases are most similar (using Jaccard distance: Google it!), and then recommending books purchased by those similar users but that the current User hasn't yet purchased.

## Development Environment Setup

Enabled with CI from the start of the project. In the root folder of the project there should be a pom.xml file. The pom.xml to compile, test, package and run the application.
To set your gitmessage template, use
```agsl
git config commit.template .github/.gitmessage
```

## Database Schema
![image](https://github.com/pumped-up-kicks/amazin-online-bookstore/assets/76576373/3aca082d-50ad-4d89-a415-bb17690ac3b2)

## UML Class Diagram
![uml drawio](https://github.com/pumped-up-kicks/amazin-online-bookstore/assets/91295489/bc5fc0b1-7468-4f72-a0e0-ebbfcf1803c7)

## Agile Development Details
### Functionality
Currently, the bookstore admin page has the following features:
- Access the administrative view, the bookstore view, and the user cart page
- Create a new book, with a specified name, isbn, picture, publisher, and quantity in stock
- Update the information of a particular book
- Remove books entirely from the bookstore

The bookstore customer page also has the following abilities:
- View all or specific books stored in the bookstore by providing id or book name
  
Security and authentication:
- Only authenticated users can access the homepage or logout
- Unauthenticated user can register for a new unique account
- Unauthenticated user can login with user account credentials
- The system will stop unauthenticated users when they manually modify the URL routes or send any request to the server
- The UI will display the error and have friendly user experience
  
These features will be available next Sprint:
- View all the books that bookstore created (DONE)
- Add items to their cart with specified quantity (DONE)
- View cart items (DONE)
- Remove items completely from cart (DONE)
- Checkout functionality (DONE)
- See their purchase history (DONE)
- See a recommendations page (DONE)
- Book have price and total price when checkout (DONE)

### Development and release process
The project must have a main branch, and each contributor should create a new branch for each of the
features they implement. Once development is complete, the team member should open a pull request
and request code reviews by at least one other team member. After the reviewer(s) approve the code, it
can be merged into master, re-tested and built with CI, and deployed to Azure.

### Scrums
Each team member is required to communicate weekly updates following the principles of the daily
scrum meetings. The purpose of daily scrums is to communicate within the project team. This
communication should also be visible to the TA, and therefore you will use GitHub Issues:
Each week a group member should open a new issue called Weekly scrum – [date], then every team
member should add comments with their own contribution, i.e. their answers to the questions:
- what have I done this week? (with link(s) to the relevant GitHub Issue(s))
- what will I do next week? (with link(s) to the relevant GitHub Issue(s))
- what is holding me back? (if applicable)
Expected length: 1-2 sentences per item

### Product backlog
Use GitHub Projects ("Projects" tab in your GitHub repo) to create a "Kanban" style view of your
Issues, with columns dedicated to "backlog", "in progress", "completed". The README.md file on
Github should summarize the current state of the project as per the Kanban, and include a plan for the
next sprint. It should also include the up-to-date schema of the database.

### Milestones
Milestone 1: Early prototype. Give a 10-15 minute demo during the lab on November 13th.
For this milestone we are looking to see enough functionality to get a feel for the system and how it will
work. One important use case should be operational. It should collect data from the back end, do
something with it and display the result. The display doesn't need to be fancy. There should be a GitHub
repo, integrated with CI, and the app should be up and running on Azure. Cloning the repo and running
the pom.xml should provide us with a ready-to-run JAR file.
We will also inspect the README file, the Issues, the Kanban, the code reviews, the tests, and we will
verify that everybody is participating in all aspects of the project (if that is not the case, different team
members will end up with different grades).</br>
Milestone 2: Alpha Release. Give a 10-15 minute demo during the lab on November 27th.
For the alpha release your system should be somewhat usable, although not feature-complete. This
means that a user should be able to use several related features of the app and do something reasonably
useful. The README on GitHub must be updated with a plan for the next sprint.</br>
Milestone 3 - Final demo. Project complete. Give a 10-15 minute demo during the lab on December 8th

### Project report
The information on the GitHub README should provide sufficient information to understand what the
project is and how much is implemented. Include a UML class diagram of your Models (and only of the
Models!), as well as the corresponding database schema created by the ORM (observe what patterns are
being used by the ORM behind the scenes! You need to know these as preparation for the final exam
too...). Your diagrams must be in sync with the code that you have produced, so just put these diagrams
in version control, and grow/update them along with your code!
