package hu.durasoft.service;

import hu.durasoft.domain.Blogger;
import hu.durasoft.domain.Story;
import hu.durasoft.repository.BloggerRepository;
import hu.durasoft.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class StoryService {

    private StoryRepository storyRepo;
    private BloggerRepository bloggerRepo;

    @Autowired
    @Qualifier(value = "story")
    public void setStoryRepo(StoryRepository storyRepo) {
        this.storyRepo = storyRepo;
    }

    @Autowired
    @Qualifier(value = "blogger")
    public void setBloggerRepo(BloggerRepository bloggerRepo) {
        this.bloggerRepo = bloggerRepo;
    }

    public List<Story> getStories() {
        return storyRepo.findAll();
    }

    public Story findFirstByOrderByPostedDesc() {
        return storyRepo.findFirstByOrderByPostedDesc();
    }

    public Story findByTitle(String title) {
        return storyRepo.findByTitle(title);
    }

    public List<Story> getStoriesByBloggerName(String name) {
        return storyRepo.findAllByBloggerNameIgnoreCaseOrderByPostedDesc(name);
    }

    @PostConstruct
    public void init() {
        Blogger blogger = new Blogger("Belső Sanyi", 40);
        bloggerRepo.save(blogger);
        Story story = new Story("Belső teszt", "Belső tartalom", new Date(), blogger);
        storyRepo.save(story);
    }

}
