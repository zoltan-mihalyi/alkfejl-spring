package hu.elte;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private List<Recipe> recipes = new ArrayList<>();

    public MainController() {
        recipes.add(new Recipe("recipe 1", "asd"));
        recipes.add(new Recipe("recipe 2", "asdaads"));
    }

    @RequestMapping("/")
    public ModelAndView mainPage() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("title", "My page");
        result.addObject("recipes", recipes);
        return result;
    }

    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView result = new ModelAndView("edit");
        result.addObject("recipe", recipes.get(id));
        return result;
    }

    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.POST)
    public ModelAndView editSubmit(@PathVariable int id, Recipe recipe) {
        recipes.remove(id);
        recipes.add(id, recipe);
        return edit(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result = new ModelAndView("edit");
        result.addObject("recipe", new Recipe());
        return result;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createSubmit(Recipe recipe) {
        recipes.add(recipe);
        return "redirect:/recipe/" + (recipes.size() - 1);
    }

    @RequestMapping(path = "/recipe/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable int id) {
        recipes.remove(id);
        return "redirect:/";
    }
}
