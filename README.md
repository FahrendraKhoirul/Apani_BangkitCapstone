# Apani - Cloud Computing 

This README provides an overview of the endpoints available in the Apani Backend API and their respective request/response formats.

## Article

### Show all articles

Endpoint: GET /articles

Sample Request:
`https://apani-backend-api-dot-apani-capstone-project-389507.et.r.appspot.com/articles`

Sample Response:
```json
[
    {
        "article_id": 1,
        "title": "Judul 1",
        "author": "Hibban",
        "date": "2021-12-03T22:25:59.000Z",
        "article": "Article 1",
        "source": "www.dummy.com",
        "img_url": "www.dummy.com/image",
        "createdAt": "2023-06-11T08:39:55.000Z",
        "updatedAt": "2023-06-11T08:39:55.000Z"
    },
    {
        "article_id": 2,
        "title": "Judul 2",
        "author": "Syakir",
        "date": "2021-12-03T22:25:59.000Z",
        "article": "Article 2",
        "source": "www.dummy2.com",
        "img_url": "www.dummy2.com/image",
        "createdAt": "2023-06-11T08:40:27.000Z",
        "updatedAt": "2023-06-11T08:40:27.000Z"
    }
]
```

### Show an article
Endpoint: GET /articles/:article_id

Sample Request:
`https://apani-backend-api-dot-apani-capstone-project-389507.et.r.appspot.com/articles/1`

Sample Response:
```json
{
    "article_id": 1,
    "title": "Judul 1",
    "author": "Hibban",
    "date": "2021-12-03T22:25:59.000Z",
    "article": "Article 1",
    "source": "www.dummy.com",
    "img_url": "www.dummy.com/image",
    "createdAt": "2023-06-11T08:39:55.000Z",
    "updatedAt": "2023-06-11T08:39:55.000Z"
}
```

## Project

### Create new project

Endpoint: POST /projects

Parameters:
- email (string, required): Email of the user.
- project_name (string, required): Name of the project.
- description (string, required): Description of the project.
- date (datetime, required): Date of the project.
- note (string, required): Note of the project.
- status (boolean, required): Active status (true/false) of the project.

Sample Request:
`https://apani-backend-api-dot-apani-capstone-project-389507.et.r.appspot.com/projects/create`

Sample Response:
```json
{
   "project_id": "1",
   "email": "syakir@gmail.com",
   "project_name": "Project 6",
   "description": "Deskripsi project 6",
   "date": "2021-12-03 23:59:58",
   "note": "Ini adalah catatan project 6",
   "status": true,
   "updatedAt": "2023-06-11T08:40:27.374Z",
   "createdAt": "2023-06-11T08:40:27.374Z"
}
```
### Show all projects

Endpoint: GET /projects/:email

Parameters:
- email (string, optional): Email of the user.

Sample Request:
`https://apani-backend-api-dot-apani-capstone-project-389507.et.r.appspot.com/projects/contoh@gmail.com`

Sample Response:
```json
[
    {
        "project_id": "1",
        "email": "contoh@gmail.com",
        "project_name": "Project 5",
        "description": "Deskripsi project 5",
        "date": "2021-12-03 23:59:58",
        "note": "Ini adalah catatan project 5",
        "status": true,
        "updatedAt": "2023-06-11T08:40:27.374Z",
        "createdAt": "2023-06-11T08:40:27.374Z"
    },
    {
        "project_id": "2",
        "email": "contoh@gmail.com",
        "project_name": "Project 6",
        "description": "Deskripsi project 6",
        "date": "2021-12-03 23:59:58",
        "note": "Ini adalah catatan project 6",
        "status": true,
        "updatedAt": "2023-06-11T08:40:27.374Z",
        "createdAt": "2023-06-11T08:40:27.374Z"
    }
]
```

### Show a project

Endpoint: GET /projects/:email/:project_id

Parameters:
- email (string, required): Email of the user.
- project_id (string, required): ID of the project.

Sample Request:
`https://apani-backend-api-dot-apani-capstone-project-389507.et.r.appspot.com/projects/hibban@gmail.com/1 `

Sample Response:
```json
{
    "project_id": 1,
    "email": "kaikim@gmail.com",
    "project_name": "Project coba 1 updated",
    "description": "Deskripsi project 1",
    "date": "2021-12-03 23:59:59",
    "note": "Ini adalah catatan project 1",
    "createdAt": "2023-06-16T07:42:17.000Z",
    "updatedAt": "2023-06-16T09:49:13.154Z"
}
```


