import frida
import sys
PACKAGE ='com.zhangsheng.shunxin'
if __name__ == '__main__':
    jscode = open('script.js','r',encoding='UTF-8').read()
    process = frida.get_usb_device().attach(PACKAGE)
    print(process)
    script = process.create_script(jscode)
    print('[*] Running CTF')
    script.load()
    sys.stdin.read()