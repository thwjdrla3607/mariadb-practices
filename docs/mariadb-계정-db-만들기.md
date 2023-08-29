1. 모든 작업은 dba(root)로 한다.
'''sh
# mysql - u root -p
'''

2. 데이터베이스 생성
'''sh
MariaDB [(none)]> create database local;
MariaDB [(none)]> show databases;

억까심하네요ㅕ
'''

3. 사용자 생성
'''sh
MariaDB [(none)]> create user 'webdb'@'192.168.*' identified by 'webdb';
하이
'''

4. 권한 주기
'''sh
MariaDB [(none)]> grant all privileges on webdb.* to 'webdb'@'192.168.*';
MariaDB [(none)]> flush privileges;
'''

5. 확인하기
'''sh
# mysql -u webdb -D webdb -p
Enter password:
'''