import os
from threading import Thread
import subprocess


def exec_commond(commond):
    print( "运行的命令为：" + commond )
    os.system( commond )


def exec_bat(batcommond):
    p = subprocess.Popen( batcommond, shell=True, stdout=subprocess.PIPE )
    stdout, stderr = p.communicate()
    print(p.returncode)  # is 0 if success


if __name__ == '__main__':
    print( "开始执行工具命令脚本" )
    # 执行 nacos
    # 执行 redis
    # 执行 sentinel
    # 执行 rabbitmq
    commondStr = ['D:\\codingutil\\nacos\\nacos-server-1.4.1\\nacos\\bin\\startup.cmd',
                  'java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar D:\\codingutil\\sentinel-dashboard-1.8.2.jar',
                  '']
    # canal 最好手动运行
    os.system( "D:\\codingutil\canal.deployer-1.1.5\\bin\\startup.bat" )
    # exec_bat(batStr);
    for commond in commondStr:
        t = Thread( target=exec_commond, args=(commond,) )
        t.start();
    os.chdir( "D:\\codingutil" )
    os.system( "redis-server.exe" )
