# -*- coding: utf-8 -*-
"""
Created on Mon Jan  6 12:13:12 2025

@author: tjoeun
"""

fruits = ["apple", "banana", "cherry"]

for _ in fruits:
    	print("I like fruit.")
    	print("I like fruit.")
    	print("I like fruit.")
              print("I like fruit.")

names = ["apple", "anything", "banana", "afternoon", "cream"]
i = 0
while names[i][0] == "a":
    print(names[i])    
    i += 1
    
names = ["apple", "anything", "banana", "afternoon", "cream"]
i = 0
while i < len(names):
    if names[i][0] == "a":
        print(names[i])    
    i += 1
    

b = {'test':100, 'program':2}
try:
    b["python"] = b["python"] + 1
except:
    b["python"] = 1
print(b["python"])    


#b = {'test':100, 'program':2}
cnt = b.get("python")
if cnt == None:
    b["python"] = 1
else:
    b["python"] = b["python"] + 1
print(b["python"])      