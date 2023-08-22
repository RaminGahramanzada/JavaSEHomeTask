package org.example;
import java.util.Objects;

public class Person extends Human {
    private Person father;
    private Person mother;

    public Person(String name, String surname, int age, Person mother, Person father) {
        super(name, surname, age);
        this.father = father;
        this.mother = mother;
    }

    public Person(String name, String surname, int age) {
        super(name, surname, age);
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getFather(), person.getFather()) && Objects.equals(getMother(), person.getMother());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFather(), getMother());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", age=" + getAge() +
                ", father=" + (father != null ? father.getName() : "null") +
                ", mother=" + (mother != null ? mother.getName() : "null") +
                '}';
    }



}



