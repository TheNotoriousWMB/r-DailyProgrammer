#IRC: Making a Connection

##Description

A network socket is an endpoint of a connection across a computer network. Today, most communication between computers is based on the Internet Protocol; therefore most network sockets are Internet sockets. Internet Relay Chat

(IRC) is a chat system on the Internet. It allows people from around the world to have conversations together, but it can also be used for two people to chat privately.

Freenode is an IRC network used to discuss peer-directed projects. Their servers are all accessible from the domain names chat.freenode.net and irc.freenode.net. In 2010, it became the largest free and open source software-focused IRC network. In 2013 it became the largest IRC network, encompassing more than 90,000 users and 40,000 channels and gaining almost 5,000 new users per year. We have a channel on freenode ourselves for all things /r/DailyProgrammer on freenode, which is #reddit-dailyprogrammer.

Your challenge today will be to communicate with the freenode IRC server. This will consist of opening a TCP socket to freenode and sending two protocol messages to initiate the connection. The original IRC RFC

defines a message as a line of text up to 512 bytes starting with a message code, followed by one or more space separated parameters, and ending with a CRFL (\r\n). The last paramater can be prefixed by a colon to mark it as a parameter that can contain spaces, which will take up the rest of the line. An example of a colon-prefixed parameter would be the contents of a chat message, as that is something that contains spaces.

The first of the two initiation messages (NICK) defines what name people will see when you send a chat message. It will have to be unique, and you will not be able to connect if you specify a name which is currently in use or reserved. It has a single parameter <nickname> and will be sent in the follwing form:

    NICK <nickname>

The second of the two initiation messages (USER) defines your username, user mode, server name, and real name. The username must also be unique and is usually set to be the same as the nickname. Originally, hostname was sent instead of user mode, but this was changed in a later version of the IRC protocol. For our purposes, standard mode 0 will work fine. As for server name, this will be ignored by the server and is conventionally set as an asterisk (*). The real name parameter can be whatever you want, though it is usually set to be the same value as the nickname. It does not have to be unique and may contain spaces. As such, it must be prefixed by a colon. The USER message will be sent in the following form:

    USER <username> 0 * :<realname>

##Input Description

You will give your program a list of lines specifying server, port, nickname, username, and realname. The first line will contain the server and the port, separated by a colon. The second through fourth lines will contain nick information.

    chat.freenode.net:6667
    Nickname
    Username
    Real Name

##Output Description

Your program will open a socket to the specified server and port, and send the two required messages. For example:

    NICK Nickname
    USER Username 0 * :Real Name

Afterwards, it will begin to receive messages back from the server. Many messages sent from the server will be prefixed to indicate the origin of the message. This will be in the format :server or :nick[!user][@host], followed by a space. The exact contents of these initial messages are usually not important, but you must output them in some manner. The first few messages received on a successful connection will look something like this:

    :wolfe.freenode.net NOTICE * :*** Looking up your hostname...
    :wolfe.freenode.net NOTICE * :*** Checking Ident
    :wolfe.freenode.net NOTICE * :*** Found your hostname
    :wolfe.freenode.net NOTICE * :*** No Ident response
    :wolfe.freenode.net 001 GeekDude :Welcome to the freenode Internet Relay Chat Network GeekDude

##Challenge Input

The server will occasionally send PING messages to you. These have a single parameter beginning with a colon. The exact contents of that parameter will vary between servers, but is usually a unique string used to verify that your client is still connected and responsive. On freenode, it appears to be the name of the specific server you are connected to. For example:

    PING :wolfe.freenode.net

##Challenge Output

In response, you must send a PONG message with the parameter being the same unique string from the PING. You must continue to do this for the entire time your program is running, or it will get automatically disconnected from the server. For example:

    PONG :wolfe.freenode.net

************************************************************************************************************************************************************
Sample run:
************************************************************************************************************************************************************

    Server: chat.freenode.net
    Port: 6667
    Nickname: FWM
    Username: AgentMulder
    Real Name: Fox Mulder

    :adams.freenode.net NOTICE * :*** Looking up your hostname...
    :adams.freenode.net NOTICE * :*** Checking Ident
    :adams.freenode.net NOTICE * :*** Found your hostname
    :adams.freenode.net NOTICE * :*** No Ident response
    :adams.freenode.net 001 FWM :Welcome to the freenode Internet Relay Chat Network FWM
    :adams.freenode.net 002 FWM :Your host is adams.freenode.net[94.125.182.252/6667], running version ircd-seven-1.1.3
    :adams.freenode.net 003 FWM :This server was created Mon Jul 20 2015 at 16:58:36 UTC
    :adams.freenode.net 004 FWM adams.freenode.net ircd-seven-1.1.3 DOQRSZaghilopswz CFILMPQSbcefgijklmnopqrstvz bkloveqjfI
    :adams.freenode.net 005 FWM CHANTYPES=# EXCEPTS INVEX CHANMODES=eIbq,k,flj,CFLMPQScgimnprstz CHANLIMIT=#:120 PREFIX=(ov)@+ MAXLIST=bqeI:100 MODES=4 NETWORK=freenode KNOCK STATUSMSG=@+ CALLERID=g :are supported by this server
    :adams.freenode.net 005 FWM CASEMAPPING=rfc1459 CHARSET=ascii NICKLEN=16 CHANNELLEN=50 TOPICLEN=390 ETRACE CPRIVMSG CNOTICE DEAF=D MONITOR=100 FNC TARGMAX=NAMES:1,LIST:1,KICK:1,WHOIS:1,PRIVMSG:4,NOTICE:4,ACCEPT:,MONITOR: :are supported by this server
    :adams.freenode.net 005 FWM EXTBAN=$,ajrxz WHOX CLIENTVER=3.0 SAFELIST ELIST=CTU :are supported by this server
    :adams.freenode.net 251 FWM :There are 152 users and 91136 invisible on 24 servers
    :adams.freenode.net 252 FWM 23 :IRC Operators online
    :adams.freenode.net 253 FWM 6 :unknown connection(s)
    :adams.freenode.net 254 FWM 52732 :channels formed
    :adams.freenode.net 255 FWM :I have 4729 clients and 1 servers
    :adams.freenode.net 265 FWM 4729 12000 :Current local users 4729, max 12000
    :adams.freenode.net 266 FWM 91288 97578 :Current global users 91288, max 97578
    :adams.freenode.net 250 FWM :Highest connection count: 12001 (12000 clients) (230707 connections received)
    :adams.freenode.net 375 FWM :- adams.freenode.net Message of the Day - 
    :adams.freenode.net 372 FWM :- Welcome to adams.freenode.net. Thanks to ATW Internet Kft 
    :adams.freenode.net 372 FWM :- (http://www.atw.hu) for sponsoring this server!
    :adams.freenode.net 372 FWM :-  
    :adams.freenode.net 372 FWM :- ADAMS, DOUGLAS (1952-2001).  Author of The Hitch Hikers Guide
    :adams.freenode.net 372 FWM :- to the Galaxy and many other witty and humourous books,
    :adams.freenode.net 372 FWM :- portrayed in his uniquely British irony. He is sorely missed
    :adams.freenode.net 372 FWM :- by many millions of devoted fans. "So long and thanks for all
    :adams.freenode.net 372 FWM :- the books!"
    :adams.freenode.net 372 FWM :-  
    :adams.freenode.net 372 FWM :- Welcome to freenode - supporting the free and open source
    :adams.freenode.net 372 FWM :- software communities since 1998.
    :adams.freenode.net 372 FWM :-  
    :adams.freenode.net 372 FWM :- By connecting to freenode you indicate that you have read and
    :adams.freenode.net 372 FWM :- accept our policies as set out on http://www.freenode.net
    :adams.freenode.net 372 FWM :- freenode runs an open proxy scanner. Please join #freenode for
    :adams.freenode.net 372 FWM :- any network-related questions or queries, where a number of
    :adams.freenode.net 372 FWM :- volunteer staff and helpful users will be happy to assist you.
    :adams.freenode.net 372 FWM :-  
    :adams.freenode.net 372 FWM :- You can meet us at FOSSCON (http://www.fosscon.org) where we get
    :adams.freenode.net 372 FWM :- together with like-minded FOSS enthusiasts for talks and
    :adams.freenode.net 372 FWM :- real-life collaboration.
    :adams.freenode.net 372 FWM :-  
    :adams.freenode.net 372 FWM :- We would like to thank Private Internet Access
    :adams.freenode.net 372 FWM :- (https://www.privateinternetaccess.com/) and the other
    :adams.freenode.net 372 FWM :- organisations that help keep freenode and our other projects
    :adams.freenode.net 372 FWM :- running for their sustained support.
    :adams.freenode.net 372 FWM :-  
    :adams.freenode.net 372 FWM :- In particular we would like to thank the sponsor
    :adams.freenode.net 372 FWM :- of this server, details of which can be found above.
    :adams.freenode.net 372 FWM :-  
    :adams.freenode.net 376 FWM :End of /MOTD command.
    :FWM MODE FWM :+i
    PING :adams.freenode.net
