package com.example.demo;

class Book1 {
    String name = "Book1";
    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}

class Book2 extends Book1 {
    String name = "Book2";
    void setName(String name) {
        super.name = name;
    }
    String getName() {
        return name;
    }
}

class Test {
    public static void main(String[] args) {
        Book1 a = new Book2();
        Book2 b = new Book2();
        a.setName("Book3");
        b.setName("Book4");
        System.out.print(a.getName());
        System.out.print(b.getName());
        System.out.print(a.name);
    }
} 