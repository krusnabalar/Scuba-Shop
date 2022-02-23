# Scuba Gear Shop Application

## An Introduction

- ***What* will the application do?**

This application will act as a purchasing system for Scuba Diving Gear that is currently available at the store. It will be able to keep track of the types of gear and their prices. There will be a log of the purchases made by the customer. I am also planning on adding a perspective to my code which will allow the "store owner" to add gear to the store, and change prices for the gear. I might also have the application keep track of the money each customer has.
    
    
- ***Who* will use it?**

The primary use for this application is for people who are planning on buying gear for Scuba Diving, along with the option of entering the application as a store owner and changing the availability and price of gear.


- ***Why* is this project of interest to me?**

I have been scuba diving for a few years, and I love the adventurous and exploratory nature of this activity. It has been incredibly fulfilling, and one important part of diving is the gear. Having such an application can ease and automate the process of keeping track of the gear that has been purchased along with having a history of what each customer has purchased.


## User Stories

- As a user, I want to be able to verify that I am a shop owner through a login system.
- As a user, I want to be able to add gear to the shop as a shop owner.
- As a user, I want to be able to view the gears available to me, along with their prices.
- As a user, I want to be able to buy gear, adding it to my gears.

--

- As a user, when I quit, both as a customer and a user, I want the application to save the current shop inventory to file.
- As a user, I want to be able to reload the inventory previously saved and resume shopping from that point.

## Instructions for Grader

- You can generate the first required event by typing in "customer", entering your name, entering the name of the gear you want to buy in the GUI, and clicking the "Buy!" button.
- You can generate the second required event by typing in "Shop Owner", entering the username (ScubaHut123) and password (Underwater54321), clicking the login button, entering the name and price of the gear, and clicking the "Add to Inventory!" button.
- You can locate my visual component by entering as a shop owner, and opening the GUI. It will have the logo visible. There are also audio components played after clicking buttons, for example the "Buy!" creates a cash-register sound.
- You can save the state of my application by entering as a customer, and selecting the save inventory button after buying gear.
- The state of my application reloads automatically everytime, but you have the option to save as a customer and shop owner if you want and update the cart after making a change.

## Phase 4: Task 2

Option#1:

Test and design a class that is robust.
- The robust class - *Reader* - is in the Persistence Package.

You must have at least one method that throws a checked exception. 
- One of the methods using an exception is the *readInventory* method. 

You must have one test for the case where the exception is expected and another where the exception is not expected.
- Tests in *ReaderTest* handle 2 cases where an exception is not expected, and 1 case where it is expected.

## Phase 4: Task 3

- ScubaOwner Class had poor cohesion and coupling: it only had one method which was coupling with the UI. A change in the UI in phase 3 replaced this method with some GUI code. So I transferred the fields to ScubaShop where it fit well since it makes sense that the entry username and password for the shop owner are stored in the shop. And it did not make sense to have the ShopOwner class any longer, cohesively it made more sense having it in ScubaShop.

- CustomerList Class had poor cohesion, the constructor was never used and the list itself was better stored in the customer class. There was only one method, but it also fit well in the Customer class, so to have a more complete Customer class, I added the fields and classes to the Customer class.
