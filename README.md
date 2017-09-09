# Geofence

Geofence is Android app that will detect if the device is inside of a geofence area.

Geofence area is defined as a combination of some geographic point, radius, and specific Wifi network name. 
Device is considered to be inside of the geofence zone if it’s connected to the specified network or remains 
geographically in the defined circle.

# Test assignment cover text

* As we discussed with "Dane McLeod" - no external non-android library is used in this project.
* This is just test assignment, not a production grade code. So i felt free to write some unstable,
unsupportable code, invent wheels, write something, i could never do in production. You can find
code those in package "com.denisroyz.geofence.di"
* Have to emphasize, this code was written after 9 hours working day on my current job. So i
am sure this can look pretty terrifyingly for a person with fresh mind. Unfortunately my mind
was exhausted during this code assignment implementation (you can check commits time to find
out when i had time to work on this).

# APK download

Production APK can be downloaded from (https://drive.google.com/open?id=0B0iecnnNqUtFb0NURmZsSDk4cXc)

# APK Installation

You can find how to install android apk on Simulator by link (https://developer.android.com/studio/run/emulator.html)

You can find how to install android apk on Real android device by link (https://developer.android.com/studio/run/device.html)

# Project build process

I assume the ones who watch this doc already know how to build native android gradle projects with build variants.

Project have 2 flavours

### Mock 
* Contains implementation with mocked location manager, WifiManager. They provide always the same data:
1) Wifi network name: MOCK
2) GPS Lattitude: 50.440
3) GPS Longitude: 30.545
* This implementation holds "User entered rules" only during application life.
* This flavor is used for test purposes
 
### Production 
* Contains implementation, which uses android locationManager, WifiManager to get gps coordincates, 
wifi network name.
* This implentation saves "User entered rules".
* This is project flavor you want to look  into.

# Usage 

App supports multiwindow.

To create geofence rules fill "inputs":
1) Enter geofence zone Wifi network name
2) Enter geofence zone center Lattitude
3) Enter geofence zone center Longitude
4) Enter radius of geofence zone
5) Press button "Save new geofence configuration


<img align="left" height="400"   src="https://github.com/denis-royz/Geofence/blob/develop/screanshots/screanshot1.png">

To start geofence search - click switch button "ON/OFF"
While geofence search is enabled - text under button "ON/OFF" will reliably show, are you in geofence area.
If geofence search is not enabled - text under button "ON/OFF" will show last information he have.

To start geofence search - click switch button "ON/OFF"
While geofence search is enabled - text under button "ON/OFF" will reliably show, are you in geofence area.
If geofence search is not enabled - text under button "ON/OFF" will show last information he have.

<br /><br /><br /><br /><br /><br />
---
<img align="left" height="400"   src="https://github.com/denis-royz/Geofence/blob/develop/screanshots/screanshot4.png">
While geofence search is enabled - you can see notification in notification bar, which contain information, 
are you in geofence area, and how long you last time (entered/leaved) geofence area.

<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
---
<img align="left" height="400"  src="https://github.com/denis-royz/Geofence/blob/develop/screanshots/screanshot2.png">
If you are using android M+ device, and you have not provided Location permission to this app - you will find out
card with red text, which requests you to enables Location permission.
By clocking this card you will be requested to allow app to use Location permission.
