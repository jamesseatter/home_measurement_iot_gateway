# home_measurement_iot_gateway

[![CircleCI](https://circleci.com/gh/jamesseatter/home_heating_iot_edge.svg?style=svg)](https://circleci.com/gh/jamesseatter/home_heating_iot_edge)

Home Measurement IOT Gateway

The project is focused on building a system to monitor the temperature of the water entering the house from a shared common heating source. That hot water is then distributed to taps and the underfloor heating system. The project will collect temperatures from strategic points to monitor water temperature and record the data for viewing within a web based front end.

The driver for this project is to monitor the hot water for system faults and provide an alarm when there are issues that need intervention. There is a history of system failures over the years that have taken hours and days to be corrected due to various factors.


This code covers the edge point that accepts data from the endpoint and stores it in the DB.
The code will also act as the REST gateway to the data as uses by the Web Clients and other data readers.

**Code Goals**
   * Receive data from 1 or more collecors using REST interfaces
   * Use authentication and roles to control access both devices and systems
   * Update collector configuration remotely
   * Store incoming data in a DB
   * Store data localy if DB is not accessible and replay

**Code/hardware Components**
   * Raspberry Pi 3 B+
   * Java 8 code base
