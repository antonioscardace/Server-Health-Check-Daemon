# Server-Health-Check-Daemon

_This Project Was Created as a Personal Project, to Practice the Following Skills:_

- _Knowledge of C#_ 
- _Knowledge of Telegram APIs_
- _Software Engineering (e.g. Design Patterns)_

## Introduction

The project aims to check the health of a list of servers every **T** second(s).

An alert message will be sent on a **Telegram Channel** or to an **Email**, in two cases:

- If a server is unhealthy and **goes offline**.
- If a server that has been reported as unhealthy **comes back online**.

Each server can be checked in two different ways:

- By Ping if it has been saved in the database as an **IP Address**.
- By an HTTP Request (GET) if it has been saved in the database as an **HTTP URL**. <br/> An HTTP request can be made just for URLs which start with ``http://`` or ``https://``.

## Daemon Structure

![Project UML](/imgs/uml/daemon-v1.svg)

The **Database** has the following E-R diagaram:

<img alt="Database ER" src="/imgs/uml/db.svg" style="width: 500px;"/>

The project also contains **App.config** that is an XML file for configuration. <br/>
It contains some system variables - I read them using **/Config/ConfigManager.cs** - which you can modify: 

- ``checkTime``: is the time **T** (in seconds) before checking every time all server's health.
- ``httpTimeout``: is the HTTP timeout (in seconds) before sending the alert.
- ``pingTimeout``: is the Ping timeout (in seconds) before sending the alert.<br/><br/>
- ``emailSubject``: is the alert email subject - ``Server Health Notice`` by default.
- ``senderEmail``: is the Sender email (your).
- ``senderName``: is the name of the Sender that appears on the U.I. - ``Server Health Check Daemon`` by default.
- ``senderUsername``: is the Sender username to authenticate your sender account on SMTP server.
- ``senderPassword``: is the Sender password to authenticate your sender account on SMTP server.
- ``smtpHost``: is the SMTP host your email uses - e.g. ``smtp.gmail.com``.
- ``smtpPort``: is the SMTP port your email uses - e.g. ``465``.
- ``telegramToken``: is the Telegram Bot Token, useful to send the alerts on Telegram.<br/><br/>
- ``errorMessage``: is the text of the error message.
- ``solveMessage``: is the text of the reconnection message.<br/><br/>
- ``mysql_server_check``: is the MySQL connection string (server, port, user, password, db name).

## Telegram Bot

I have created [Server Health Check Daemon](https://t.me/server_health_checker_bot) - [@server_health_checker_bot](https://t.me/server_health_checker_bot) using [BotFather](https://t.me/botfather). </br>
If you wanna use it you just have to add it as an administrator in your channel.

![Bot Logo](/imgs/telegram-bot-logo.png)

## Demo

I have simulated a disconnection and a reconnection of my local server. <br/>
Here is the result on both a Public Telegram Channel (which I made just to test) and on an Email.

![Telegram Channel Screen](/imgs/snaps/telegram-channel.png) <br/>
<img alt="Email Screen" src="/imgs/snaps/email.png" style="width: 600px;"/>

## Getting Started

So that the repository is successfully cloned and project run smoothly, a few steps need to be followed.

### Requisites

- Need to download and install [Visual Studio](https://visualstudio.microsoft.com/downloads/).
- Need to have MySQL, if you don't have it anywhere, download and install it [from here](https://dev.mysql.com/downloads/installer/).
- Need to have a Telegram or an Email Account wherever you want to receive alerts.

### Installation for Developers

1. Clone the repository
```sh
   git clone https://github.com/ElephanZ/Server-Health-Check-Daemon.git
```  
2. Import database on MySQL.
3. Insert rows into the database. For instance:
```sql
   INSERT INTO `servers` VALUES ('8.8.8.8', 'ip');
   INSERT INTO `accounts` VALUES ('@MY_CHANNEL_TAG', 'telegram');
   INSERT INTO `observes` VALUES ('@MY_CHANNEL_TAG', '8.8.8.8');
``` 
4. Create a _New Console Project in C#_ in Visual Studio.
5. Copy and import through Visual Studio all the files contained in _/src/_.
6. Install these _NuGet Packets_: <br/>
   - ``MySql.Data``
   - ``System.Configuration.ConfigurationManager``
   - ``Telegram.Bot``

## Future Improvements

- [ ] Dockerize all components.
- [ ] Implement input of server address and channel tag by console.
- [ ] Add a GUI using Visual C#.

## License :copyright:

Author: [Antonio Scardace](https://antonioscardace.altervista.org/). <br/>
See ``LICENSE`` for more information.
