# -*- coding: utf-8 -*-
"""
Created on Mon Jan  6 10:46:41 2025

@author: tjoeun
"""

file = open("./example.txt", "w")  # 쓰기 모드
file.write("Python is fun!\n")
file.write("File handling is simple.")
file.close()