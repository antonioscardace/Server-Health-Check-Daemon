# Server-Health-Check-Daemon

_This Project Was Created as a Personal Project, to Practice the Following Skills:_

- _Knowledge of Java_ 
- _Knowledge of Telegram and Slack APIs_
- _Software Engineering (e.g. Design Patterns)_

[![CodeFactor](https://www.codefactor.io/repository/github/antonioscardace/server-health-check-daemon/badge)](https://www.codefactor.io/repository/github/antonioscardace/server-health-check-daemon)

## Introduction

The project aims to check the health of a list of servers every **T** second(s).

An alert message will be sent on a **Telegram Channel** or to a **Slack Channel** or to an **Email**, in two cases:

- If a server is unhealthy and **goes offline**.
- If a server that has been reported as unhealthy **comes back online**.

Each server can be checked in two different ways:

- By Ping if it has been saved in the database as an **IP Address**.
- By an HTTP Request (GET) if it has been saved in the database as an **HTTP URL**. <br/> An HTTP request can be made just for URLs which start with ``http://`` or ``https://``.

## Daemon Structure

![Project UML](/imgs/uml/app-v3.svg)

The **Database** has the following E-R diagaram:

<img alt="Database ER" src="/imgs/uml/db.svg" style="width: 500px;"/>

The project also contains **config.properties** that is a file for configuration. <br/>
It contains some system variables - I read them using **Config.java** - which you can modify: 

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
- ``server``: is the MySQL server address.
- ``port``: is the port for connecting to the MySQL server - ``3306`` by default.
- ``userid``: is the username for connecting to the MySQL server.
- ``password``: is the password for connecting to the MySQL server.
- ``dbName``: is the database name - ``server_check`` by default. 

## Telegram Bot

I have created [Server Health Check Daemon](https://t.me/server_health_checker_bot) - [@server_health_checker_bot](https://t.me/server_health_checker_bot) using [BotFather](https://t.me/botfather). </br>
If you wanna use it you just have to add it as an administrator in your channel.

![Bot Logo](/imgs/telegram-bot-logo.png)

## Slack

To post messages into Slack you can follow [this](https://medium.com/@sharan.aadarsh/sending-notification-to-slack-using-python-8b71d4f622f3) guide.

**Incoming WebHooks** will also return a **Webhook URL** parsed as ``https://hooks.slack.com/services/TOKEN``. You will have to insert just the ``TOKEN`` into the database. I also suggest you set the username and the icon that the integration will post.

## Demo

I have simulated a disconnection and a reconnection of my local server. <br/>
Here is the result on an Email, on a Public Telegram Channel (which I made just to test), and on Slack.

<img alt="Email Screen" src="/imgs/snaps/email.png" style="width: 600px;"/>

![Telegram Channel Screen](/imgs/snaps/telegram-channel.png)

![Slack Screen](/imgs/snaps/slack.png)

## Getting Started

So that the repository is successfully cloned and project run smoothly, a few steps need to be followed.

### Requisites

- The use of [Visual Studio](https://visualstudio.microsoft.com/downloads/) is recommended.
- Need to download and install Java and Maven.
- Need to have MySQL, if you don't have it anywhere, download and install it [from here](https://dev.mysql.com/downloads/installer/).
- Need to have a Telegram or a Slack or an Email account to receive alerts.

### Installation for Developers

1. Clone the repository
```sh
   git clone https://github.com/ElephanZ/Server-Health-Check-Daemon.git
```  
2. Import database schema on MySQL from ``app/resources/schema.sql``.
3. Insert rows into the database. For instance:
```sql
   INSERT INTO `servers` VALUES ('8.8.8.8', 'ip');
   INSERT INTO `accounts` VALUES ('@MY_CHANNEL_TAG', 'telegram');
   INSERT INTO `accounts` VALUES ('T123456/ABCDEF/justF0rT3st1ng', 'slack');
   INSERT INTO `observes` VALUES ('@MY_CHANNEL_TAG', '8.8.8.8');
   INSERT INTO `observes` VALUES ('T123456/ABCDEF/justF0rT3st1ng', '8.8.8.8');
``` 
4. Run the daemon
```sh
   cd YOUR_PROJECT_PATH/Server-Health-Check-Daemon/app/
   mvn validate
   mvn compile
   mvn test
   mvn exec:java -Dexec.mainClass=com.antonioscardace.app.App
```

## Future Improvements

- [x] Add Slack as Sender component.
- [ ] Dockerize all components.
- [ ] Implement input of server address and channel tag by console.

## License :copyright:

Author: [Antonio Scardace](https://antonioscardace.altervista.org/). <br/>
See ``LICENSE`` for more information.
