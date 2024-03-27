# My User Services

Application offers simple CRUD operations facilitated by Spring Data JPA. Users can create profiles, retrieve user profiles and all associated details by ID, create addresses, place orders, and update both addresses and order statuses seamlessly through our RESTful API endpoints. These functionalities streamline user management and order processing, enhancing the overall user experience.
## Features

- User Profile Creation
- Get User Profile
- Get All detils of User By ID
- Creation of address
- Creation of order
- Update address
- Update Order Status



## End Points

#### 1. Create Profile

- **Endpoint:** `POST http://localhost:8081/myUsers/createProfile`
- **Description:** This endpoint allows users to create a new user profile.
- **Request Body:**
    ```json
    {
        "firstName": "Kumar",
        "lastName": "Doe",
        "userName": "gowtham",
        "email": "gowtham699@example.com",
        "mobile": "6303380842",
        "password": "secretpassword"
    }


#### 2. Create Address

- **Endpoint:** `POST http://localhost:8081/myUsers/createAddress`
- **Description:** This endpoint allows users to create a new address.
- **Request Body:**
    ```json
    {
        "userId": "69bf1188-1320-4cd0-a6d2-a023f9878400",
        "addressType": "Personal",
        "addressLine1": "123 Main St",
        "addressLine2": "Apt 4B",
        "landmark": "Near Park",
        "postalCodeDetails": {
            "postalCode" : "123456",
            "city" : "City1",
            "state" : "AP",
            "country" : "Country1"
        },  
        "mobile": "9876543210",
        "isDefault": false
    }


#### 3. Create Order

- **Endpoint:** `POST http://localhost:8081/myUsers/createOrder`
- **Description:** This endpoint allows users to create a new order.
- **Request Body:**
    ```json
    {
        "userId": "86ddb04c-51bc-46fe-84e2-ad5a862326e9",
        "totalAmount": 100.00,
        "deliveryAddress": {
            "addressId": "e7e6f2e9-c7c9-11ee-ba53-3c2c30a05b0c",
	        "userId": "93819b10-af3f-46c0-b9de-ad6a90f6a96e",
	        "addressType": "Home",
	        "addressLine1": "123 Main St",
	        "addressLine2": "Apt 4B",
	        "landmark": "Near Park",
	        "postalCodeDetails": {
                "postalCode" : "12345",
                "city" : "City1",
                "state" : "AP",
                "country" : "Country1"
            },
	    "mobile": "9876543210",
	    "isDefault": true
	    },
        "shipmentStatus": "Shipped",
        "paymentDetails": {
            "paymentMethod": "Credit Card",
            "paymentStatus": "Paid",
            "createdDate": "2022-02-14T12:30:00"
        },
        "createdDate": "2022-02-14T12:30:00",
        "orderItems": [
            {
                "productId": "86ddb04c-51bc-46fe-84e2-ad5a862326e9",
                "quantity": 2
            },
            {
                "productId": "86ddb04c-51bc-46fe-84e2-ad5a862326e9",
                "quantity": 1
            }
        ] 
    }


#### 4. Get All Details By User Id

- **Endpoint:** `GET http://localhost:8081/myUsers/getAllDetails/b90643f4-ea60-11ee-af65-588a5a477990`
- **Description:** This endpoint allows us to get all details.
- **Response Body:**
    ```json
    {
        "userId": "86ddb04c-51bc-46fe-84e2-ad5a862326e9",
        "totalAmount": 100.00,
        "deliveryAddress": {
            "addressId": "e7e6f2e9-c7c9-11ee-ba53-3c2c30a05b0c",
	        "userId": "93819b10-af3f-46c0-b9de-ad6a90f6a96e",
	        "addressType": "Home",
	        "addressLine1": "123 Main St",
	        "addressLine2": "Apt 4B",
	        "landmark": "Near Park",
	        "postalCodeDetails": {
                "postalCode" : "12345",
                "city" : "City1",
                "state" : "AP",
                "country" : "Country1"
            },
	    "mobile": "9876543210",
	    "isDefault": true
	    },
        "shipmentStatus": "Shipped",
        "paymentDetails": {
            "paymentMethod": "Credit Card",
            "paymentStatus": "Paid",
            "createdDate": "2022-02-14T12:30:00"
        },
        "createdDate": "2022-02-14T12:30:00",
        "orderItems": [
            {
                "productId": "86ddb04c-51bc-46fe-84e2-ad5a862326e9",
                "quantity": 2
            },
            {
                "productId": "86ddb04c-51bc-46fe-84e2-ad5a862326e9",
                "quantity": 1
            }
        ] 
    }


#### 5. Get User Profile By User Id

- **Endpoint:** `GET http://localhost:8081/myUsers/getUserProfile/b90643f4-ea60-11ee-af65-588a5a477990`
- **Description:** This endpoint allows us to get user profile by Id.
- **Response Body:**
    ```json
    {
        "userId": "b90643f4-ea60-11ee-af65-588a5a477990",
        "firstName": "John",
        "lastName": "Doe",
        "userName": "john_doe",
        "email": "john@example.com",
        "mobile": "1234567890",
        "password": "password123",
        "addresses": null,
        "orderDetails": null
    }


#### 6. Update Address

- **Endpoint:** `GET http://localhost:8081/myUsers/getUserProfile/b90643f4-ea60-11ee-af65-588a5a477990`
- **Description:** This endpoint allows us to update address.
- **Request Body:**
    ```json
    {
        "addressId": "b914b7f4-ea60-11ee-af65-588a5a477990",
        "userId": "b90643f4-ea60-11ee-af65-588a5a477990",
        "addressType": "Change",
        "addressLine1": "123 Main St",
        "addressLine2": "Apt 4B",
        "landmark": "Near Park",
        "postalCodeDetails" : {
            "postalCode": "12345",
            "city": "City1",
            "state": "AP",
            "country": "Country1"
        },
        "mobile": "9876543210",
        "isDefault": true
    }
- **Response Body:**
    ```json
    {
        "addressId": "b914b7f4-ea60-11ee-af65-588a5a477990",
        "userId": "b90643f4-ea60-11ee-af65-588a5a477990",
        "addressType": "Change",
        "addressLine1": "123 Main St",
        "addressLine2": "Apt 4B",
        "landmark": "Near Park",
        "postalCode": "12345",
        "city": "City1",
        "state": "State1",
        "country": "Country1",
        "mobile": "9876543210",
        "isDefault": true
    }


#### 7. Update Order Status 

- **Endpoint:** `PUT http://localhost:8081/myUsers/updateOrderStatus/b921b19e-ea60-11ee-af65-588a5a477990?status=Cancelled`
- **Description:** This endpoint allows us to update order status.
- **Response Body:**
    ```json
    Updated

## Tech Stack

**Server:** Spring boot

**Database:** MySql


## Usage

To use this API, you can send HTTP requests to the specified routes using tools like cURL, Postman, or any HTTP client library.


