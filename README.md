Add ReadMe.

[See markdown syntax...](https://help.github.com/en/github/writing-on-github/basic-writing-and-formatting-syntax)

Describe steps to run an application.

#Description
The online ordering system simplifies the ordering process for both the customer and
the restaurant. The system presents an interactive menu with all available options in an easy to use
manner. Customer can choose one or more dishes to place an order which will land in the basket. Customers can view all the
order details in the cart before checking out. In the end, the customer gets order confirmation. Once the order is
placed it is entered in the database and retrieved in pretty much real-time.
#Installation


#Using an application
For the first time it is required to create an admin user in your pgAdmin database.
Firstly, all new users have to register and after that sign in their accounts.\

The system provides three type of user roles.\
For ADMIN role:\
After starting an application admin is able to check and manage all the users,dishes,dish types,ingredients and orders in system.\
All table's data is displayed under corresponding pages which you can get via the buttons on the navigation bar. \
For all these entities it is possible to apply all CRUD methods such as creating new dish,updating existing dish type or deleting a user from system.\

For COOK role:\
Cook also has ability to manipulate all systems data besides users.\
In orders, COOK can see all orders with status IN_PROCESS and click button to finish cook. 

For USER role:\
Customers view all the existing dishes on the Dish page,where they can choose the count for each particular dish and add it to basket.\
On basket page users see the whole information about their orders and proceed with checkout.\
After checkout, users see the confirmation page and the status of order changes to IN_PROGRESS.\
After finishing the cooking,cook submits the order as done and the status of order changes to WAIT_PAYMENT.\
Users can see all their orders on Order page.Once order got the status WAIT_PAYMENT,user must click on Pay button and redirect to payment page.
On this step user should write a correct amount to pay and submit the form.\
Congratulations!You have purchased the order and now the status is changed to DONE. 

After finishing of the application all users must to Log out from their accounts. 


#Script to  


##Build with
[Java](https://docs.oracle.com/en/java/) - Backend language\
[Springboot](https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/htmlsingle/) (MVC, Security, Data JPA)\
[Thymeleaf](https://www.thymeleaf.org/)- A server-side Java template engine\
[Maven](https://maven.apache.org/) - Dependency Management\
[HTML, CSS](https://devdocs.io/html/) - Basis of building Web pages and Web Applications\
[PostgreSQL](https://www.postgresql.org/docs/) - Database Management


##Developer
Eldar Guliyev

[For example](https://github.com/jieiiia/photopond)
