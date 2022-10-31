# CoffeeShopApplication
 
 ## Problem Context
 The application should allow admin user to add or update or delete the products and normal user should not have access to that API.The other user can only view the list of products and order it.The discount should be calculated based on below scenarios
 - 25% on total price if the ordered amount it more than 12 
 - item with less price is free if total ordered product it more or equal 3
 - item with less price is free if both above condition satisfied 
 
 ## Sloution
 
Created spring boot maven project with postgress database
 
  ### Technologies
 - Java 11
 - Maven 
 - Docker
 - postgres

 ### Running with Docker 
- Install docker: https://docs.docker.com/engine/install/
- Go to project root folder and run the docker compose file

    docker-compose up
 
 ### API URL and response
 Swagger UI for the application is http://localhost:8082/swagger-ui/index.html
 
 ![image](https://user-images.githubusercontent.com/48526042/198899607-16406fd6-d2b5-493d-a5fb-541625e106f8.png)

**Add products by admin**

![image](https://user-images.githubusercontent.com/48526042/198874067-183eb6ba-d18f-4b77-bedd-513e7ba84f3b.png)

**Update products by admin**

![image](https://user-images.githubusercontent.com/48526042/198874090-09e178d5-3d4d-406e-b6d8-d925d32a1c39.png)

**Delete products by admin**

![image](https://user-images.githubusercontent.com/48526042/198874112-ec2b9dd9-3864-47f9-8cd8-4f9569b9fced.png)

**Product added to cart buy user**
User enter only the productId and number of products then will get order summary with list of ordered products along with discount details if its eligible.

![image](https://user-images.githubusercontent.com/48526042/198874158-3e584af5-3c45-428c-ba34-c0de01670005.png)

