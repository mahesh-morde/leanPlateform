# LeanPlateform API Documentation

Welcome to the documentation for LeanPlateform API. This API provides endpoints for managing users, booking sessions, pre-session activities, and feedback functionalities. Below you'll find details about each endpoint along with examples of requests.

## Base URL

https://leanplateform.onrender.com


## Endpoints

### Users

#### 1. Get All Users

* **Endpoint:** `GET /api/users`
* **Description:** Retrieve a list of all users.

#### 2 . Register User

* **Endpoint:** `POST /api/users/register`
* **Description:** Register a new user.
* **Body (JSON):**
  ```json
  {
    "username": "newUser",
    "password": "password123",
    "role": "Client"
  }

#### 3 . Login User

* **Endpoint:** `POST /api/users/login`
* **Description:** Log in a user.
* **Params:**
  * `username`: (String) User's username
  * `password`: (String) User's password

#### 4 . Get User by ID

* **Endpoint:** `GET /api/users/{id}`
* **Description:** Retrieve user details by ID.

####  5 . Update User

* **Endpoint:** `PUT /api/users/{id}`
* **Description:** Update user details by ID.
* **Body (JSON):**
  ```json
  {
    "username": "updatedUser",
    "password": "newPassword",
    "role": "Consultant"
  }

#### 6 . Delete User

* **Endpoint:** `DELETE /api/users/{id}`
* **Description:** Delete user by ID.


#### 7 . Search Consultants in all users:
* **Endpoint:** `GET /api/users/searchConsultants`
* **Description:** Retrieve a list of all consultants.


### Sessions

#### 1. Book Session

* **Endpoint:** `POST /api/users/bookSession`
* **Params:**
  * `clientId`: ID of the client
  * `consultantId`: ID of the consultant
  * `dateTime`: Date and time for the session

* **Example Request:** POST /api/users/bookSession?clientId=1&consultantId=2&dateTime=2024-03-20T14:30:00

#### 2. Session Details

* **Client Endpoint:** `GET /api/users/sessionDetails/client/{clientId}`
* **Consultant Endpoint:** `GET /api/users/sessionDetails/consultant/{consultantId}`
* **Path Variables:**
  * `{clientId}`: ID of the client
  * `{consultantId}`: ID of the consultant

* **Example Requests:**
  * Client: `GET /api/users/sessionDetails/client/1`
  * Consultant: `GET /api/users/sessionDetails/consultant/2`

#### 3. Pre-session Activities

* **Select Documents:** `POST /api/users/preSessionActivities`
  * **Params:**
    * `consultantId`: ID of the consultant
    * `documentList`: List of documents to be selected
* **Get Documents for Session:** `GET /api/users/preSessionActivities/client/{clientId}`
  * **Path Variable:**
    * `{clientId}`: ID of the client
* **Upload Documents:** `POST /api/users/uploadDocuments`
  * **Params:**
    * `clientId`: ID of the client
    * `documents`: List of documents to be uploaded
* **Get Uploaded Documents:** `GET /api/users/uploadDocuments/consultant/{consultantId}`
  * **Path Variable:**
    * `{consultantId}`: ID of the consultant

* **Example Requests:**
  * Select Documents: `POST /api/users/preSessionActivities?consultantId=2&documentList=document1,document2`
  * Get Documents: `GET /api/users/preSessionActivities/client/1`
  * Upload Documents: `POST /api/users/uploadDocuments?clientId=1&documents=document3,document4`
  * Get Uploaded Documents: `GET /api/users/uploadDocuments/consultant/2`

#### 4. Upload Feedback

* **Upload Feedback:** `POST /api/users/uploadFeedback`
  * **Params:**
    * `consultantId`: ID of the consultant
    * `clientId`: ID of the client
    * `feedback`: Feedback text
* **Get Client Feedback:** `GET /api/users/uploadFeedback/client/{clientId}`
  * **Path Variable:**
    * `{clientId}`: ID of the client

* **Example Requests:**
  * Upload Feedback: `POST /api/users/uploadFeedback?consultantId=2&clientId=1&feedback=Excellent session!`
  * Get Client Feedback: `GET /api/users/uploadFeedback/client/1`

