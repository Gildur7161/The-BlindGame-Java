<p align="center"><img src="Blindtest.main/bin/icon.png" height="128" alt="The-Blindgame"></p>
<h3 align="center">The Blindgame</h3>
<p align="center"><i>A community based blindtest game</i><p>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0">
    <img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=for-the-badge" alt="Apache 2.0 License"/>
  </a>
  <a href="https://github.com/Gildur7161/The-BlindGame-Java/issues">
    <img src="https://img.shields.io/github/issues/Gildur7161/The-BlindGame-Java.svg?style=for-the-badge" alt="issues"/>
  </a>
  <a href="https://www.java.com/fr/">
    <img src="https://img.shields.io/badge/MADE%20WITH-JAVA-red.svg?style=for-the-badge" alt="Java">
  </a>
  <a href="https://github.com/Gildur7161/The-BlindGame-Java/stargazers">
      <img src="https://img.shields.io/github/stars/Gildur7161/The-BlindGame-Java.svg?style=for-the-badge" alt="Stars">
  </a>
  <a href="https://paypal.me/AmauryChabane">
    <img src="https://img.shields.io/badge/$-donate-ff69b4.svg?style=for-the-badge" alt="Donate">
  </a>
</p>

<p align="center">
  <a href="#overview">Overview</a> •
  <a href="#how-to-install">How to install</a> •
  <a href="#how-to-use">How to use</a> •
  <a href="#license">License</a> •
  <a href="#donate">Donate</a>
</p>

# Overview
* Easy to use
* Simple interface
* Any theme is supported
* An category is supported
* File sharing on LAN included

<p align="center">
  <img src="assets/img/play.jpg">
</p>

# How to install

Download latest version [here !](https://github.com/Gildur7161/Blindtest/releases)

If you have already Java installed on your computer, just download the "without JRE" version. If you're not sure or you don't know what is Java or what is JRE, or just if you want a portable version, download the "with JRE" version.

No need to install, just uncompress it wherever you want and you're done !

# How to use

## Create your theme

You can add as many themes as you want. 
1) Create a folder in the "files" directory
2) Add in your freshly created folder the audio file (currently support mp3 and wav files). If you don't, a warning icon will be displayed on the main screen and beside the theme. You can aunch anyway, but the theme will not be used
3) Add the theme cover as a png, jpg, or jpeg file. If you don't, an "Image not found" cover will be displayed
4) Copy the "theme.properties" file from another theme or from the one in the "bin" directory. If you don't, one will be created automatically on launch

### theme.properties infos

- `title` - The movie/TV show/cartoon/... the theme is from
- `composer` - The theme composer(s)
- `type` - The category the theme is from (ex: Film, TV show, cartoon, ...)
- `release` - The release date
- `timecode` - The time in second from where you want to start your theme from
- `infos` - Any idditionnal informations

All fields must be on one line, including the infos one. The game will take care of it

## In-game content

<p align="center">
  <img src="assets/img/main_menu.jpg">
</p>

- You can click on a theme cover to modify any informations within "theme.properties"
- You can play the theme in this panel (will start from the timecode in corresponding field) : the play button will inform you about the current time code. You can change the timecode and restart from it without having to get back to the main menu
- If and audio file is missing, you can download it directly from youtube URL
<p align="center">
  <img src="assets/img/theme_editor_1.jpg">
</p>
- If an audio file is detected
<p align="center">
  <img src="assets/img/theme_editor_2.jpg">
</p>

## Options

<p align="center">
  <img src="assets/img/options.jpg">
</p>
- Choose the category(ies) you want to use my checking the checkboxes (clicking on the category name will work too)
- Set the time to guess as you prefer (in seconds)


# License

This project is under Apache License 2.0 - see [LICENSE.md](LICENSE.md) for more details.

# Donate
You can buy me a coffee using this link (trust me, it helps keeping projects like this updated): https://paypal.me/AmauryChabane
