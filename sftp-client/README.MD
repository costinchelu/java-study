## Sftp test server

Create a Docker container for sftp:

```commandline
docker volume create v1sftp
docker run --name sftptest -v v1sftp:/home/costinc2/sftpshare -p 222:22 -d atmoz/sftp costinc2:CostinC2:1001:


docker run --name sftptest -v /sftpshare:/home/costinc2/sftpshare -p 222:22 -d atmoz/sftp costinc2:CostinC2:1001:
```

**user**: costinc2  
**password**: CostinC2

can connect with WinSCP using localhost and port 222 . Starting location will be **/home/costinc2**

In the program, the share path is volume's path (**/sftpshare**)


using
```commandline
docker exec -it sftptest bash
```

file structure can be accessed as a root and operations like creating file and folders or assigning privileges can be done through the console:

	cd /home/costinc2
    chmod 777 sftpshare

> _This example contains the same credentials and paths used in the project._