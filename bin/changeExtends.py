import os

def eachFile(filepath):
    pathDir =  os.listdir(filepath)
    for allDir in pathDir:
        child = os.path.join('%s%s' % (filepath, allDir))
        print child.decode('gbk') # .decode('gbk')是解决中文显示乱码问题

def readFile(filenames):
        fopen = open(filenames, 'w+') # r 代表read
        fileread = fopen.read()
        
        fildread.replace("extends JFrame", "extends HFrame")
        fopen.write(fileread)

        fopen.close()