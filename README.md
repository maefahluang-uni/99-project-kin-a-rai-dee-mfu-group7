Project Kin A Rai Dee MFU
=============

# Team Members Group 7

| ID             | Name                | GitHub Account       |
| -------------- | ------------------- | --------------------- |
| 6531503080     | Sorrawit Tanakhwang | [nongsaxso123](https://github.com/nongsaxso123) |
| 6531503094     | Kamoporn Silaloi    | [kamonporrn](https://github.com/kamonporrn)     |
| 6531503098     | Sofwan Kasa         | [Sofwan6531503098](https://github.com/Sofwan6531503098) |
| 6531503118     | Metasit Chooree     | [6531503118](https://github.com/6531503118)     |
| 6531503125     | Sirita Jaikham      | [SiritaJaikham](https://github.com/SiritaJaikham) |
| 6531503128     | Aphichai Nasongkhram| [AphichaiNasongkhram](https://github.com/AphichaiNasongkhram) |

# Introduction

## Business Background
There are many restaurants near the university. But not everyone knows the details of a restaurant without an introduction. This includes first-year students or outsiders who have never been to the area before and will not be able to know the details of the restaurant, menu, and atmosphere inside the restaurant. And most importantly, feedback from users who come to eat at the restaurant.

We therefore created this web application store as a convenience for users. You can see the restaurant in front of Mae Fah Luang University. Users can express their opinions about the atmosphere of the restaurant as well as rate the restaurant, which the web application is called. "Kin A Rai Dee MFU".

##  Objective 
- This web application has been developed to rank restaurants in  Mae Fah Luang University area, provide detailed information about each establishment, and feature user reviews. 
- Users from both  Mae Fah Luang University and users from other areas are welcome to use this application.
- Develop web applications that prioritize easy to  use. Normal users can view the restaurant in front of Mae   Fah Luang University without having to log in. Including designing the user interface  to have convenient usage steps.

## Scope
- Can access the web application 24 hours a day.
- On this web application we will present the restaurants in front of Mae Fah Luang University.
- Tell restaurant details such as restaurant location, restaurant menu and food prices, as well as user comments and ratings.
- This is relevant to the Thai language.
- Users can give restaurant reviews and restaurant owners can respond.

# Controller 
We have 4 controllers:
- `MainController`is the main supervisor of registration and login .
- `RestaurantController`is a way to manage restaurants and menus by creating, adding, deleting, and editing restaurants and menus.
- `RestOwnerRegistrationController `is the control system for registering restaurant owners.
- `UserRegistrationController`is the user registration control.

# Entity
The entities we have are `Users`, `Role`, `RestaurantOwners`, `Restaurants`, and `menus`.
- User entity has many-to-many relationship with role entity.
- RestaurantOwners entity has many-to-many relationship with role entity.
- RestaurantOwners entity has one-to-many relationship with Restaurant entity.
- Restaurant entity has many-to-one relationship with RestaurantOwners entity.
- Restaurant entity has one-to-many relationship with Menu entity.
- Menu entity has many-to-one relationship with Restaurant entity.

# Templates
The template is a user interface using Thymeleaf , Bootstrap 5 , HTML and CSS .




