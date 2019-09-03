--------------------
HOW TO USE
--------------------
This library is not currently available on the Maven central repository. If you wish to use this 
library you will need to clone this repo and import the project using the following import 
syntax:

```
<dependency>
    <groupId>com.stevo</groupId>
    <artifactId>Cryptic</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

--------------------
DESCRIPTION
--------------------
The library provides private key encryption using AES, DES and Triple DES encryption, using Cipher Block Chaining mode and 
automatically pads the results if necessary.

The output after a file is encrypted is in the form of two files. One file has a .cryptic file extension which is the 
encrypted file. The other file has a .cryptickey extension which is the key to decrypt the file when needed.

NOTE: IF YOU LOSE THE KEY OR INTERRUPT THE ENCRYPTION/DECRYPTION PROCESS YOU MAY LOSE ALL OF THE DATA YOU ARE 
ENCRYPTING/DECRYPTING.

--------------------
EXAMPLE CODE
--------------------
Upon importing the project and configuring the dependencies, you can use the following convenience methods:

The three type of encryption that can be specified are:
"AES/CBC/PKCS5Padding"
"DES/CBC/PKCS5Padding"
"DESede/CBC/PKCS5Padding"

```
Crypter crypter = new Crypter();
ICrypt iCrypt = crypter.getCrypter();

iCrypt.encryptFile([File object to encrypt], [Output directory to save encrypted file], "AES/CBC/PKCS5Padding", (String description, double progress) -> {

    System.out.println(description + " " + progress);
});

iCrypt.decryptFile([File object to decrypt], [Output directory to save decrypted file], [File object key], (String description, double progress) -> {

    System.out.println(description + " " + progress);
});
```
