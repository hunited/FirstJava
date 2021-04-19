package hu.durasoft.controller;

import hu.durasoft.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private static final String STORY = "story";
    private static final String FOOTER = "footerText";
    private static final String COPYRIGHT = "Made by DuraSoft © 2021";

    private StoryService storyService;

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @RequestMapping("/")
    public String stories(Model model) {
        model.addAttribute("pageTitle", "DuraSoft cikkek");
        model.addAttribute("stories", storyService.getStories());
        model.addAttribute(FOOTER, COPYRIGHT);
        return "stories";
    }

    @RequestMapping("/story")
    public String story(Model model) {
        model.addAttribute(STORY, storyService.findFirstByOrderByPostedDesc());
        model.addAttribute(FOOTER, COPYRIGHT);
        return STORY;
    }

    @RequestMapping("/blogger")
    public String blogger(Model model) {
        model.addAttribute("pageTitle", "DuraSoft cikkek");
        model.addAttribute("bloggers", storyService.getBloggers());
        model.addAttribute(FOOTER, COPYRIGHT);
        return "bloggers";
    }

    @RequestMapping("/title/{title}")
    public String searchTitle(@PathVariable(value = "title") String title, Model model) {
        if (title == null) {
            throw new IllegalArgumentException("Title not found");
        }
        model.addAttribute(STORY, storyService.findByTitle(title));
        return STORY;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest request, Exception exception, Model model) {
        model.addAttribute("errMessage", exception.getMessage());
        return "exceptionHandler";
    }

}
