News portal

The application is a news portal. The following technologies were used: Servlets, Java Server Pages (JSP), Java Server Pages Standard Tag Library (JSTL), Expression Language, The Java Database Connectivity API (JDBC).

The actions that can be performed on the site depend on the user's role (administrator, user, guest). The application has a listener to initialize the connection pool.

The application interface supports English and Russian localization. The main page displays the news, there are buttons for registration, authorization, changing the locale.

When trying to perform any action, a user with the "guest" role will be directed to the authorization page. Upon successful authorization, the user can enter his personal account.

The following functionality has been implemented:

- viewing all news;
- offer news (for the user);
- add news;
- read the news;
- edit and delete news (only for the administrator);
- change password, email, name, surname;
- view a list of all the news and their status (only for administrator);
- view a list of all users, and delete a user(only for administrator)
- add and delete comment on the news.
