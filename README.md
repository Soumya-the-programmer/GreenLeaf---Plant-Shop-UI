# GreenLeaf - Plant Shop UI

GreenLeaf is a stylish, responsive UI for a plant shop app, crafted using Jetpack Compose in Android Studio. This interface lets users explore plants, adjust cart items, toggle between vibrant themes, and complete their purchase effortlessly.

## ScreenShots

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/804477bd-ba4c-4189-8af9-ad445dd401b6" alt="Screenshot 1" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/c0a4a9fd-7732-464c-ac7f-05bdc780f3b2" alt="Screenshot 2" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/cac9b42c-155c-48b8-9043-8c290fb5342d" alt="Screenshot 3" width="200"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/d47dc60f-dd6c-4fb2-92cc-5f481b759965" alt="Screenshot 6" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/e2293080-bbd6-4d6c-9b9e-4a9425fcd403" alt="Screenshot 4" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/a5b5a371-6099-408f-887a-53a0ca32c5ee" alt="Screenshot 5" width="200"/></td>
  </tr>
</table>

## Features

- **Product Display**: Displays each plant item with name, description, and price.
- **Quantity Selector**: Allows users to adjust the quantity before adding items to their cart.
- **Wishlist and Cart Functionality**: Users can add plants to a wishlist or directly to the cart, with feedback through snackbar notifications.
- **Customizable Themes**: Offers theme options such as Light Green, Black, Yellow, Sea Green, and Deep Sea Green to personalize the shopping experience.
- **Navigation Support**: Convenient back button for easy navigation.
- **Proceed to Payment**: A "Proceed to Payment" button to guide users to finalize their order.

## UI Components

- **GreenLeafScreen**: The main layout screen with plant product details and shopping controls.
- **Wishlist and Cart Controls**: Icons for adding plants to the wishlist and cart, with real-time feedback.
- **Snackbar Notifications**: Quick notifications after each action to enhance user interaction.
- **Theme Toggle**: Allows users to switch between various theme options.
- **Proceed Button**: Guides users to proceed to checkout.

## Tech Stack

- **Language**: Kotlin
- **Framework**: Jetpack Compose for Android UI
- **UI Elements**: Buttons, Icons, Text Fields, Snackbar Notifications

## Dependencies

Add the following dependencies in your `build.gradle` file to support Jetpack Compose and other essential components:

```gradle
dependencies {
    // Core dependencies
    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.5.1'

    // Jetpack Compose dependencies
    implementation 'androidx.compose.ui:ui:1.3.0'
    implementation 'androidx.compose.material:material:1.3.0'
    implementation 'androidx.compose.ui:ui-tooling-preview:1.3.0'
    implementation 'androidx.compose.foundation:foundation:1.3.0'

    // Lifecycle and ViewModel
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1'

    // Compose Navigation
    implementation 'androidx.navigation:navigation-compose:2.5.3'

    // For Snackbar, icons, etc.
    implementation 'androidx.compose.material:material-icons-core:1.3.0'
    implementation 'androidx.compose.material:material-icons-extended:1.3.0'

    // Tooling for UI preview
    debugImplementation 'androidx.compose.ui:ui-tooling:1.3.0'
    debugImplementation 'androidx.compose.ui:ui-test-manifest:1.3.0'
}
```
## Development Environment

**Android Studio**: To run this project, download and install the latest version of [Android Studio](https://developer.android.com/studio) (Arctic Fox or later). Ensure that Kotlin and Jetpack Compose are supported and enabled.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/GreenLeaf-PlantShopUI.git
   ```
2. **Open in Android Studio**:

- Launch Android Studio and select **Open an existing project**.
- Navigate to the project folder and open it.

3. **Sync Gradle**:

- When prompted, click on **Sync Now** to download the necessary dependencies.
- Ensure that no dependency errors are shown after syncing.

## **Run the Project**:

- Connect an Android device or use an emulator.
- Click **Run** (the green play button) to compile and launch the app on your device/emulator.

## **How to Use**:

- **Browse Plants**: View plant details, including name, description, and price.
- **Add to Cart or Wishlist**: Use the icons to add plants to the cart or wishlist.
- **Adjust Quantity**: Modify the number of items before adding them to the cart.
- **Change Themes**: Toggle between themes (Light Green, Black, Yellow, Sea Green, and Deep Sea Green) to personalize the app appearance.
- **Proceed to Payment**: Complete the purchase by tapping the "Proceed to Payment" button.


