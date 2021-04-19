package hu.durasoft.controller;

import hu.durasoft.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class HomeController {

    private StoryService storyService;

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @RequestMapping("/")
    public String stories(Model model, Locale locale) {
        model.addAttribute("pageTitle", "DuraSoft cikkek");
        model.addAttribute("stories", storyService.getStories());
        model.addAttribute("footerText", "Made by DuraSoft © 2021");
        return "stories";
    }

    @RequestMapping("/story")
    public String story(Model model) {
        model.addAttribute("story", storyService.findFirstByOrderByPostedDesc());
        model.addAttribute("footerText", "Made by DuraSoft © 2021");
        return "story";
    }

    @RequestMapping("/title/{title}")
    public String searchTitle(@PathVariable(value = "title") String title, Model model) {
        if (title == null) {
            throw new IllegalArgumentException("Title not found");
        }
        model.addAttribute("story", storyService.findByTitle(title));
        return "story";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest request, Exception exception, Model model) {
        model.addAttribute("errMessage", exception.getMessage());
        return "exceptionHandler";
    }

}
