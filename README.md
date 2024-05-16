# Cabify Shop

🛒 **Cabify Shop** is a demonstration application showcasing modern Android development using advanced technologies and practices such as Dagger, Coroutines, Flow, Jetpack Compose, JUnit5, Mockk, and Material Design, all based on the MVVM architecture. This project was created as part of a skill assessment for Cabify.

## Technology Stack

- **Minimum SDK level**: 26
- **Kotlin**: Used for the entire development, with **Coroutines** and **Flow** for asynchronous operations.
- **Jetpack Compose**: Used for views and navigation, allowing a modern, declarative user interface.
- **Dagger Hilt**: For dependency injection.
- **JUnit5** and **Mockk**: For unit testing.
- **Retrofit2** and **OkHttp3**: For API consumption.
- **Turbine**: For testing with Flows.
- **Glide**: For image loading.
- **Konsist**: To ensure conventions and best practices, especially in testing.

## Architecture

- **MVVM Architecture**: Model-View-ViewModel.
- **Modularity**: The application is divided into modules following Clean Architecture, where each module has controlled access, promoting good practices.
- **Strategy Pattern**: Used in `PromotionOfferModel` to apply the logic of different promotions.

<div style="display: flex; justify-content: space-around;">
  <img src="https://github.com/JGomez-Dev/cabifyTest/assets/49919880/b64edd3c-bcf7-47d4-a679-bc97fdb4ba75" alt="Cabify Shop" width="15%">
  <img src="https://github.com/JGomez-Dev/cabifyTest/assets/49919880/f91b1747-e852-4506-b7de-1d7ccc3337a6" alt="Cabify Shop" width="77%">
</div>

## Features

### Products

Description of the product functionality.

### Offers

For the promotion logic, a JSON file simulates offers coming from a service. Considering that the product values could change, these values have been parameterized.

```json
[
  {
    "code": "TSHIRT",
    "text": "3 TSHIRTs for €19 each! Unmissable!",
    "image": "https://web.ccistore.fr/sites/default/files/inline-images/op%C3%A9%20sp%C3%A9ciale%202.jpg",
    "offer": {
      "minimumQuantity": 3,
      "discountPerItem": 1.0
    }
  },
  {
    "code": "VOUCHER",
    "text": "Buy one and get another FREE on VOUCHER",
    "image": "https://storage.timviec365.vn/timviec365/pictures/news/2019/03/13/iyf1552475540.jpg",
    "offer": {}
  }
]
```
# Video
https://github.com/JGomez-Dev/cabifyTest/assets/49919880/f7ea750f-7d20-4f5a-86d6-9b65d387d8ea

# Mockups
![Rectangle 5](https://github.com/JGomez-Dev/cabifyTest/assets/49919880/960dd231-4495-4602-9398-0267e6f2deb7)
