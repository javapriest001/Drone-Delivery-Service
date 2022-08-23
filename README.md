## Drones



---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has: 
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task. 

The service should allow:
- registering a drone;
- loading a drone with medication items; 
- checking loaded medication items for a given drone;
- checking available drones for loading;
- check drone battery level for a given drone; 


> Feel free to make assumptions for the design approach. 

---

### Requirements

While implementing your solution **please take care of the following requirements**: 

#### Functional requirements

- There is no need for UI; 
- Prevent the drone from being loaded with more weight that it can carry; 
- Prevent the drone from being in LOADING state if the battery level is **below 25%**; 
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---

#### How to build
#### Requirements

- Java 8;
- Java IDE : IntelliJ (or Eclipse, Vscode, Netbeans);
- Database: H2 Memory(All data will be preloaded on run);
- Postman(For testing );

Steps by step for building and runing the project locally;
Clone the from the link git clone https://github.com/Cybermatepro/Drone-Delivery-Service.git
![Screenshot (81)](https://user-images.githubusercontent.com/89165338/185836882-3e6187ff-e6df-4ce0-855a-b16d924ab9f0.png)

- Open the cloned project in eclipse;

- Go to POM.xml the update Project to update all the maven dependencies;

- Maven Build the project and run;

- Before running you can run the JUnit test cases to assert that everything is working correctly (I have included all of the JUnit tests);

---

#### Testing The API Endpoints
#### Some of the Assumptions made in the course of the task

- A drone can accommadate more than one medication at a time provided the total weight af all the medications does not exceed the max weight of the drone;
- You cannot load a particular medication more than once on a drone (Eg: Medication with ID: **MD1** cannot be loaded to a Drone with ID: **DRN1** more than once);
- preloaded IDs of Drones (**DRN1** , **DRN2** , **DRN3** **DRN4** , **DRN5** , **DRN6** , **DRN7** , **DRN8** , **DRN9** , **DRN10**);
- preloaded IDs of Medications (**MD1**, **MD2** , **MD3**);

---
**Registering A Drone**
- Endpoint: 'http://127.0.0.1:9000/drone/register';

**Payload**
```
curl --location --request POST 'http://127.0.0.1:9000/api/register' \
--header 'Accept: application/json' \
--header 'Authorization: Bearer 6|KcVIobPKqHTo6U0TE0JK9tbIgc63RJIDHTq3r36c' \
--header 'Content-Type: application/json' \
--data-raw '{
    "serialNumber": "DRN11",
    "model": "HEAVYWEIGHT",
    "weight": 500,
    "batteryCapacity": 100,
    "stateOfDuty": "IDLE",
    "medication": []
}'

````
**Response**

```
{
    "message": "Success",
    "timesStamp": "2022-08-21T03:51:02.2776233",
    "serialNumber": "DRN11"
}

```
---

**Loading A Drone**
- Endpoint: '(http://localhost:9000/drone/DRN3/load/MD3)';
- Path Variables:
   - DRN3 : Drone ID;
   - MD3: Medication ID;

**Payload**
```
curl --location --request GET 'http://localhost:9000/drone/DRN3/load/MD3' \
--data-raw ''

````
**Response**

```
{
    "message": "success",
    "timesStamp": "2022-08-22T05:31:13.3669108",
    "serialNumber": "DRN3",
    "medications": [
        {
            "code": "MD3",
            "name": "7-keys",
            "weight": 50,
            "image": "0xudbcf.png"
        }
    ]
}

```
---

**checking loaded medication items for a given drone**
- Endpoint: 'http://localhost:9000/drone/DRN3/medications';
- Path Variables:
   - DRN3 : Drone ID;

**Payload**
```
curl --location --request GET 'http://localhost:9000/drone/DRN3/medications'

````
**Response**

```
{
    "message": "success",
    "timesStamp": "2022-08-22T05:43:05.876705",
    "serialNumber": "DRN3",
    "medications": [
        {
            "code": "MD3",
            "name": "7-keys",
            "weight": 50,
            "image": "0xudbcf.png"
        },
        {
            "code": "MD1",
            "name": "Ibuprofen",
            "weight": 50,
            "image": "0xudbcf.png"
        }
    ]
}

```
---

**Getting Available Drones For Loading**
- Endpoint: 'http://localhost:9000/drone/availabledrones';
- Assumption:
   - Drones are available for loading if their battery capacity is above 25%;
   - Drones are available for loading if their loading state is IDLE

**Payload**
```
curl --location --request GET 'http://localhost:9000/drone/availabledrones'

````
**Response**

```
{
    "message": "success",
    "timesStamp": "2022-08-21T05:57:26.9013046",
    "availableDrones": [
        {
            "serialNumber": "DRN1",
            "model": "HEAVYWEIGHT",
            "weight": 500,
            "batteryCapacity": 100,
            "stateOfDuty": "IDLE",
            "medication": []
        },
        {
            "serialNumber": "DRN3",
            "model": "CRUISERWEIGHT",
            "weight": 450,
            "batteryCapacity": 89,
            "stateOfDuty": "IDLE",
            "medication": []
        },
        {
            "serialNumber": "DRN5",
            "model": "HEAVYWEIGHT",
            "weight": 500,
            "batteryCapacity": 100,
            "stateOfDuty": "IDLE",
            "medication": []
        },
        {
            "serialNumber": "DRN6",
            "model": "HEAVYWEIGHT",
            "weight": 500,
            "batteryCapacity": 90,
            "stateOfDuty": "IDLE",
            "medication": []
        },
        {
            "serialNumber": "DRN7",
            "model": "MIDDLEWEIGHT",
            "weight": 500,
            "batteryCapacity": 100,
            "stateOfDuty": "IDLE",
            "medication": []
        },
        {
            "serialNumber": "DRN9",
            "model": "LIGHTWEIGHT",
            "weight": 500,
            "batteryCapacity": 65,
            "stateOfDuty": "IDLE",
            "medication": []
        },
        {
            "serialNumber": "DRN10",
            "model": "HEAVYWEIGHT",
            "weight": 500,
            "batteryCapacity": 100,
            "stateOfDuty": "IDLE",
            "medication": []
        }
    ]
}

```
---

**Check Battery Level for a given Drone**
- Endpoint: --request GET 'http://localhost:9000/drone/DRN1/battery';

**Payload**
```
curl --location --request GET 'http://localhost:9000/drone/DRN1/battery'

````
**Response**

```
{
    "message": "success",
    "timesStamp": "2022-08-22T05:54:57.1988455",
    "serialNumber": "DRN1",
    "batteryCapacity": 100
}

```
---



