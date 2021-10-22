# 零基础项目

## 服务端

公钥，私钥

### keystore

存储：密钥和证书+公钥的一个实体。（1.公私钥对，2.证书公钥）

### keytools

生成keystore

```sh
PS D:\Java\jdk1.8.0_131\bin> .\keytool.exe -genkeypair -alias dongbao-alias -keypass 123456 -keyalg RSA -keysize 1024 -validity 365 -keystore e:\cer2\dongbao-alias.keystore -storepass 123456
您的名字与姓氏是什么?
  [Unknown]:  chao
您的组织单位名称是什么?
  [Unknown]:  msb
您的组织名称是什么?
  [Unknown]:  msb
您所在的城市或区域名称是什么?
  [Unknown]:  bj
您所在的省/市/自治区名称是什么?
  [Unknown]:  bj
该单位的双字母国家/地区代码是什么?
  [Unknown]:  cn
CN=chao, OU=msb, O=msb, L=bj, ST=bj, C=cn是否正确?
  [否]:  是

PS D:\Java\jdk1.8.0_131\bin>
```

查看keystore

```sh
PS D:\Java\jdk1.8.0_131\bin> .\keytool.exe -list -v -keystore E:\cer2\dongbao-alias.keystore
输入密钥库口令:

密钥库类型: JKS
密钥库提供方: SUN

您的密钥库包含 1 个条目

别名: dongbao-alias
创建日期: 2021-5-14
条目类型: PrivateKeyEntry
证书链长度: 1
证书[1]:
所有者: CN=chao, OU=msb, O=msb, L=bj, ST=bj, C=cn
发布者: CN=chao, OU=msb, O=msb, L=bj, ST=bj, C=cn
序列号: be4b676
有效期开始日期: Fri May 14 20:17:28 CST 2021, 截止日期: Sat May 14 20:17:28 CST 2022
证书指纹:
         MD5: FE:6C:2B:E6:10:AF:4E:65:E6:23:31:2C:0E:7E:B5:65
         SHA1: 02:69:99:B8:FB:7A:84:FD:53:88:60:60:24:9F:91:9D:9A:0B:35:E4
         SHA256: ED:DF:DE:9E:15:EA:CB:68:76:94:FE:C1:C9:B2:89:93:51:FA:E8:5C:9E:9D:B6:03:05:79:87:DD:E0:89:EB:82
         签名算法名称: SHA256withRSA
         版本: 3

扩展:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: B4 B9 C3 B6 5A B8 4E 15   AA CB D3 4D 17 8C E1 38  ....Z.N....M...8
0010: 35 3D B5 94                                        5=..
]
]



*******************************************
*******************************************


PS D:\Java\jdk1.8.0_131\bin> .\keytool.exe -list -rfc -keystore E:\cer2\dongbao-alias.keystore
输入密钥库口令:

密钥库类型: JKS
密钥库提供方: SUN

您的密钥库包含 1 个条目

别名: dongbao-alias
创建日期: 2021-5-14
条目类型: PrivateKeyEntry
证书链长度: 1
证书[1]:
-----BEGIN CERTIFICATE-----
MIICPjCCAaegAwIBAgIEC+S2djANBgkqhkiG9w0BAQsFADBSMQswCQYDVQQGEwJj
bjELMAkGA1UECBMCYmoxCzAJBgNVBAcTAmJqMQwwCgYDVQQKEwNtc2IxDDAKBgNV
BAsTA21zYjENMAsGA1UEAxMEY2hhbzAeFw0yMTA1MTQxMjE3MjhaFw0yMjA1MTQx
MjE3MjhaMFIxCzAJBgNVBAYTAmNuMQswCQYDVQQIEwJiajELMAkGA1UEBxMCYmox
DDAKBgNVBAoTA21zYjEMMAoGA1UECxMDbXNiMQ0wCwYDVQQDEwRjaGFvMIGfMA0G
CSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEXr8P0dlvpZRhcKSkKgfzSRA3TpH8RfZc
7b529PeiaRQGm7cXlath5w0Nj1gs6jZWSzltcf7SIdMEkpxTX4/xbXt8v/87L7Dd
icGOt4+VVh6NOrqB9HhNqeEtGRMv+DAHg6zij3uA+YCNA40Oretojjf4v51QSsvf
Qv6W4DWhTQIDAQABoyEwHzAdBgNVHQ4EFgQUtLnDtlq4ThWqy9NNF4zhODU9tZQw
DQYJKoZIhvcNAQELBQADgYEAO44gcNl5M2J8aYovmV+yh48gvF3JXjE0UudfHony
/3Gy36HIvJxADrKcTZ+6ScCg9w/BzJdYaQyHYvALG/ICMc4EMQhwPsiDu4VGLQqF
HxWSY0Is5gBSIV+WHa+KxgQ5j05qeIOIxt5peBJg/+ShuAdyn2e0OIwzaUq6fcqh
698=
-----END CERTIFICATE-----


*******************************************
*******************************************


PS D:\Java\jdk1.8.0_131\bin>
```



代码验证过了。公私钥对没问题。



## web项目

一个简单的web项目。

不支持https





## 客户端

CA的公钥

导出证书

```sh
PS D:\Java\jdk1.8.0_131\bin> .\keytool.exe -exportcert -alias dongbao-alias -keystore E:\cer2\dongbao-alias.keystore -file e:\cer2\my.cer
输入密钥库口令:
存储在文件 <e:\cer2\my.cer> 中的证书
PS D:\Java\jdk1.8.0_131\bin>
```



# 生成带域名的keystore

```sh
PS D:\Java\jdk1.8.0_131\bin> .\keytool.exe -genkeypair -alias dongbao-alias -keypass 123456 -keyalg RSA -validity 365 -keystore e:\cer2\dongbao-alias.keystore -storepass 123456 -ext san=dns:localhost
您的名字与姓氏是什么?
  [Unknown]:  chao
您的组织单位名称是什么?
  [Unknown]:  msb
您的组织名称是什么?
  [Unknown]:  msb
您所在的城市或区域名称是什么?
  [Unknown]:  msb
您所在的省/市/自治区名称是什么?
  [Unknown]:  cn
该单位的双字母国家/地区代码是什么?
  [Unknown]:  cn
CN=chao, OU=msb, O=msb, L=msb, ST=cn, C=cn是否正确?
  [否]:  是

PS D:\Java\jdk1.8.0_131\bin> .\keytool.exe -exportcert -alias dongbao-alias -keystore E:\cer2\dongbao-alias.keystore -file e:\cer2\my.cer
输入密钥库口令:
存储在文件 <e:\cer2\my.cer> 中的证书
PS D:\Java\jdk1.8.0_131\bin>
```



# nginx

自己装openssl

```sh
PS E:\cer2\nginx> openssl genrsa -des3 -out dongbao.key 1024
Loading 'screen' into random state - done
Generating RSA private key, 1024 bit long modulus
..................++++++
....++++++
e is 65537 (0x10001)
Enter pass phrase for dongbao.key:
Verifying - Enter pass phrase for dongbao.key:
PS E:\cer2\nginx> openssl req -new -key dongbao.key -out dongbao.csr
Enter pass phrase for dongbao.key:
Loading 'screen' into random state - done
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:cn
State or Province Name (full name) [Some-State]:bj
Locality Name (eg, city) []:bj
Organization Name (eg, company) [Internet Widgits Pty Ltd]:msb
Organizational Unit Name (eg, section) []:msb
Common Name (e.g. server FQDN or YOUR name) []:cpf.com
Email Address []:asdasdf@qq.com

Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:123456
An optional company name []:
PS E:\cer2\nginx> openssl rsa -in .\dongbao.key.src -out dongbao.key
Enter pass phrase for .\dongbao.key.src:
writing RSA key
PS E:\cer2\nginx> openssl x509 -req -days 365 -in .\dongbao.csr -signkey .\dongbao.key -out dongbao.crt
Loading 'screen' into random state - done
Signature ok
subject=/C=cn/ST=bj/L=bj/O=msb/OU=msb/CN=cpf.com/emailAddress=asdasdf@qq.com
Getting Private key
PS E:\cer2\nginx>
```



conf

```sh

#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html;
            index  index.html index.htm;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}
	
	
	upstream com.cpf {
		server 127.0.0.1:8080; # 需要监听的端口名 我用的
		keepalive 64;
	}

    # HTTPS server
    #
    server {
        listen       443 ssl;
        server_name  cpf.com;
		

        ssl_certificate      E://cer2//nginx//dongbao.crt;
        ssl_certificate_key  E://cer2//nginx//dongbao.key;
		

        ssl_session_cache    shared:SSL:1m;
        ssl_session_timeout  5m;

        ssl_ciphers  HIGH:!aNULL:!MD5;
        ssl_prefer_server_ciphers  on;

        location / {
            proxy_pass http://com.cpf;
        }
    }
	

}


```

