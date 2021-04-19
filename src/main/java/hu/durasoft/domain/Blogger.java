package hu.durasoft.domain;

import java.util.List;

//@Entity
public class Blogger {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Id
    private Long id;
    private String name;
    private int age;
    //@JsonBackReference
    //@OneToMany(mappedBy = "blogger")
    private List<Story> stories;

    private Blogger() {
    }

    public Blogger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Blogger(Long id, String name, int age) {
        this(name, age);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

}
