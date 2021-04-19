package hu.durasoft.controller;

import hu.durasoft.domain.Story;
import hu.durasoft.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private StoryService storyService;

    @Autowired
    @Qualifier("Story")
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @RequestMapping("/storyapi")
    public Story story() {
        return storyService.findFirstByOrderByPostedDesc();
    }

    @RequestMapping("/titleapi/{title}")
    public Story searchTitle(@PathVariable(value = "title") String title) {
        return storyService.findByTitle(title);
    }

    @RequestMapping("/storiesapi/{name}")
    public List<Story> searchStoriesByBloggerName(@PathVariable(value = "name") String name) {
        return storyService.getStoriesByBloggerName(name);
    }

}
