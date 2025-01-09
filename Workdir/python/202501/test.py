txt = ""
try:
    file = open("test.txt", "r")
    txt = file.read()
except Exception:    
    file = open("test.txt", "rt", encoding="UTF-8")
    txt = file.read()
    file.seek(0)
print(txt)
print("="*20)
txt_list = txt.split("\n")
print(txt_list)
print("="*20)    
for i in range(len(txt_list)):
    txtLine = file.readline()
    print(txtLine)
file.close()
