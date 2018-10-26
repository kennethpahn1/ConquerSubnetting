# Divide & Conquer Subnetting
## Introduction
This application is designed to allow users to better understand subnetting through introducing IP addressing, subnet masks, the subnetting process, and IPv6.

## How do we educate users?
The way it does this is through original cOnTeNt, videos sourced from a YouTube API, and testing users using a combination of true/false and multiple choice questions. These responses are then marked and collated into a marking report - where students are provided with an explanation of how they did and refresh themselves on the cOnTeNt until they are comfortable. Users are also provided with retry capability even if they answer a question wrong until they get it right.

## So what makes our API usage so impressive?
### FEEEWKA API (Custom API)
FEEEWKA stands for 'For Enhanced Educational Experiences with Kenneth's Assistance'. This API was home-grown and hosted on DigitalOcean in Singapore. Using a LAMP (Linux, Apache, MySQL, PHP) stack, this API writes and reads to a MySQL database to enable some unique features including:
- Save states: the program uses save.php on the server to save the exact section and subsection the user is up to in each module. The program uses resume.php and getorder.php to retrieve information on where exactly the user should resume from.
- Online answer saves: using getmcq.php and gettf.php - our API remembers what answers and questions the user entered, and where to pick up where they left off from. The record versions of the former php files are also used to record them on the cloud.
- Cross-device capability: Rather than saving the answers and progress on device, users can use their accounts on other Android devices. The app relies on login.php to login to their account.
- One device, multiple users: Users can share their device as they have the ability to register. The app relies on resume.php to do so.
### YouTube API
Our app uses the YouTube API to show a playlist of videos that will help add additional context to the module text cOnTeNt provided.

## How is our UI/UX amazing?
Our UI is extremely simple to use. How?
- Effortless logins/registrations: When a user tries to login with their zID but have not registered, and only realises after they try, when they click the register button, it will automatically populate their zID/zPass that they used. All they have to fill in is their name.
- Large text and icons: These icons have been used to ensure users can easily see where to go, and not have to look so close to their device.
- Resume functionality: Users often have other places to go and other things to do, so we added an ability to resume from where they left off using our FEEWKA API.
- Responsive design: The design uses constraints to ensure that you can use your device - your way, across any Android device.

## Potential
We wanted to add a long response section and the ability for tutors to mark homework (long response answers only) using the FEEWKA API, however, we did not have enough time as we were focusing on trying to push cOnTeNt into the app.

## How to compile this in Android Studio?
- Use this on an Android device running Oreo.
- Make sure that your version of Android Studio supports API 27.
- Preferably use the Google Pixel 2 x86 emulator running Android 8.
- Ensure that your phone or emulator has an active internet connection or else it will NOT work as it relies on the FEEWKA API which is online.

## Walkthrough
In the case the application does not compile properly or does not function as expected on your build, or you need some extra context - feel free to watch our walkthrough below.
https://www.youtube.com/watch?v=CJUwU1WD278

## Resources
To be honest we probably forgot to reference every single time we used something so we're going to put it all here as well:
- To enable the back button to be used for different purposes: https://stackoverflow.com/questions/3141996/android-how-to-override-the-back-button-so-it-doesnt-finish-my-activity
- Parsing JSON and HTML output from FEEWKA API: https://mobilesiri.com/json-parsing-in-android-using-android-studio/
- Yes/No Dialog used for logging out, resuming, etc.: https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
- Bug in Yes/No Dialog where it could not get context to show in: https://stackoverflow.com/questions/5447092/get-context-inside-onclickdialoginterface-v-int-buttonid#5447125
- Dialog for Yes/No was not showing buttons properly fix: https://stackoverflow.com/questions/27965662/how-can-i-change-default-dialog-button-text-color-in-android-5
- Bypass requiring AsyncTasks: https://developer.android.com/reference/android/os/StrictMode
- Learning Switch statements: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
- Making a TextView scrollable: https://stackoverflow.com/questions/23873454/android-textview-scrollable
- Random number generator used to select a random nice comment for the report card: https://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java
- Creating the FEEEWKA API: https://www.tutorialspoint.com/android/android_php_mysql.htm
