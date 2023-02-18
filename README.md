# CircularRevealThemesChanger
### Change Android Themes Instantly Using the Circular Reveal Animation
This library help you change Theme Dynamically with Circular Reveal Animation on Android.

the implementation based on this article [article](https://pspdfkit.com/blog/2020/change-android-themes-with-circular-reveal-animation/) big thanks to @Simone Arpe


<img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExNzY4NzIwYTRiODA2YTM2MzE4OGRhNTkyZDJjNTMyZWVlNzM1NDAwNCZjdD1n/GkhhDBVdy0UkFPKzBt/giphy.gif" width="300">


# Usage

### for smooth changing theme dynamically with circular reveal animation like Telegram app you just need to call this single line
  
    ThemeChanger(Activity, View).switchTheme()
    
 - Replace `Activity` with activity `context` 
 - Replace `View` with view reference that you want animation end at 


# Installation

### Gradle
Step 1. Add the JitPack repository to your build file Add it in your root `build.gradle` at the end of repositories:

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
recently you can find repositories block inside `settings.gradle` file so add this line under repositories block

    maven { url 'https://jitpack.io' }
   
    
Step 2. Add the dependency

    dependencies {
	        implementation 'com.github.MostafaAnter:CircularRevealThemesChanger:1.0.0'
	}
    
    
    
# Licence

    Copyright @2023 by Mostafa Anter

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
