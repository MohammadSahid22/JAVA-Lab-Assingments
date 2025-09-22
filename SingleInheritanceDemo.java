class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    void eat() {
        System.out.println(name + " is eating.");
    }

    void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

class Dog extends Animal {
    String breed;

    Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    void bark() {
        System.out.println(name + " is barking.");
    }

    void displayInfo() {
        System.out.println("Dog Name: " + name);
        System.out.println("Breed: " + breed);
    }
}

public class SingleInheritanceDemo {
    public static void main(String[] args) {
        Dog myDog = new Dog("Rocky", "German Shepherd");

        myDog.displayInfo();
        myDog.eat();
        myDog.sleep();
        myDog.bark();
    }
}
