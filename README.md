# home_heating_iot_edge
Home Heating IOT Edge connection written in Java

The project is focused on building a system to monitor the heat of the water entering the house that provides the hot water for taps and the heating system The hot water is provided by a central system for a number of houses and it fails to often. Whats more the system has no monitoring so everyone finds out when the shower in the morning :(

This code covers the edge point that accepts data from the endpoint and stores it in the DB.
The code will also act as the REST gateway to the data as uses by the Web Clients and other data readers.

Project Components

End Point (Client)
  Raspberry Pi Zero W with a DS18B20 temperature probe.

Edge Device
  Raspberry Pi 3 B+.

Storage
  NAS based MySQL DB

Web
  One of Angular 6 Java Thymeleaf
