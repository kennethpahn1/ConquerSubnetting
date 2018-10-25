# Divide & Conquer Subnetting
## Introduction
This application is designed to allow users to better understand subnetting through introducing IP addressing, subnet masks, the subnetting process, and IPv6.

## How do we educate users?
The way it does this is through original cOnTeNt produced by Chi Yat Jedid Ong, videos sourced from a YouTube API, and testing users using a combination of true/false and multiple choice questions. These responses are then marked and collated into a marking report - where students are provided with an explanation of how they did and refresh themselves on the cOnTeNt until they are comfortable. Users are also provided with retry capability even if they answer a question wrong until they get it right.

## So what makes our API usage so impressive?
### FEEWKA API (Custom API designed by Kenneth Ning Chien Pahn)
FEEWKA stands for 'For Extended Education with Kenneth's Assistance'. This API was home-grown and hosted on DigitalOcean in Singapore. Using a LAMP (Linux, Apache, MySQL, PHP) stack, this API writes and reads to a MySQL database to enable some unique features including:
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
- Make sure that your version of Android Studio supports API 28. Please.
- Preferably use the Google Pixel 2 x86 emulator running Android 8.
- Ensure that your phone or emulator has an active internet connection or else it will NOT work as it relies on the FEEWKA API.

A walkthrough will be uploaded onto YouTube shortly.
