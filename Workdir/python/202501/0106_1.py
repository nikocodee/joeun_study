# -*- coding: utf-8 -*-
"""
Created on Mon Jan  6 10:21:07 2025

@author: tjoeun
"""

file = open("example.txt", "w")  # 쓰기 모드
file.write("Python is fun!\n")
file.write("File handling is simple.")
file.close()

file = open("example.txt", "r")
line = file.readline()  # 첫 번째 줄 
print("-"*20)
print(line)
print("-"*20)
file.seek(0)
line = file.readline()
print(line)
print("-"*20)
file.close()

file = open("example.txt", "x")  # 쓰기 모드
file.write("Pythottttttttt!\n")
file.write("File handddddddd.")
file.close()


file = open("example.txt", "r")
lines = file.readlines()  # 줄별로 리스트 반환
print(lines)
file.close()