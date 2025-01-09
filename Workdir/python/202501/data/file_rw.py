txt = ""
input_file = open("./temp.txt", "r", encoding="UTF-8")
txt = input_file.read()
txt_line = txt.split("\n")

output_file = open("./temp.dat", "w", encoding="UTF-8")

for i in range(len(txt_line)):
    output_file.write(str(i+1))
    output_file.write(". ")
    output_file.write(", ".join(txt_line[i].split(" ")) + "\n")

input_file.close()
output_file.close()    
